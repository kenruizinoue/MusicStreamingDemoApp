package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.top_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LabelTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryWhite
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LargeDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumDp
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryColor

@Composable
fun Label(text: String = "", selected: Boolean = false, onClick: () -> Unit = {}) {
    val alphaValue by animateFloatAsState(targetValue = if (selected) 1f else 0f)
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null, // This removes the ripple effect
                onClick = onClick
            )
            .background(
                if (selected) PrimaryColor.copy(alpha = alphaValue)
                else PrimaryColor.copy(alpha = alphaValue), RoundedCornerShape(MediumDp)
            )
            .padding(horizontal = LargeDp, vertical = MediumDp)
    ) {
        Text(
            text = text,
            color = if (selected) PrimaryWhite else PrimaryColor,
            style = LabelTextStyle
        )
    }
}

@Preview
@Composable
fun LabelItemUiPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryWhite),
        contentAlignment = Alignment.Center
    ) {
        Label(text = "PlayList1", selected = true)
    }
}
