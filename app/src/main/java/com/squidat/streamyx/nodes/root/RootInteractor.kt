package com.squidat.streamyx.nodes.root

import com.bumble.appyx.components.spotlight.Spotlight
import com.bumble.appyx.components.spotlight.operation.activate
import com.bumble.appyx.navigation.clienthelper.interactor.Interactor
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode.Output
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RootInteractor(
    private val spotlight: Spotlight<RootNavigation>,
) : Interactor<RootNode>() {

    override fun onCreate(lifecycle: Lifecycle) {
        whenChildAttached(BottomNavigationNode::class) { commonLifecycle, child ->
            commonLifecycle.coroutineScope.launch {
                child.output.collectLatest { output ->
                    when (output) {
                        is Output.TabSelected -> spotlight.activate(
                            spotlightItems
                                .lastIndexOf(output.selection.navigationTarget)
                                .toFloat()
                        )
                    }
                }
            }
        }
    }
}