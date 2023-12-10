package com.kenruizinoue.musicstreamingdemoapp.data

import com.kenruizinoue.musicstreamingdemoapp.R

data class TrackState(
    val track: Track = Track(
        -1,
        "Some Cool Track Title",
        "Artist Name",
        R.drawable.cover1,
        0L
    ),
    val playbackState: PlaybackState = PlaybackState.PAUSED,
    var elapsedTime: Long = 0L
)
