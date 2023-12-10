package com.kenruizinoue.musicstreamingdemoapp.data

data class TrackState(
    val track: TrackItemData = TrackItemData(),
    val playbackState: PlaybackState = PlaybackState.PAUSED,
    var elapsedTime: Long = 0L
)
