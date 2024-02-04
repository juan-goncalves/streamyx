package com.squidat.streamyx.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.squidat.streamyx.ui.theme.StreamyxTheme
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun VideoPreview(
    modifier: Modifier = Modifier,
    videoTitle: String,
    channelName: String,
    views: Long,
    postedAt: LocalDateTime,
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Yellow),
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

@Composable
private fun VideoDetails(
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
                .background(Color.Yellow),
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

private val LocalDateTime.elapsedHours: Long
    get() = ChronoUnit.HOURS.between(this, LocalDateTime.now())

fun Long.toViewCount(): String {
    return when {
        this < 1_000 -> this.toString()
        this < 1_000_000 -> String.format("%.1fk", this / 1_000.0)
        else -> String.format("%.1fM", this / 1_000_000.0)
    }.replace(".0", "")
}

fun Long.toRelativeTime(): String = when {
    this < 24 -> "${this}h ago"
    this < 168 -> "${this / 24} day(s) ago" // Less than 7 days
    this < 720 -> "${this / 168} week(s) ago" // Less than 30 days
    else -> "${this / 720} month(s) ago" // More than 30 days
}


@Preview
@Composable
private fun VideoPreviewPreview() {
    StreamyxTheme {
        VideoPreview(
            videoTitle = "What can $1.000 get in INDIA !?",
            channelName = "G2 Sports",
            views = 3_543_000L,
            postedAt = LocalDateTime.now().plusHours(10),
        )
    }
}