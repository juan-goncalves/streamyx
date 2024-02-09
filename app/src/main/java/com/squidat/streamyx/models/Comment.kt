package com.squidat.streamyx.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Comment(
    val author: User,
    val content: String,
    val likes: Long,
    val postedAt: LocalDateTime,
) : Parcelable
