package com.squidat.streamyx.nodes.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.spotlight.Spotlight
import com.bumble.appyx.components.spotlight.SpotlightModel
import com.bumble.appyx.components.spotlight.ui.fader.SpotlightFader
import com.bumble.appyx.interactions.core.model.plus
import com.bumble.appyx.interactions.permanent.PermanentAppyxComponent
import com.bumble.appyx.interactions.permanent.PermanentModel
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.composable.PermanentChild
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.squidat.streamyx.components.StreamyxToolbar
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode
import com.squidat.streamyx.nodes.feed.FeedNode

class RootNode(
    nodeContext: NodeContext,
    private val spotlight: Spotlight<RootNavigation> = nodeContext.buildSpotlight(),
    private val permanent: PermanentAppyxComponent<RootNavigation> = nodeContext.buildPermanent(),
) : Node<RootNavigation>(
    nodeContext = nodeContext,
    appyxComponent = permanent + spotlight,
) {

    override fun buildChildNode(navTarget: RootNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            RootNavigation.Content.Feed -> FeedNode(nodeContext)
            RootNavigation.Permanent.NavigationBar -> BottomNavigationNode(nodeContext)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        Column(modifier = modifier) {
            StreamyxToolbar(modifier = Modifier.fillMaxWidth())
            AppyxNavigationContainer(
                modifier = Modifier.weight(1f),
                appyxComponent = spotlight,
            )
            PermanentChild(
                modifier = Modifier.fillMaxWidth(),
                permanentAppyxComponent = permanent,
                navTarget = RootNavigation.Permanent.NavigationBar,
            )
        }
    }
}

private fun NodeContext.buildSpotlight(): Spotlight<RootNavigation> {
    return Spotlight(
        model = SpotlightModel(
            items = listOf(RootNavigation.Content.Feed),
            savedStateMap = savedStateMap,
        ),
        visualisation = { SpotlightFader(it) },
    )
}

private fun NodeContext.buildPermanent(): PermanentAppyxComponent<RootNavigation> {
    return PermanentAppyxComponent(
        model = PermanentModel(
            savedStateMap = savedStateMap,
            initialTargets = listOf(RootNavigation.Permanent.NavigationBar),
        ),
    )
}