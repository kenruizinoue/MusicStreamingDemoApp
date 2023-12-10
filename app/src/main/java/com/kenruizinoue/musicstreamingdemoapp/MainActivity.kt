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
                        trackList = viewModel.trackList,
                        trackStateFlow = viewModel.trackState,
                        progressFlow = viewModel.progressFlow,
                        playPauseButtonClicked = viewModel::togglePlaybackState,
                        selectTrack = viewModel::selectTrack
                    )
                }
            }
        }
    }
}
