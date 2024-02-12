package com.squidat.streamyx.nodes.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.squidat.streamyx.mininimize_component.MinimizableBackstack
import com.squidat.streamyx.mininimize_component.MinimizableBackstackModel
import com.squidat.streamyx.nodes.main.MainNode
import com.squidat.streamyx.nodes.video_player.VideoPlayerNode

class RootNode(
    nodeContext: NodeContext,
    private val backstack: MinimizableBackstack<RootNavigation> = nodeContext.buildBackstack(),
) : Node<RootNavigation>(
    nodeContext = nodeContext,
    appyxComponent = backstack,
    plugins = listOf(RootInteractor(backstack)),
) {

    override fun buildChildNode(navTarget: RootNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            is RootNavigation.Main -> MainNode(nodeContext)
            is RootNavigation.VideoPlayer -> VideoPlayerNode(nodeContext, navTarget.video)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        AppyxNavigationContainer(modifier = modifier, appyxComponent = backstack)
    }
}

private fun NodeContext.buildBackstack(): MinimizableBackstack<RootNavigation> {
    return MinimizableBackstack(
        model = MinimizableBackstackModel(
            defaultItem = RootNavigation.Main,
            savedStateMap = savedStateMap,
        ),
    )
}