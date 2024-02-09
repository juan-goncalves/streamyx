package com.squidat.streamyx.nodes.root

import android.os.Parcelable
import com.squidat.streamyx.models.Video
import kotlinx.parcelize.Parcelize

sealed interface RootNavigation : Parcelable {

    @Parcelize
    data object Main : RootNavigation

    @Parcelize
    data class VideoPlayer(val video: Video) : RootNavigation
}