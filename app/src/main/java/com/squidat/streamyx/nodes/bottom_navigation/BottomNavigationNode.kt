package com.squidat.streamyx.nodes.bottom_navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.LeafNode
import com.squidat.streamyx.nodes.bottom_navigation.BottomNavigationNode.Tab
import com.squidat.streamyx.ui.StreamyxTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class BottomNavigationNode(
    nodeContext: NodeContext,
) : LeafNode(nodeContext) {

    private val _output: MutableSharedFlow<Output> = MutableSharedFlow()
    val output: Flow<Output> = _output

    sealed interface Output {
        data class TabSelected(val selection: Tab) : Output
    }

    enum class Tab {
        Home,
        Inbox,
        Favorites,
        You,
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val coroutineScope = rememberCoroutineScope()

        StreamyxBottomNavigationBar(
            modifier = modifier,
            onTabSelected = { tab ->
                coroutineScope.launch {
                    _output.emit(Output.TabSelected(tab))
                }
            }
        )
    }
}

@Composable
private fun StreamyxBottomNavigationBar(
    modifier: Modifier = Modifier,
    onTabSelected: (Tab) -> Unit,
) {
    var selectedTab by remember { mutableStateOf(Tab.Home) }

    BottomAppBar(modifier = modifier) {
        BottomNavigationItem(
            selected = selectedTab == Tab.Home,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = Tab.Home
                onTabSelected(Tab.Home)
            },
            label = {
                Text(text = "Home", style = MaterialTheme.typography.labelMedium)
            },
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                )
            },
        )
        BottomNavigationItem(
            selected = selectedTab == Tab.Inbox,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = Tab.Inbox
                onTabSelected(Tab.Inbox)
            },
            label = {
                Text(text = "Inbox", style = MaterialTheme.typography.labelMedium)
            },
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                )
            },
        )
        BottomNavigationItem(
            selected = selectedTab == Tab.Favorites,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = Tab.Favorites
                onTabSelected(Tab.Favorites)
            },
            label = {
                Text(text = "Favorites", style = MaterialTheme.typography.labelMedium)
            },
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                )
            },
        )
        BottomNavigationItem(
            selected = selectedTab == Tab.You,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = Tab.You
                onTabSelected(Tab.You)
            },
            label = {
                Text(text = "You", style = MaterialTheme.typography.labelMedium)
            },
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                )
            },
        )
    }
}

@Preview
@Composable
private fun StreamyxBottomNavigationBarPreview() {
    StreamyxTheme {
        StreamyxBottomNavigationBar(
            modifier = Modifier.fillMaxWidth(),
            onTabSelected = {},
        )
    }
}