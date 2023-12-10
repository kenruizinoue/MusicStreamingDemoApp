package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.botom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.R
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.DarkBackgroundColor


@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .height(BottomBarHeight)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(DarkBackgroundColor.copy(alpha = 0.9f), DarkBackgroundColor)
                )
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            painter = painterResource(id = R.drawable.ic_home),
            text = stringResource(id = R.string.bottom_bar_item_home),
            modifier = Modifier.weight(1f),
            selected = true
        )
        BottomBarItem(
            painter = painterResource(id = R.drawable.ic_search),
            text = stringResource(id = R.string.bottom_bar_item_search),
            modifier = Modifier.weight(1f),
            selected = false
        )
        BottomBarItem(
            painter = painterResource(id = R.drawable.ic_music_library),
            text = stringResource(id = R.string.bottom_bar_item_library),
            modifier = Modifier.weight(1f),
            selected = false
        )
    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    BottomBar()
}
