package com.squidat.streamyx.nodes.bottom_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.LeafNode
import com.squidat.streamyx.ui.theme.StreamyxTheme

class BottomNavigationNode(
    nodeContext: NodeContext
) : LeafNode(nodeContext) {

    @Composable
    override fun Content(modifier: Modifier) {
        StreamyxBottomNavigationBar(modifier)
    }
}

@Composable
private fun StreamyxBottomNavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(56.dp)
            .background(Color.Red),
    ) {

    }
}

@Preview
@Composable
private fun StreamyxBottomNavigationBarPreview() {
    StreamyxTheme {
        StreamyxBottomNavigationBar(modifier = Modifier.fillMaxWidth())
    }
}