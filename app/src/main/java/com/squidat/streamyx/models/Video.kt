package com.squidat.streamyx.models

import android.os.Parcelable
import com.bumble.appyx.navigation.collections.immutableListOf
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Video(
    val title: String,
    val views: Long,
    val postedAt: LocalDateTime,
    val postedBy: Channel,
    val comments: List<Comment> = immutableListOf(),
) : Parcelable