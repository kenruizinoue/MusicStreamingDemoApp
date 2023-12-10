package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.track_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.kenruizinoue.musicstreamingdemoapp.data.MockTrackListProvider
import com.kenruizinoue.musicstreamingdemoapp.data.Track
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.DarkBackgroundColor
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.OverlappingHeight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrackList(
    trackList: List<Track>,
    selectTrack: (Track) -> Unit = {},
    trackStateFlow: Flow<TrackState> = flowOf(TrackState()),
    playbackProgressFlow: Flow<Float> = flowOf(0f),
    overlappingElementsHeight: Dp = OverlappingHeight
) {
    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        LazyColumn {
            items(trackList, key = { track -> track.id }) { track ->
                TrackItem(
                    track = track,
                    selectTrack = selectTrack,
                    trackStateFlow = trackStateFlow,
                    playbackProgressFlow = playbackProgressFlow
                )
            }
            item {
                Spacer(modifier = Modifier.height(overlappingElementsHeight))
            }
        }
    }
}

@Preview
@Composable
fun TrackListPreview() {
    Box(modifier = Modifier.background(DarkBackgroundColor)) {
        TrackList(trackList = MockTrackListProvider.trackList)
    }
}
