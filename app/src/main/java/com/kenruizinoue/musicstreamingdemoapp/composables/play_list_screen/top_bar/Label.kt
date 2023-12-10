package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LabelTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.SnowColor
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LargeCorner
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LargeMargin
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumMargin
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryDarkBlue

@Composable
fun Label(text: String, labelSelected: Boolean, onClick: () -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null, // This removes the ripple effect
                onClick = onClick
            )
            .background(if (labelSelected) PrimaryDarkBlue else SnowColor, RoundedCornerShape(LargeCorner))
            .padding(horizontal = LargeMargin, vertical = MediumMargin)
    ) {
        Text(
            text = text,
            color = if (labelSelected) SnowColor else PrimaryDarkBlue,
            style = LabelTextStyle
        )
    }
}

@Preview
@Composable
fun PreviewLabel() {
    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(SnowColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Label(text = "PlayList1", labelSelected = false)
    }
}
