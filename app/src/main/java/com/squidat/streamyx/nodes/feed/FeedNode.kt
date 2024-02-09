package com.squidat.streamyx.nodes.feed

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navigation.collections.ImmutableList
import com.bumble.appyx.navigation.modality.NodeContext
import com.bumble.appyx.navigation.node.LeafNode
import com.squidat.streamyx.components.VideoPreview
import com.squidat.streamyx.data.Videos
import com.squidat.streamyx.models.Video
import com.squidat.streamyx.ui.theme.StreamyxTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FeedNode(nodeContext: NodeContext) : LeafNode(nodeContext) {

    private val _output: MutableSharedFlow<Output> = MutableSharedFlow()
    val output: Flow<Output> = _output

    sealed interface Output {
        data class VideoSelected(val video: Video) : Output
    }

    @Composable
    override fun Content(modifier: Modifier) {
        Feed(
            modifier = modifier,
            videos = Videos,
            onVideoSelected = {
                lifecycleScope.launch {
                    _output.emit(Output.VideoSelected(it))
                }
            },
        )
    }
}

@Composable
private fun Feed(
    modifier: Modifier = Modifier,
    videos: ImmutableList<Video>,
    onVideoSelected: (Video) -> Unit,
) {
    Surface(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(videos) { video ->
                VideoPreview(
                    videoTitle = video.title,
                    channelName = video.postedBy.name,
                    views = video.views,
                    postedAt = video.postedAt,
                    onClick = {
                        onVideoSelected(video)
                    },
                )
                Spacer(Modifier.size(12.dp))
            }
        }
    }
}

@Preview
@Composable
private fun FeedPreview() {
    StreamyxTheme {
        Feed(
            modifier = Modifier.fillMaxSize(),
            videos = Videos,
            onVideoSelected = {},
        )
    }
}