package com.kenruizinoue.musicstreamingdemoapp.data

data class Track(
    val id: Int = -1,
    val title: String = "",
    val artist: String = "",
    val coverDrawableId: Int = -1,
    val trackDuration: Long = 0L
)
