package com.squidat.streamyx.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
@Immutable
data class Video(
    val title: String,
    val views: Long,
    val postedAt: LocalDateTime,
    val author: Channel,
    val comments: List<Comment> = emptyList(),
) : Parcelable