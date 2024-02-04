package com.squidat.streamyx.nodes

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.spotlight.Spotlight
import com.bumble.appyx.components.spotlight.SpotlightModel
import com.bumble.appyx.components.spotlight.ui.fader.SpotlightFader
import com.bumble.appyx.navigation.composable.AppyxNavigationContainer
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.Node
import com.squidat.streamyx.components.StreamyxToolbar
import com.squidat.streamyx.nodes.feed.FeedNode
import kotlinx.parcelize.Parcelize

class RootNode(
    nodeContext: NodeContext,
    private val navigationComponent: Spotlight<RootNavigation> = Spotlight(
        model = SpotlightModel(
            items = listOf(RootNavigation.Feed),
            savedStateMap = nodeContext.savedStateMap,
        ),
        visualisation = { SpotlightFader(it) },
    ),
) : Node<RootNavigation>(
    nodeContext = nodeContext,
    appyxComponent = navigationComponent,
) {

    override fun buildChildNode(navTarget: RootNavigation, nodeContext: NodeContext): Node<*> {
        return when (navTarget) {
            RootNavigation.Feed -> FeedNode(nodeContext)
        }
    }

    @Composable
    override fun Content(modifier: Modifier) {
        Column(modifier = modifier) {
            StreamyxToolbar(modifier = Modifier.fillMaxWidth())
            AppyxNavigationContainer(navigationComponent)
        }
    }
}

sealed interface RootNavigation : Parcelable {

    @Parcelize
    data object Feed : RootNavigation
}