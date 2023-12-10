package com.kenruizinoue.musicstreamingdemoapp.utils

fun formatDuration(durationMillis: Long): String {
    val totalSeconds = durationMillis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    return String.format("%d:%02d", minutes, seconds)
}
