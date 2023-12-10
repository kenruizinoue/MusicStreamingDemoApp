package com.kenruizinoue.musicstreamingdemoapp.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val PlayListTitleStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
)

val LabelTextStyle = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp
)

val TrackItemPrimaryTextStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    letterSpacing = 0.5.sp
)

val TrackItemSecondaryTextStyle = TextStyle(
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    color = ArtistBlue,
    letterSpacing = 0.5.sp
)

val SelectedTrackItemTitleLabelStyle = TextStyle(
    fontWeight = FontWeight.Medium,
    color = Color.White,
    fontSize = 13.sp,
)

val SelectedTrackItemArtistLabelStyle = TextStyle(
    color = MediumGray,
    fontSize = 11.sp
)

val BottomBarItemTextStyle = TextStyle(
    fontSize = 10.sp
)
