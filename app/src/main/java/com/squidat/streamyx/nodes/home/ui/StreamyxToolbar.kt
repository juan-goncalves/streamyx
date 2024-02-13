package com.squidat.streamyx.nodes.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.squidat.streamyx.R
import com.squidat.streamyx.ui.StreamyxTheme


@Composable
fun StreamyxToolbar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Logo()
        Actions()
    }
}

@Composable
private fun Logo() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.streamyx_icon),
            contentDescription = null,
        )
        Spacer(Modifier.size(4.dp))
        Text(
            text = "streamyx",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun Actions() {
    Row {
        IconButton(
            onClick = {},
            content = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        )
        Spacer(Modifier.size(4.dp))
        IconButton(
            onClick = {},
            content = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        )
    }
}

@Preview
@Composable
private fun StreamyxToolbarPreview() {
    StreamyxTheme {
        StreamyxToolbar(modifier = Modifier.fillMaxWidth())
    }
}