package com.kenruizinoue.musicstreamingdemoapp

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenruizinoue.musicstreamingdemoapp.data.BottomBarItemData
import com.kenruizinoue.musicstreamingdemoapp.data.MockDataProvider
import com.kenruizinoue.musicstreamingdemoapp.data.PlaybackState
import com.kenruizinoue.musicstreamingdemoapp.data.TrackItemData
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _trackState = MutableStateFlow(TrackState())
    private var elapsedTimeJob: Job? = null
    private var pausedTime: Long = 0

    // Update the data sources as needed
    val labelItems: List<String> = MockDataProvider.labelItems
    val trackItems: List<TrackItemData> = MockDataProvider.trackItems
    val bottomBarItems: List<BottomBarItemData> = MockDataProvider.bottomBarItems
    val trackState: StateFlow<TrackState> = _trackState.asStateFlow()
    val progressFlow: StateFlow<Float> = _trackState.map { trackState ->
        calculateProgress(trackState)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)

    init {
        // Select initial track
        selectTrack(MockDataProvider.trackItems[0], PlaybackState.PAUSED)
    }

    private fun calculateProgress(trackState: TrackState): Float {
        if (trackState.track.trackDuration > 0) {
            val progress = trackState.elapsedTime.toFloat() / trackState.track.trackDuration.toFloat()
            if (progress >= 1f) selectNextTrack() // Track finished, select next track
            return progress.coerceIn(0f, 1f) // Make sure progress is between 0 and 1
        }
        return 0f
    }

    fun selectPreviousTrack() {
        MockDataProvider.getPreviousTrackById(_trackState.value.track.id)?.let {
            selectTrack(it)
        }
    }

    fun selectNextTrack() {
        MockDataProvider.getNextTrackById(_trackState.value.track.id)?.let {
            selectTrack(it)
        }
    }

    fun selectTrack(track: TrackItemData, playbackState: PlaybackState = PlaybackState.PLAYING) {
        // If the track is already selected, toggle playback state
        if (track.id == _trackState.value.track.id) {
            togglePlaybackState()
        } else {
            _trackState.value = TrackState(track, playbackState)
            resetElapsedTime()
            if (playbackState == PlaybackState.PLAYING) {
                startElapsedTimeCalculation()
            }
        }
    }

    fun togglePlaybackState() {
        val currentState = _trackState.value
        when (currentState.playbackState) {
            PlaybackState.PLAYING -> {
                pausedTime = _trackState.value.elapsedTime
                elapsedTimeJob?.cancel()
                _trackState.value = currentState.copy(playbackState = PlaybackState.PAUSED)
            }
            PlaybackState.PAUSED -> {
                startElapsedTimeCalculation(pausedTime)
                _trackState.value = currentState.copy(playbackState = PlaybackState.PLAYING)
            }
        }
    }

    private fun resetElapsedTime() {
        pausedTime = 0L
        _trackState.value = _trackState.value.copy(elapsedTime = 0L)
        elapsedTimeJob?.cancel()
    }

    private fun startElapsedTimeCalculation(startTime: Long = 0L) {
        elapsedTimeJob?.cancel() // Cancel any existing job
        elapsedTimeJob = viewModelScope.launch {
            val initialTime = SystemClock.elapsedRealtime() - startTime
            while (isActive) {
                val elapsedTime = SystemClock.elapsedRealtime() - initialTime
                _trackState.value = _trackState.value.copy(elapsedTime = elapsedTime)
                delay(50)
            }
        }
    }

    override fun onCleared() {
        elapsedTimeJob?.cancel()
    }
}
