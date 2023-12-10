package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.botom_bar.BottomBar
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.playback_bar.FloatingPlaybackBar
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.top_bar.LabelSelectorBar
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.track_list.TrackList
import com.kenruizinoue.musicstreamingdemoapp.data.BottomBarItemData
import com.kenruizinoue.musicstreamingdemoapp.data.MockDataProvider
import com.kenruizinoue.musicstreamingdemoapp.data.TrackItemData
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.OverlappingHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryWhite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun PlayListScreen(
    labelItems: List<String> = listOf(""),
    trackItems: List<TrackItemData> = listOf(TrackItemData()),
    bottomBarItems: List<BottomBarItemData> = emptyList(),
    trackStateFlow: Flow<TrackState> = flowOf(TrackState()),
    playbackProgressFlow: Flow<Float> = flowOf(0f),
    onPreviousClicked: () -> Unit = {},
    onPlayPauseClicked: () -> Unit = {},
    onNextClicked: () -> Unit = {},
    selectTrack: (TrackItemData) -> Unit = {},
) {
    val lazyListState = rememberLazyListState()
    Box {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(PrimaryWhite)) {
            LabelSelectorBar(labelItems)
            TrackList(
                lazyListState = lazyListState,
                trackItems = trackItems,
                selectTrack = { track -> selectTrack(track) },
                trackStateFlow = trackStateFlow,
                overlappingElementsHeight = OverlappingHeight,
                playbackProgressFlow = playbackProgressFlow
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
        ) {
            FloatingPlaybackBar(
                lazyListState = lazyListState,
                trackStateFlow = trackStateFlow,
                onPreviousClicked = onPreviousClicked,
                onPlayPauseClicked = onPlayPauseClicked,
                onNextClicked = onNextClicked
            )
            BottomBar(bottomBarItems = bottomBarItems)
        }
    }
}

@Preview
@Composable
fun PreviewPlayListScreen() {
    PlayListScreen(
        labelItems = MockDataProvider.labelItems,
        trackItems = MockDataProvider.trackItems,
        bottomBarItems = MockDataProvider.bottomBarItems,
        trackStateFlow = flowOf(TrackState(track = MockDataProvider.trackItems[0]))
    )
}
