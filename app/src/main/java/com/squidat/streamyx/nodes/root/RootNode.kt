package com.squidat.streamyx.nodes.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.squidat.streamyx.nodes.home.MainNode
import com.squidat.streamyx.nodes.video_player.VideoPlayerNode
import com.squidat.streamyx.picture_in_picture.PictureInPicture
import com.squidat.streamyx.picture_in_picture.PictureInPictureModel
import com.squidat.streamyx.picture_in_picture.gesture.DragOverlayGesture
import com.squidat.streamyx.picture_in_picture.ui.floating_visualization.FloatingVisualization

class RootNode(
    nodeContext: NodeContext,
    private val pip: PictureInPicture<RootNavigation> = nodeContext.buildPictureInPicture(),
) : Node<RootNavigation>(
    nodeContext = nodeContext,
    appyxComponent = pip,
    plugins = listOf(RootInteractor(pip)),
) {

    override fun buildChildNode(navTarget: RootNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            is RootNavigation.Home -> MainNode(nodeContext)
            is RootNavigation.VideoPlayer -> VideoPlayerNode(nodeContext, navTarget.video)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        AppyxNavigationContainer(modifier = modifier, appyxComponent = pip)
    }
}

private fun NodeContext.buildPictureInPicture(): PictureInPicture<RootNavigation> {
    return PictureInPicture(
        model = PictureInPictureModel(
            defaultItem = RootNavigation.Home,
            savedStateMap = savedStateMap,
        ),
        visualisation = { FloatingVisualization(it) },
        gestureFactory = { DragOverlayGesture(it) },
    )
}