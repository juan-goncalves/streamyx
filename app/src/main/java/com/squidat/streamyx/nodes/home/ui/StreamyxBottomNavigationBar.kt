package com.squidat.streamyx.nodes.home.ui

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.squidat.streamyx.nodes.home.HomeNavigation

@Composable
fun StreamyxBottomNavigationBar(
    modifier: Modifier = Modifier,
    onTabSelected: (HomeNavigation) -> Unit,
) {
    var selectedTab: HomeNavigation by remember { mutableStateOf(HomeNavigation.Feed) }

    BottomAppBar(modifier = modifier) {
        BottomNavigationItem(
            selected = selectedTab == HomeNavigation.Feed,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = HomeNavigation.Feed
                onTabSelected(HomeNavigation.Feed)
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
            selected = selectedTab == HomeNavigation.Inbox,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = HomeNavigation.Inbox
                onTabSelected(HomeNavigation.Inbox)
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
            selected = selectedTab == HomeNavigation.Favorites,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = HomeNavigation.Favorites
                onTabSelected(HomeNavigation.Favorites)
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
            selected = selectedTab == HomeNavigation.You,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = HomeNavigation.You
                onTabSelected(HomeNavigation.You)
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

