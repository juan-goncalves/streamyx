package com.squidat.streamyx.nodes.root

import com.bumble.appyx.navigation.clienthelper.interactor.Interactor
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.squidat.streamyx.minimizable_backstack.MinimizableBackstack
import com.squidat.streamyx.minimizable_backstack.operation.dismiss
import com.squidat.streamyx.minimizable_backstack.operation.maximize
import com.squidat.streamyx.minimizable_backstack.operation.push
import com.squidat.streamyx.nodes.main.MainNode
import com.squidat.streamyx.nodes.video_player.VideoPlayerNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RootInteractor(
    private val backstack: MinimizableBackstack<RootNavigation>,
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
                    is MainNode.Output.VideoSelected -> {
                        backstack.push(RootNavigation.VideoPlayer(output.video))
                    }
                }
            }
        }
    }

    private fun VideoPlayerNode.collectOutputIn(lifecycle: Lifecycle) {
        lifecycle.coroutineScope.launch {
            output.collectLatest { output ->
                when (output) {
                    is VideoPlayerNode.Output.MaximizeSelected -> backstack.maximize()
                    is VideoPlayerNode.Output.DismissSelected -> backstack.dismiss()
                }
            }
        }
    }
}