package com.squidat.streamyx.models

import java.time.LocalDateTime

data class Video(
    val title: String,
    val views: Long,
    val postedAt: LocalDateTime,
    val postedBy: Channel
)