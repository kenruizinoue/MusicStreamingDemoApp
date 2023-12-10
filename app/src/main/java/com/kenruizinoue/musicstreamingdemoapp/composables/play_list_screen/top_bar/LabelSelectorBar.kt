package com.kenruizinoue.musicstreamingdemoapp.composables.play_list_screen.top_bar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kenruizinoue.musicstreamingdemoapp.data.MockDataProvider
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.LabelSelectorBarHeight
import com.kenruizinoue.musicstreamingdemoapp.ui.theme.MediumLargeDp

@Composable
fun LabelSelectorBar(labelItems: List<String> = listOf()) {
    val selectedLabel = rememberSaveable { mutableStateOf(labelItems.firstOrNull() ?: "") }
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.height(LabelSelectorBarHeight)
    ) {
        item { Spacer(modifier = Modifier.width(MediumLargeDp)) }
        items(labelItems) { label ->
            Label(text = label, selected = label == selectedLabel.value) {
                selectedLabel.value = label
            }
        }
        item { Spacer(modifier = Modifier.width(MediumLargeDp)) }
    }
}

@Preview
@Composable
fun LabelSelectorPreview() {
    LabelSelectorBar(labelItems = MockDataProvider.labelItems)
}
