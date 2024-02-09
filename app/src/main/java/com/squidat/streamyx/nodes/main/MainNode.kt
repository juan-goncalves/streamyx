package com.squidat.streamyx.nodes.main

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
import com.squidat.streamyx.models.Video
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode.Tab
import com.squidat.streamyx.nodes.feed.FeedNode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class MainNode(
    nodeContext: NodeContext,
    private val spotlight: Spotlight<MainNavigation> = nodeContext.buildSpotlight(),
    private val permanent: PermanentAppyxComponent<MainNavigation> = nodeContext.buildPermanent(),
) : Node<MainNavigation>(
    nodeContext = nodeContext,
    appyxComponent = permanent + spotlight,
    plugins = listOf(MainInteractor(spotlight))
) {

    val output: MutableSharedFlow<Output> = MutableSharedFlow()

    sealed interface Output {
        data class VideoSelected(val video: Video) : Output
    }

    override fun buildChildNode(navTarget: MainNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            MainNavigation.Content.Feed -> FeedNode(nodeContext)
            MainNavigation.Content.Inbox -> node(nodeContext) {
                Text(text = "Inbox!")
            }

            MainNavigation.Content.Favorites -> node(nodeContext) {
                Text(text = "Favorites!")
            }

            MainNavigation.Content.You -> node(nodeContext) {
                Text(text = "You!")
            }

            MainNavigation.Permanent.NavigationBar -> BottomNavigationNode(nodeContext)
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
                navTarget = MainNavigation.Permanent.NavigationBar,
            )
        }
    }
}

private fun NodeContext.buildSpotlight(): Spotlight<MainNavigation> {
    return Spotlight(
        model = SpotlightModel(
            items = spotlightItems,
            savedStateMap = savedStateMap,
        ),
        visualisation = { SpotlightFader(it) },
    )
}

private fun NodeContext.buildPermanent(): PermanentAppyxComponent<MainNavigation> {
    return PermanentAppyxComponent(
        model = PermanentModel(
            savedStateMap = savedStateMap,
            initialTargets = listOf(MainNavigation.Permanent.NavigationBar),
        ),
    )
}

val spotlightItems: List<MainNavigation>
    get() = Tab.entries.map(Tab::navigationTarget)

val Tab.navigationTarget: MainNavigation
    get() = when (this) {
        Tab.Home -> MainNavigation.Content.Feed
        Tab.Inbox -> MainNavigation.Content.Inbox
        Tab.Favorites -> MainNavigation.Content.Favorites
        Tab.You -> MainNavigation.Content.You
    }