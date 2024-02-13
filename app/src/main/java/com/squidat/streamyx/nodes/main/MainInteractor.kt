package com.squidat.streamyx.nodes.main

import com.bumble.appyx.navigation.clienthelper.interactor.Interactor
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.squidat.streamyx.nodes.feed.FeedNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainInteractor : Interactor<MainNode>() {

    override fun onCreate(lifecycle: Lifecycle) {
        whenChildAttached(FeedNode::class) { commonLifecycle, child ->
            child.collectOutputIn(commonLifecycle)
        }
    }

    private fun FeedNode.collectOutputIn(lifecycle: Lifecycle) {
        lifecycle.coroutineScope.launch {
            output.collectLatest { output ->
                when (output) {
                    is FeedNode.Output.VideoSelected -> node.output
                        .emit(MainNode.Output.VideoSelected(output.video))
                }
            }
        }
    }
}