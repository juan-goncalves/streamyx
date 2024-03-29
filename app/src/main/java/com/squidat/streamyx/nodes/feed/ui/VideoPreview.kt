package com.squidat.streamyx.nodes.feed.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.squidat.streamyx.nodes.video_player.ui.VideoDetails
import com.squidat.streamyx.ui.StreamyxTheme
import java.time.LocalDateTime

@Composable
fun VideoPreview(
    modifier: Modifier = Modifier,
    videoTitle: String,
    channelName: String,
    views: Long,
    postedAt: LocalDateTime,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.primary),
        )

        VideoDetails(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            videoTitle = videoTitle,
            channelName = channelName,
            views = views,
            postedAt = postedAt,
        )
    }
}

@Preview
@Composable
private fun VideoPreviewPreview() {
    StreamyxTheme {
        VideoPreview(
            videoTitle = "What can $1.000 get in INDIA !?",
            channelName = "G2 Sports",
            views = 3_543_000L,
            postedAt = LocalDateTime.now().minusHours(10),
            onClick = {},
        )
    }
}