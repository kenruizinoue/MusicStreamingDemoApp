package com.kenruizinoue.musicstreamingdemoapp.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun isScrolledToBottom(lazyListState: LazyListState): Boolean {
    val layoutInfo = remember { derivedStateOf { lazyListState.layoutInfo } }.value
    val totalItemCount = layoutInfo.totalItemsCount
    val lastVisibleItemIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

    return lastVisibleItemIndex >= totalItemCount - 1
}

@Composable
fun getScreenWidth(): Dp {
    return LocalConfiguration.current.screenWidthDp.dp
}
