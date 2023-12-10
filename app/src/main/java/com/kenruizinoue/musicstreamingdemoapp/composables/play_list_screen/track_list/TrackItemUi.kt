package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.track_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.kenruizinoue.musicstreamingdemoapp.R
import com.kenruizinoue.musicstreamingdemoapp.data.PlaybackState
import com.kenruizinoue.musicstreamingdemoapp.data.TrackItemData
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LargeDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumLargeDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TrackItemHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryBlack
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryWhite
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TertiaryColor
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TrackItemAlbumCoverSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TrackItemSecondaryTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TrackItemPrimaryTextStyle
import com.kenruizinoue.musicstreamingdemoapp.utils.formatDuration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun TrackItem(
    track: TrackItemData,
    trackStateFlow: Flow<TrackState> = flowOf(TrackState()),
    playbackProgressFlow: Flow<Float> = flowOf(0f),
    selectTrack: (TrackItemData) -> Unit = {}
) {
    val trackState = trackStateFlow.collectAsState(initial = TrackState()).value
    val playbackProgress = playbackProgressFlow.collectAsState(initial = 0f).value
    val imagePainter = rememberImagePainter(
        data = track.coverDrawableId,
        builder = {
            fallback(R.drawable.fallback_album_cover)
        }
    )
    Row(
        modifier = Modifier
            .height(TrackItemHeight)
            .fillMaxWidth()
            .clickable {
                selectTrack(track)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(MediumLargeDp))
        Box(
            modifier = Modifier
                .size(TrackItemAlbumCoverSize)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = imagePainter,
                contentDescription = stringResource(
                    id = R.string.album_cover_content_description,
                    track.title
                ),
                modifier = Modifier.fillMaxSize()
            )
            if (trackState.track.id == track.id) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PrimaryBlack.copy(alpha = 0.5f))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(MediumDp),
                        progress = playbackProgress,
                        color = TertiaryColor,
                        trackColor = PrimaryWhite
                    )
                }
                val (iconId, contentDescriptionId) =
                    if (trackState.playbackState == PlaybackState.PLAYING) {
                        R.drawable.ic_pause to R.string.pause_button_content_description
                    } else {
                        R.drawable.ic_play to R.string.play_button_content_description
                    }
                Icon(
                    painter = painterResource(id = iconId),
                    tint = PrimaryWhite,
                    contentDescription = stringResource(id = contentDescriptionId),
                )
            }
        }
        Spacer(modifier = Modifier.width(LargeDp))
        Column(
            modifier = Modifier
                .weight(1f) // This makes the column take up all available space
        ) {
            Text(
                style = TrackItemPrimaryTextStyle,
                text = track.title,
                color = if (trackState.track.id == track.id) TertiaryColor else PrimaryBlack,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                style = TrackItemSecondaryTextStyle,
                text = track.artist,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            modifier = Modifier.padding(end = LargeDp),
            style = TrackItemSecondaryTextStyle,
            text = formatDuration(track.trackDuration)
        )
    }
}

@Preview
@Composable
fun TrackItemUiPreview() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        TrackItem(
            track = TrackItemData(
                id = 1,
                title = "Midnight Echoes",
                artist = "Luna Harmonics",
                coverDrawableId = R.drawable.cover1
            ),
            playbackProgressFlow = flowOf(0.5f),
            trackStateFlow = flowOf(
                TrackState(
                    track = TrackItemData(
                        id = 1,
                        title = "Midnight Echoes",
                        artist = "Luna Harmonics",
                        coverDrawableId = R.drawable.cover1
                    ),
                    playbackState = PlaybackState.PLAYING
                )
            )
        )
    }
}
