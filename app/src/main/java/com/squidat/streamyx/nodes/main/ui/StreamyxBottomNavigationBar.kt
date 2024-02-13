package com.squidat.streamyx.nodes.main.ui

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
import com.squidat.streamyx.nodes.main.MainNavigation

@Composable
fun StreamyxBottomNavigationBar(
    modifier: Modifier = Modifier,
    onTabSelected: (MainNavigation) -> Unit,
) {
    var selectedTab: MainNavigation by remember { mutableStateOf(MainNavigation.Feed) }

    BottomAppBar(modifier = modifier) {
        BottomNavigationItem(
            selected = selectedTab == MainNavigation.Feed,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = MainNavigation.Feed
                onTabSelected(MainNavigation.Feed)
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
            selected = selectedTab == MainNavigation.Inbox,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = MainNavigation.Inbox
                onTabSelected(MainNavigation.Inbox)
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
            selected = selectedTab == MainNavigation.Favorites,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = MainNavigation.Favorites
                onTabSelected(MainNavigation.Favorites)
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
            selected = selectedTab == MainNavigation.You,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = Color.DarkGray,
            onClick = {
                selectedTab = MainNavigation.You
                onTabSelected(MainNavigation.You)
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

