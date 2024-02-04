package com.squidat.streamyx.nodes.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
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
import com.bumble.appyx.navigation.node.node
import com.squidat.streamyx.components.StreamyxToolbar
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode.Tab
import com.squidat.streamyx.nodes.feed.FeedNode

class RootNode(
    nodeContext: NodeContext,
    private val spotlight: Spotlight<RootNavigation> = nodeContext.buildSpotlight(),
    private val permanent: PermanentAppyxComponent<RootNavigation> = nodeContext.buildPermanent(),
) : Node<RootNavigation>(
    nodeContext = nodeContext,
    appyxComponent = permanent + spotlight,
    plugins = listOf(RootInteractor(spotlight))
) {

    override fun buildChildNode(navTarget: RootNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            RootNavigation.Content.Feed -> FeedNode(nodeContext)
            RootNavigation.Content.Inbox -> node(nodeContext) {
                Text(text = "Inbox!")
            }

            RootNavigation.Content.Favorites -> node(nodeContext) {
                Text(text = "Favorites!")
            }

            RootNavigation.Content.You -> node(nodeContext) {
                Text(text = "You!")
            }

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
            items = spotlightItems,
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

val spotlightItems: List<RootNavigation>
    get() = Tab.entries.map(Tab::navigationTarget)

val Tab.navigationTarget: RootNavigation
    get() = when (this) {
        Tab.Home -> RootNavigation.Content.Feed
        Tab.Inbox -> RootNavigation.Content.Inbox
        Tab.Favorites -> RootNavigation.Content.Favorites
        Tab.You -> RootNavigation.Content.You
    }