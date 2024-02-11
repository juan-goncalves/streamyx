package com.squidat.streamyx.nodes.video_player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.LeafNode
import com.squidat.streamyx.components.Comment
import com.squidat.streamyx.components.VideoDetails
import com.squidat.streamyx.components.VideoPlayer
import com.squidat.streamyx.data.Videos
import com.squidat.streamyx.models.Video
import com.squidat.streamyx.ui.theme.StreamyxTheme

class VideoPlayerNode(
    nodeContext: NodeContext,
    private val video: Video,
) : LeafNode(
    nodeContext = nodeContext,
) {

    @Composable
    override fun Content(modifier: Modifier) {
        VideoPlayerScreen(
            modifier = modifier,
            video = video,
        )
    }
}

@Composable
fun VideoPlayerScreen(
    modifier: Modifier,
    video: Video,
) {
    Surface(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            VideoPlayer(modifier = Modifier.weight(0.5f))
            VideoDetails(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                videoTitle = video.title,
                channelName = video.postedBy.name,
                views = video.views,
                postedAt = video.postedAt,
            )
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp),
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = "Comments",
                style = MaterialTheme.typography.subtitle2,
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                items(video.comments) { comment ->
                    Comment(modifier = Modifier.fillMaxWidth(), comment = comment)
                    Spacer(Modifier.size(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun VideoPlayerScreenPreview() {
    StreamyxTheme {
        VideoPlayerScreen(
            modifier = Modifier.fillMaxSize(),
            video = Videos.first(),
        )
    }
}
