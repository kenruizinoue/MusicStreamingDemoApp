package com.kenruizinoue.musicstreamingdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.PlayListScreen
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MusicStreamingDemoAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicStreamingDemoAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PlayListScreen(
                        labelItems = viewModel.labelItems,
                        trackItems = viewModel.trackItems,
                        bottomBarItems = viewModel.bottomBarItems,
                        trackStateFlow = viewModel.trackState,
                        playbackProgressFlow = viewModel.progressFlow,
                        onPreviousClicked = viewModel::selectPreviousTrack,
                        onPlayPauseClicked = viewModel::togglePlaybackState,
                        onNextClicked = viewModel::selectNextTrack,
                        selectTrack = viewModel::selectTrack
                    )
                }
            }
        }
    }
}
