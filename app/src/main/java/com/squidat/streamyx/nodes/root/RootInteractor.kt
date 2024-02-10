package com.squidat.streamyx.nodes.root

import com.bumble.appyx.navigation.clienthelper.interactor.Interactor
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.squidat.streamyx.mininimize_component.MinimizableBackstack
import com.squidat.streamyx.mininimize_component.operation.push
import com.squidat.streamyx.nodes.main.MainNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RootInteractor(
    private val backstack: MinimizableBackstack<RootNavigation>,
) : Interactor<RootNode>() {


    override fun onCreate(lifecycle: Lifecycle) {
        whenChildAttached(MainNode::class) { commonLifecycle, child ->
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
}