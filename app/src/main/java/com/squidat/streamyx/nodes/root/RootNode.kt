package com.squidat.streamyx.nodes.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.squidat.dock.DockComponent
import com.squidat.dock.create
import com.squidat.streamyx.nodes.home.MainNode
import com.squidat.streamyx.nodes.video_player.VideoPlayerNode

class RootNode(
    nodeContext: NodeContext,
    private val dock: DockComponent<RootNavigation> = DockComponent.create(
        defaultItem = RootNavigation.Home,
        savedStateMap = nodeContext.savedStateMap,
    ),
) : Node<RootNavigation>(
    nodeContext = nodeContext,
    appyxComponent = dock,
    plugins = listOf(RootInteractor(dock)),
) {

    override fun buildChildNode(navTarget: RootNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            is RootNavigation.Home -> MainNode(nodeContext)
            is RootNavigation.VideoPlayer -> VideoPlayerNode(nodeContext, navTarget.video)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        AppyxNavigationContainer(modifier = modifier, appyxComponent = dock)
    }
}
