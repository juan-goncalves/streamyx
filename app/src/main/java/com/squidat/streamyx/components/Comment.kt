package com.squidat.streamyx.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.squidat.streamyx.data.Users
import com.squidat.streamyx.data.elapsedHours
import com.squidat.streamyx.data.toRelativeTime
import com.squidat.streamyx.data.toViewCount
import com.squidat.streamyx.models.Comment
import com.squidat.streamyx.models.User
import com.squidat.streamyx.ui.theme.LightAmber
import com.squidat.streamyx.ui.theme.StreamyxTheme
import com.squidat.streamyx.ui.theme.VeryLightAmber
import com.squidat.streamyx.ui.theme.VeryLightGrey
import java.time.LocalDateTime

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment,
) {
    Comment(
        modifier = modifier,
        author = comment.author,
        likes = comment.likes,
        comment = comment.content,
        postedAt = comment.postedAt,
    )
}

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    author: User,
    likes: Long,
    comment: String,
    postedAt: LocalDateTime,
) {
    val likeAmount = remember(likes) { likes.toViewCount() }
    val relativePostingTime = remember(postedAt) { postedAt.elapsedHours.toRelativeTime() }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = VeryLightGrey),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
            )

            Spacer(Modifier.size(8.dp))
            Column {
                Text(
                    text = "${author.username} · $relativePostingTime",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = comment,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.size(8.dp))
                Row {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Outlined.ThumbUp,
                        contentDescription = null,
                    )
                    Spacer(Modifier.size(4.dp))
                    Text(
                        text = likeAmount,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CommentPreview() {
    StreamyxTheme {
        Comment(
            modifier = Modifier.fillMaxWidth(),
            author = Users.first(),
            likes = 1_532,
            comment = "Absolutely loved this video! The creativity and effort put into it really shine through. It's rare to find content that's both informative and entertaining in such a perfect balance.",
            postedAt = LocalDateTime.now(),
        )
    }
}