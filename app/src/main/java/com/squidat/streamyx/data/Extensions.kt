package com.squidat.streamyx.data

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

val LocalDateTime.elapsedHours: Long
    get() = ChronoUnit.HOURS.between(this, LocalDateTime.now())

fun Long.toViewCount(): String {
    return when {
        this < 1_000 -> this.toString()
        this < 1_000_000 -> String.format("%.1fk", this / 1_000.0)
        else -> String.format("%.1fM", this / 1_000_000.0)
    }.replace(".0", "")
}

fun Long.toRelativeTime(): String = when {
    this < 24 -> "${this}h ago"
    this < 168 -> "${this / 24} day(s) ago" // Less than 7 days
    this < 720 -> "${this / 168} week(s) ago" // Less than 30 days
    else -> "${this / 720} month(s) ago" // More than 30 days
}