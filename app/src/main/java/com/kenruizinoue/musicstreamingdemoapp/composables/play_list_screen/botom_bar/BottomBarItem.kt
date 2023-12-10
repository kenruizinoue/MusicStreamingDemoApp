package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.botom_bar

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.R
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarItemIconSize
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarItemTextStyle
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.DarkBackgroundColor
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LightGray

@Composable
fun BottomBarItem(modifier: Modifier, painter: Painter, text: String, selected: Boolean) {
    val context = LocalContext.current
    val color = if (selected) Color.White else LightGray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.then(Modifier.clickable {
            Toast
                .makeText(
                    context,
                    context.resources.getString(R.string.bottom_bar_item_clicked),
                    Toast.LENGTH_SHORT
                )
                .show()
        })
    ) {
        Icon(
            painter = painter,
            contentDescription = text,
            tint = color,
            modifier = Modifier.size(BottomBarItemIconSize)
        )
        Text(text = text, color = color, style = BottomBarItemTextStyle)
    }
}

@Preview
@Composable
fun PreviewBottomBarItem() {
    Box(
        modifier = Modifier
            .height(BottomBarHeight)
            .background(DarkBackgroundColor)
    ) {
        BottomBarItem(
            painter = painterResource(id = R.drawable.ic_music_library),
            text = "Library",
            modifier = Modifier.width(BottomBarHeight),
            selected = false
        )
    }
}
