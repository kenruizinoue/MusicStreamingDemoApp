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
import com.kenruizinoue.musicstreamingdemoapp.data.Track
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.DarkBackgroundColor
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.ElectricPurple
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LargeMargin
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumMargin
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.SmallMargin
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TeackItemHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TitleBlue
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TrackItemAlbumCoverSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TrackItemSecondaryTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.TrackItemPrimaryTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.White
import com.kenruizinoue.musicstreamingdemoapp.utils.formatDuration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun TrackItem(
    track: Track,
    trackStateFlow: Flow<TrackState> = flowOf(TrackState()),
    playbackProgressFlow: Flow<Float> = flowOf(0f),
    selectTrack: (Track) -> Unit = {}
) {
    val trackState = trackStateFlow.collectAsState(initial = TrackState()).value
    val playbackProgress = playbackProgressFlow.collectAsState(initial = 0f).value

    val imagePainter = rememberImagePainter(
        data = track.coverDrawableId,
        builder = {
            fallback(R.drawable.falback_album_cover)
        }
    )

    Row(
        modifier = Modifier
            .height(TeackItemHeight)
            .fillMaxWidth()
            .clickable {
                selectTrack(track)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(LargeMargin))
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
                        .background(DarkBackgroundColor.copy(alpha = 0.5f))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize().padding(MediumMargin),
                        progress = playbackProgress,
                        color = ElectricPurple,
                        trackColor = White
                    )
                }
                // TODO ken add content description
                val iconId =
                    if (trackState.playbackState == PlaybackState.PLAYING) {
                        R.drawable.ic_pause
                    } else {
                        R.drawable.ic_play
                    }
                Icon(
                    painter = painterResource(id = iconId),
                    tint = White,
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.width(LargeMargin))
        Column(
            modifier = Modifier
                .weight(1f) // This makes the column take up all available space
        ) {
            Text(
                style = TrackItemPrimaryTextStyle,
                text = track.title,
                color = if (trackState.track.id == track.id) ElectricPurple else TitleBlue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(SmallMargin))
            Text(
                style = TrackItemSecondaryTextStyle,
                text = track.artist,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            modifier = Modifier.padding(end = LargeMargin),
            style = TrackItemSecondaryTextStyle,
            text = formatDuration(track.trackDuration)
        )
    }
}

@Preview
@Composable
fun TrackItemPreview() {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        TrackItem(
            track = Track(
                id = 1,
                title = "Midnight Echoes",
                artist = "Luna Harmonics",
                coverDrawableId = R.drawable.cover1
            ),
            playbackProgressFlow = flowOf(0.5f),
            trackStateFlow = flowOf(
                TrackState(
                    track = Track(
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
