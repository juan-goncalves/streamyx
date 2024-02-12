package com.squidat.streamyx.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.squidat.streamyx.ui.theme.StreamyxTheme

@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
) {
    var isPlaying by remember { mutableStateOf(false) }

    Box(modifier.background(Color.Black)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(
                onClick = {
                    isPlaying = !isPlaying
                },
                content = {
                    if (isPlaying) {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Filled.Pause,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    } else {
                        Icon(
                            modifier = Modifier.size(80.dp),
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun VideoPlayerPreview() {
    StreamyxTheme {
        VideoPlayer(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
        )
    }
}