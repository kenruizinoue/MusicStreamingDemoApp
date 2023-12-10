package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.botom_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.R
import com.kenruizinoue.musicstreamingdemoapp.data.BottomBarItemData
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarItemIconSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarSelectedItemCircleSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryBlack
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryColor
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryWhite

@Composable
fun BottomBarItem(
    modifier: Modifier,
    itemData: BottomBarItemData = BottomBarItemData(),
    selected: Boolean = false,
    onClick: (BottomBarItemData) -> Unit = {}
) {
    val color = if (selected) PrimaryColor else PrimaryWhite
    val alphaValue by animateFloatAsState(targetValue = if (selected) 1f else 0f)
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.then(Modifier.clickable {
            onClick(itemData)
        })
    ) {
        // If the item is selected, show a circle behind
        Box(
            modifier = Modifier
                .size(BottomBarSelectedItemCircleSize)
                .background(PrimaryWhite.copy(alpha = alphaValue), CircleShape)
        )
        Icon(
            painter = painterResource(id = itemData.iconId),
            contentDescription = itemData.title,
            tint = color,
            modifier = Modifier.size(BottomBarItemIconSize)
        )
    }
}

@Preview
@Composable
fun BottomBarItemUiPreview() {
    Box(
        modifier = Modifier.height(BottomBarHeight).background(PrimaryBlack),
        contentAlignment = Alignment.Center
    ) {
        BottomBarItem(
            itemData = BottomBarItemData(1, "Home", R.drawable.ic_home),
            modifier = Modifier.width(BottomBarHeight),
            selected = false
        )
    }
}
