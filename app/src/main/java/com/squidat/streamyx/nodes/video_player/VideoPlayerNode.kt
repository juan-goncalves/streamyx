package com.squidat.streamyx.nodes.video_player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.LeafNode
import com.squidat.streamyx.components.Comment
import com.squidat.streamyx.components.VideoDetails
import com.squidat.streamyx.components.VideoPlayer
import com.squidat.streamyx.data.Videos
import com.squidat.streamyx.models.Video
import com.squidat.streamyx.ui.theme.StreamyxTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class VideoPlayerNode(
    nodeContext: NodeContext,
    private val video: Video,
) : LeafNode(
    nodeContext = nodeContext,
) {

    private val _output: MutableSharedFlow<Output> = MutableSharedFlow()
    val output: Flow<Output> = _output

    sealed interface Output {
        data object MaximizeSelected : Output
    }

    @Composable
    override fun Content(modifier: Modifier) {
        ResponsiveVideoPlayer(
            modifier = modifier,
            video = video,
            onMaximizeClicked = {
                lifecycleScope.launch {
                    _output.emit(Output.MaximizeSelected)
                }
            }
        )
    }
}

@Composable
fun LargeVideoPlayer(
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

@Composable
fun SmallVideoPlayer(
    modifier: Modifier,
    video: Video,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier.clickable { onClick() },
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight()
                    .background(Color.Black)
            )
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .padding(8.dp)
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.size(2.dp))
                Text(
                    text = video.postedBy.name,
                    style = MaterialTheme.typography.body2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {},
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface,
                        )
                    }
                )
                IconButton(
                    onClick = {},
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface,
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun ResponsiveVideoPlayer(
    modifier: Modifier,
    video: Video,
    onMaximizeClicked: () -> Unit,
) {
    var sizePx by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    val height = remember(sizePx) {
        with(density) { sizePx.height.toDp() }
    }

    Surface(
        modifier = modifier.onSizeChanged { sizePx = it },
    ) {
        if (height > 300.dp) {
            LargeVideoPlayer(modifier = Modifier.fillMaxSize(), video = video)
        } else {
            SmallVideoPlayer(
                modifier = Modifier.fillMaxSize(),
                video = video,
                onClick = onMaximizeClicked,
            )
        }
    }
}


@Preview
@Composable
fun VideoPlayerScreenPreview() {
    StreamyxTheme {
        LargeVideoPlayer(
            modifier = Modifier.fillMaxSize(),
            video = Videos.first(),
        )
    }
}

@Preview
@Composable
fun SmallVideoPlayerScreenPreview() {
    StreamyxTheme {
        SmallVideoPlayer(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            video = Videos.first(),
            onClick = {},
        )
    }
}
