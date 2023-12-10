package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.botom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.R
import com.kenruizinoue.musicstreamingdemoapp.data.BottomBarItemData
import com.kenruizinoue.musicstreamingdemoapp.data.MockDataProvider
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.BottomBarHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.PrimaryColor
import com.kenruizinoue.musicstreamingdemoapp.utils.getScreenWidth

@Composable
fun BottomBar(
    selectedBottomBarItem: MutableState<BottomBarItemData> =
        mutableStateOf(BottomBarItemData(1, "Home", R.drawable.ic_home)),
    bottomBarItems: List<BottomBarItemData> = listOf()
) {
    val itemWidth = getScreenWidth() / bottomBarItems.size
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(BottomBarHeight)
            .background(PrimaryColor),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(bottomBarItems) { item ->
            BottomBarItem(
                itemData = item,
                modifier = Modifier.width(itemWidth),
                selected = selectedBottomBarItem.value.id == item.id
            ) {
                selectedBottomBarItem.value = item
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    val dataState = remember { mutableStateOf(BottomBarItemData(1, "Home", R.drawable.ic_home)) }
    BottomBar(
        selectedBottomBarItem = dataState,
        bottomBarItems = MockDataProvider.bottomBarItems
    )
}
