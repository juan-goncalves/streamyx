package com.squidat.streamyx.nodes.root

import com.bumble.appyx.navigation.clienthelper.interactor.Interactor
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.squidat.dock.DockComponent
import com.squidat.dock.operation.dismiss
import com.squidat.dock.operation.open
import com.squidat.streamyx.nodes.home.MainNode
import com.squidat.streamyx.nodes.video_player.VideoPlayerNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RootInteractor(
    private val dock: DockComponent<RootNavigation>,
) : Interactor<RootNode>() {


    override fun onCreate(lifecycle: Lifecycle) {
        whenChildAttached(MainNode::class) { commonLifecycle, child ->
            child.collectOutputIn(commonLifecycle)
        }

        whenChildAttached(VideoPlayerNode::class) { commonLifecycle, child ->
            child.collectOutputIn(commonLifecycle)
        }
    }

    private fun MainNode.collectOutputIn(lifecycle: Lifecycle) {
        lifecycle.coroutineScope.launch {
            output.collectLatest { output ->
                when (output) {
                    is MainNode.Output.VideoSelected -> dock.open(
                        interactionTarget = RootNavigation.VideoPlayer(output.video),
                    )
                }
            }
        }
    }

    private fun VideoPlayerNode.collectOutputIn(lifecycle: Lifecycle) {
        lifecycle.coroutineScope.launch {
            output.collectLatest { output ->
                when (output) {
                    is VideoPlayerNode.Output.MaximizeSelected -> dock.open(
                        interactionTarget = RootNavigation.VideoPlayer(output.video),
                    )

                    is VideoPlayerNode.Output.DismissSelected -> dock.dismiss()
                }
            }
        }
    }
}