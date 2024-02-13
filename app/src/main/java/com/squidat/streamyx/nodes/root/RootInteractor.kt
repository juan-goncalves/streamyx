package com.squidat.streamyx.nodes.root

import com.bumble.appyx.navigation.clienthelper.interactor.Interactor
import com.bumble.appyx.navigation.lifecycle.Lifecycle
import com.squidat.streamyx.nodes.main.MainNode
import com.squidat.streamyx.nodes.video_player.VideoPlayerNode
import com.squidat.streamyx.picture_in_picture.PictureInPicture
import com.squidat.streamyx.picture_in_picture.operation.dismiss
import com.squidat.streamyx.picture_in_picture.operation.open
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RootInteractor(
    private val pip: PictureInPicture<RootNavigation>,
) : Interactor<RootNode>() {


    override fun onCreate(lifecycle: Lifecycle) {
        whenChildAttached(MainNode::class) { commonLifecycle, child ->
            child.collectOutputIn(commonLifecycle)
        }

        whenChildAttached(VideoPlayerNode::class) { commonLifecycle, child ->
            child.collectOutputIn(commonLifecycle)
        }
    }

    private fun MainNode.collectOutputIn(lifecycle: Lifecycle) {
        lifecycle.coroutineScope.launch {
            output.collectLatest { output ->
                when (output) {
                    is MainNode.Output.VideoSelected -> {
                        pip.open(RootNavigation.VideoPlayer(output.video))
                    }
                }
            }
        }
    }

    private fun VideoPlayerNode.collectOutputIn(lifecycle: Lifecycle) {
        lifecycle.coroutineScope.launch {
            output.collectLatest { output ->
                when (output) {
                    is VideoPlayerNode.Output.MaximizeSelected -> pip.open(
                        RootNavigation.VideoPlayer(
                            output.video
                        )
                    )

                    is VideoPlayerNode.Output.DismissSelected -> pip.dismiss()
                }
            }
        }
    }
}