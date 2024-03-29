package com.squidat.streamyx.nodes.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.spotlight.Spotlight
import com.bumble.appyx.components.spotlight.SpotlightModel
import com.bumble.appyx.components.spotlight.operation.activate
import com.bumble.appyx.components.spotlight.ui.fader.SpotlightFader
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.node
import com.squidat.streamyx.models.Video
import com.squidat.streamyx.nodes.feed.FeedNode
import com.squidat.streamyx.nodes.home.HomeNavigation.Favorites
import com.squidat.streamyx.nodes.home.HomeNavigation.Feed
import com.squidat.streamyx.nodes.home.HomeNavigation.Inbox
import com.squidat.streamyx.nodes.home.HomeNavigation.You
import com.squidat.streamyx.nodes.home.ui.StreamyxBottomNavigationBar
import com.squidat.streamyx.nodes.home.ui.StreamyxToolbar
import kotlinx.coroutines.flow.MutableSharedFlow

class MainNode(
    nodeContext: NodeContext,
    private val spotlight: Spotlight<HomeNavigation> = nodeContext.buildSpotlight(),
) : Node<HomeNavigation>(
    nodeContext = nodeContext,
    appyxComponent = spotlight,
    plugins = listOf(HomeInteractor())
) {

    val output: MutableSharedFlow<Output> = MutableSharedFlow()

    sealed interface Output {
        data class VideoSelected(val video: Video) : Output
    }

    override fun buildChildNode(navTarget: HomeNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            Feed -> FeedNode(nodeContext)
            Inbox -> node(nodeContext) { Text(text = "Inbox!") }
            Favorites -> node(nodeContext) { Text(text = "Favorites!") }
            You -> node(nodeContext) { Text(text = "You!") }
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
            StreamyxBottomNavigationBar(
                modifier = Modifier.fillMaxWidth(),
                onTabSelected = ::navigateTo,
            )
        }
    }

    private fun navigateTo(target: HomeNavigation) {
        val index = spotlightItems
            .lastIndexOf(target)
            .toFloat()

        spotlight.activate(index)
    }
}

private fun NodeContext.buildSpotlight(): Spotlight<HomeNavigation> {
    return Spotlight(
        model = SpotlightModel(
            items = spotlightItems,
            savedStateMap = savedStateMap,
        ),
        visualisation = { SpotlightFader(it) },
    )
}

val spotlightItems: List<HomeNavigation>
    get() = listOf(Feed, Inbox, Favorites, You)