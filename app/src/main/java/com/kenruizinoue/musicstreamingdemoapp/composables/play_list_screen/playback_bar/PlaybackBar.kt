package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.playback_bar

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.R
import com.kenruizinoue.musicstreamingdemoapp.data.PlaybackState
import com.kenruizinoue.musicstreamingdemoapp.data.TrackState
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.FiveEightsMediumMargin
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.Gray
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumCorner
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumMargin
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PlayPauseButtonIconSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PlaybackBarButtonIconSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PlaybackBarButtonSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PlaybackBarCoverSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PlaybackBarHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryDarkBlue
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.SelectedTrackItemArtistLabelStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.SelectedTrackItemTitleLabelStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.SmallCorner
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.ThreeEightsMediumMargin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun PlaybackBar(
    playbackProgress: Float,
    trackStateFlow: Flow<TrackState> = flowOf(TrackState()),
    playButtonClicked: () -> Unit = {}
) {

    val trackState = trackStateFlow.collectAsState(initial = TrackState())
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(horizontal = MediumMargin)
            .background(
                color = PrimaryDarkBlue,
                shape = RoundedCornerShape(MediumCorner)
            )
            .height(PlaybackBarHeight)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                Toast
                    .makeText(
                        context,
                        context.resources.getString(R.string.playback_bar_clicked),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
    ) {
        Spacer(modifier = Modifier.height(MediumMargin))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(MediumMargin))
            Image(
                painter = painterResource(id = trackState.value.track.coverDrawableId),
                contentDescription = stringResource(
                    id = R.string.album_cover_content_description,
                    trackState.value.track.title
                ),
                modifier = Modifier
                    .size(PlaybackBarCoverSize)
                    .clip(RoundedCornerShape(SmallCorner))
            )
            Spacer(modifier = Modifier.width(MediumMargin))
            Column(
                modifier = Modifier
                    .weight(1f) // This makes the column take up all the available space
            ) {
                Text(
                    style = SelectedTrackItemTitleLabelStyle,
                    text = trackState.value.track.title,
                    maxLines = 1
                )
                Text(
                    style = SelectedTrackItemArtistLabelStyle,
                    text = trackState.value.track.artist,
                    maxLines = 1
                )
            }
            IconButton(
                onClick = {
                    Toast
                        .makeText(
                            context,
                            context.resources.getString(R.string.options_button_clicked),
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
                modifier = Modifier.size(PlaybackBarButtonSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_audio),
                    contentDescription = stringResource(id = R.string.audio_settings_button_content_description),
                    tint = Color.White,
                    modifier = Modifier.size(PlaybackBarButtonIconSize)
                )
            }
            Spacer(modifier = Modifier.width(MediumMargin))
            IconButton(
                onClick = {
                    playButtonClicked()
                },
                modifier = Modifier.size(PlaybackBarButtonSize)
            ) {
                val (iconId, contentDescriptionId) =
                    if (trackState.value.playbackState == PlaybackState.PLAYING) {
                        R.drawable.ic_pause to R.string.pause_button_content_description
                    } else {
                        R.drawable.ic_play to R.string.play_button_content_description
                    }
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = stringResource(id = contentDescriptionId),
                    tint = Color.White,
                    modifier = Modifier.size(PlayPauseButtonIconSize)
                )
            }
            Spacer(modifier = Modifier.width(MediumMargin))
        }
        Spacer(modifier = Modifier.height(FiveEightsMediumMargin))
        LinearProgressIndicator(
            modifier = Modifier
                .height(ThreeEightsMediumMargin)
                .padding(horizontal = MediumMargin)
                .clip(RoundedCornerShape(MediumCorner))
                .fillMaxWidth(),
            progress = playbackProgress,
            color = Color.White,
            trackColor = Gray
        )
    }
}

@Preview
@Composable
fun PreviewPlaybackStatusBar() {
    val playbackProgress = remember { mutableFloatStateOf(0f) }

    // Simulate playback progress
    LaunchedEffect(Unit) {
        while (playbackProgress.floatValue < 1f) {
            delay(100) // Replace this with actual playback progress update logic
            playbackProgress.floatValue += 0.0005f
        }
    }

    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
        PlaybackBar(
            playbackProgress = playbackProgress.floatValue,
            trackStateFlow = flowOf(TrackState())
        )
    }
}
