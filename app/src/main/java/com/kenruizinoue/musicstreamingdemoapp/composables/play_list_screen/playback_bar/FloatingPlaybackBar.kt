package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.playback_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.kenruizinoue.musicstreamingdemoapp.R
import com.kenruizinoue.musicstreamingdemoapp.data.PlaybackState
import com.kenruizinoue.musicstreamingdemoapp.data.TrackItemData
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.FloatingPlaybackBarButtonIconSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.FloatingPlaybackBarButtonSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.FloatingPlaybackBarCoverSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.FloatingPlaybackBarHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LargeDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.FloatingPlaybackBarSecondaryTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.FloatingPlaybackBarPrimaryTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.SmallDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryWhite
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TertiaryColor
import com.kenruizinoue.musicstreamingdemoapp.utils.isScrolledToBottom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun FloatingPlaybackBar(
    trackStateFlow: Flow<TrackState> = flowOf(TrackState()),
    onPreviousClicked: () -> Unit = {},
    onPlayPauseClicked: () -> Unit = {},
    onNextClicked: () -> Unit = {},
    lazyListState: LazyListState = rememberLazyListState(),
) {
    val trackState = trackStateFlow.collectAsState(initial = TrackState()).value
    val imagePainter = rememberImagePainter(
        data = trackState.track.coverDrawableId,
        builder = {
            fallback(R.drawable.fallback_album_cover)
        }
    )
    val isScrolling = remember { derivedStateOf { lazyListState.isScrollInProgress } }
    val alphaValue by animateFloatAsState(
        targetValue = if (isScrolling.value && !isScrolledToBottom(lazyListState)) 0.5f else 1f,
        label = ""
    )
    Card(
        modifier = Modifier
            .alpha(alphaValue)
            .padding(MediumDp)
            .height(FloatingPlaybackBarHeight)
            .background(Color.Transparent)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = LargeDp),
        shape = RoundedCornerShape(MediumDp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(TertiaryColor)
                .padding(SmallDp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(FloatingPlaybackBarCoverSize)
                    .clip(RoundedCornerShape(MediumDp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = stringResource(
                        id = R.string.album_cover_content_description,
                        trackState.track.title
                    ),
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = LargeDp)
                    .weight(1f) // This makes the column take up all the available space
            ) {
                Text(
                    style = FloatingPlaybackBarPrimaryTextStyle,
                    text = trackState.track.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    style = FloatingPlaybackBarSecondaryTextStyle,
                    text = trackState.track.artist,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(
                onClick = { onPreviousClicked() },
                modifier = Modifier.size(FloatingPlaybackBarButtonSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_previous),
                    contentDescription = stringResource(id = R.string.previous_button_content_description),
                    tint = PrimaryWhite,
                    modifier = Modifier.size(FloatingPlaybackBarButtonIconSize)
                )
            }
            val (iconId, contentDescriptionId) =
                if (trackState.playbackState == PlaybackState.PLAYING) {
                    R.drawable.ic_pause to R.string.pause_button_content_description
                } else {
                    R.drawable.ic_play to R.string.play_button_content_description
                }
            IconButton(
                onClick = { onPlayPauseClicked() },
                modifier = Modifier.size(FloatingPlaybackBarButtonSize)
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = stringResource(id = contentDescriptionId),
                    tint = PrimaryWhite,
                    modifier = Modifier.size(FloatingPlaybackBarButtonIconSize)
                )
            }
            IconButton(
                onClick = { onNextClicked() },
                modifier = Modifier.size(FloatingPlaybackBarButtonSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = stringResource(id = R.string.next_button_content_description),
                    tint = PrimaryWhite,
                    modifier = Modifier.size(FloatingPlaybackBarButtonIconSize)
                )
            }
        }
    }
}

@Preview
@Composable
fun FloatingPlaybackBarPreview() {
    FloatingPlaybackBar(
        trackStateFlow = flowOf(
            TrackState(
                track = TrackItemData(
                    id = 0,
                    title = "Title",
                    artist = "Artist",
                    coverDrawableId = R.drawable.fallback_album_cover,
                    trackDuration = 0
                )
            )
        )
    )
}
