package com.squidat.streamyx.nodes.main

import com.bumble.appyx.components.spotlight.Spotlight
import com.bumble.appyx.components.spotlight.operation.activate
import com.bumble.appyx.navigation.clienthelper.interactor.Interactor
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode
import com.squidat.streamyx.nodes.feed.FeedNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainInteractor(
    private val spotlight: Spotlight<MainNavigation>,
) : Interactor<MainNode>() {

    override fun onCreate(lifecycle: Lifecycle) {
        whenChildAttached(BottomNavigationNode::class) { commonLifecycle, child ->
            child.collectOutputIn(commonLifecycle)
        }

        whenChildAttached(FeedNode::class) { commonLifecycle, child ->
            child.collectOutputIn(commonLifecycle)
        }
    }

    private fun BottomNavigationNode.collectOutputIn(lifecycle: Lifecycle) {
        lifecycle.coroutineScope.launch {
            output.collectLatest { output ->
                when (output) {
                    is BottomNavigationNode.Output.TabSelected -> spotlight.activate(
                        spotlightItems
                            .lastIndexOf(output.selection.navigationTarget)
                            .toFloat()
                    )
                }
            }
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