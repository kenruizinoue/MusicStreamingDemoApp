package com.kenruizinoue.musicstreamingdemoapp

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenruizinoue.musicstreamingdemoapp.data.MockTrackListProvider
import com.kenruizinoue.musicstreamingdemoapp.data.PlaybackState
import com.kenruizinoue.musicstreamingdemoapp.data.Track
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

    val trackList: List<Track> = MockTrackListProvider.trackList // Update the data source as needed
    val trackState: StateFlow<TrackState> = _trackState.asStateFlow()
    val progressFlow: StateFlow<Float> = _trackState.map { trackState ->
        calculateProgress(trackState)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0f)

    init {
        // Select initial track
        selectTrack(MockTrackListProvider.trackList[0], PlaybackState.PAUSED)
    }

    private fun calculateProgress(trackState: TrackState): Float {
        if (trackState.track.trackDuration > 0) {
            val progress = trackState.elapsedTime.toFloat() / trackState.track.trackDuration.toFloat()
            if (progress >= 1f) {
                // Track finished, select next track
                selectNextTrack(trackState)
            }
            return progress.coerceIn(0f, 1f) // Make sure progress is between 0 and 1
        }
        return 0f
    }

    private fun selectNextTrack(trackState: TrackState) {
        MockTrackListProvider.getNextTrackById(trackState.track.id)?.let {
            selectTrack(it)
        }
    }

    fun selectTrack(track: Track, playbackState: PlaybackState = PlaybackState.PLAYING) {
        _trackState.value = TrackState(track, playbackState)
        resetElapsedTime()
        if (playbackState == PlaybackState.PLAYING) {
            startElapsedTimeCalculation()
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
