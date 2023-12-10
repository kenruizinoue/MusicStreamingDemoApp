package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.botom_bar.BottomBar
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.playback_bar.PlaybackBar
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.top_bar.LabelSelector
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.track_list.TrackList
import com.kenruizinoue.musicstreamingdemoapp.data.MockTrackListProvider
import com.kenruizinoue.musicstreamingdemoapp.data.Track
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.OverlappingHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.SnowColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun PlayListScreen(
    trackList: List<Track> = listOf(Track()),
    trackStateFlow: Flow<TrackState> = flowOf(TrackState()),
    progressFlow: Flow<Float> = flowOf(0f),
    playPauseButtonClicked: () -> Unit = {},
    selectTrack: (Track) -> Unit = {},
) {
    val progress = progressFlow.collectAsState(initial = 0f).value
    Box {
        Column(modifier = Modifier.fillMaxSize().background(SnowColor)) {
            // TODO ken get it from the ViewModel
            val options = listOf("All", "Pop", "Rock", "Jazz", "Hip Hop", "Classical")
            LabelSelector(options = options)
            TrackList(
                trackList = trackList,
                selectTrack = { track ->
                    selectTrack(track)
                },
                trackStateFlow = trackStateFlow,
                overlappingElementsHeight = OverlappingHeight,
                playbackProgressFlow = progressFlow
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
        ) {
            PlaybackBar(
                playbackProgress = progress,
                trackStateFlow = trackStateFlow,
                playButtonClicked = playPauseButtonClicked
            )
            BottomBar()
        }
    }
}

@Preview
@Composable
fun PreviewPlayListScreen() {
    PlayListScreen(trackList = MockTrackListProvider.trackList)
}
