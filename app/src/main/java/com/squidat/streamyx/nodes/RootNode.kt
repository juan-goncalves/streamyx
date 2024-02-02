package com.squidat.streamyx.nodes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.squidat.streamyx.components.StreamyxToolbar

class RootNode(
    buildContext: BuildContext,
) : Node(
    buildContext = buildContext,
) {

    @Composable
    override fun View(modifier: Modifier) {
        StreamyxToolbar(modifier = modifier.fillMaxWidth())
    }
}