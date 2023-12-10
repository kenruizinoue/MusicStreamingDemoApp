package com.kenruizinoue.musicstreamingdemoapp.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val LabelTextStyle = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp
)

val TrackItemPrimaryTextStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = 0.5.sp
)

val TrackItemSecondaryTextStyle = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    color = SecondaryColor,
    letterSpacing = 0.5.sp
)

val FloatingPlaybackBarPrimaryTextStyle= TextStyle(
    color = PrimaryWhite,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = 0.5.sp
)

val FloatingPlaybackBarSecondaryTextStyle = TextStyle(
    color = PrimaryWhite,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    letterSpacing = 0.5.sp
)
