package com.squidat.streamyx.nodes.video_player.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.squidat.streamyx.elapsedHours
import com.squidat.streamyx.toRelativeTime
import com.squidat.streamyx.toViewCount
import com.squidat.streamyx.ui.StreamyxTheme
import java.time.LocalDateTime

@Composable
fun VideoDetails(
    modifier: Modifier = Modifier,
    videoTitle: String,
    channelName: String,
    views: Long,
    postedAt: LocalDateTime,
) {
    val summarisedViews = remember(views) { views.toViewCount() }
    val relativePostingTime = remember(postedAt) { postedAt.elapsedHours.toRelativeTime() }

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
        )
        Spacer(Modifier.size(4.dp))
        Column {
            Text(
                text = videoTitle,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.size(2.dp))
            Row(modifier = Modifier) {
                Text(text = "$channelName ·", style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.size(4.dp))
                Text(text = "$summarisedViews views ·", style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.size(4.dp))
                Text(text = relativePostingTime, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview
@Composable
fun VideoDetailsPreview() {
    StreamyxTheme {
        VideoDetails(
            modifier = Modifier.fillMaxWidth(),
            videoTitle = "What can $1.000 get in INDIA !?",
            channelName = "G2 Sports",
            views = 3_543_000L,
            postedAt = LocalDateTime.now().minusHours(10),
        )
    }
}