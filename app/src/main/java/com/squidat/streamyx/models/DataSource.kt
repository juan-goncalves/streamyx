package com.squidat.streamyx.models

import com.bumble.appyx.navigation.collections.immutableListOf
import java.time.LocalDateTime

val Videos = immutableListOf(
    Video(
        title = "New Year's Tech Resolutions",
        views = 300_000L,
        postedAt = LocalDateTime.of(2024, 1, 1, 10, 0),
        postedBy = Channel("Tech Daily")
    ),
    Video(
        title = "Winter Wonderland: The Best Cold-Weather Sports",
        views = 120_000L,
        postedAt = LocalDateTime.of(2024, 1, 2, 14, 30),
        postedBy = Channel("Adventure Sports")
    ),
    Video(
        title = "The Ultimate Guide to Indoor Gardening",
        views = 250_000L,
        postedAt = LocalDateTime.of(2024, 1, 5, 16, 45),
        postedBy = Channel("Green Thumb")
    ),
    Video(
        title = "Mastering Kotlin for Android in 2024",
        views = 500_000L,
        postedAt = LocalDateTime.of(2024, 1, 7, 9, 20),
        postedBy = Channel("Code Sphere")
    ),
    Video(
        title = "Exploring the Hidden Gems of Paris",
        views = 400_000L,
        postedAt = LocalDateTime.of(2024, 1, 10, 18, 0),
        postedBy = Channel("Travel Vistas")
    ),
    Video(
        title = "What's New in Tech: January 2024 Roundup",
        views = 600_000L,
        postedAt = LocalDateTime.of(2024, 1, 15, 11, 30),
        postedBy = Channel("InnoTech Today")
    ),
    Video(
        title = "Veganuary: Trying Vegan for a Month",
        views = 320_000L,
        postedAt = LocalDateTime.of(2024, 1, 18, 12, 45),
        postedBy = Channel("Healthy Eats")
    ),
    Video(
        title = "The Rise of E-Sports: 2024 Outlook",
        views = 450_000L,
        postedAt = LocalDateTime.of(2024, 1, 20, 15, 15),
        postedBy = Channel("E-Sports Central")
    ),
    Video(
        title = "How to Stay Productive During Winter",
        views = 280_000L,
        postedAt = LocalDateTime.of(2024, 1, 23, 10, 10),
        postedBy = Channel("Productivity Guru")
    ),
    Video(
        title = "The Best Winter Photography Tips",
        views = 350_000L,
        postedAt = LocalDateTime.of(2024, 1, 25, 17, 30),
        postedBy = Channel("PhotoCraft")
    ),
    Video(
        title = "Cold Weather Survival Skills",
        views = 220_000L,
        postedAt = LocalDateTime.of(2024, 1, 28, 14, 20),
        postedBy = Channel("Survivalist")
    ),
    Video(
        title = "DIY Home Projects for the New Year",
        views = 310_000L,
        postedAt = LocalDateTime.of(2024, 1, 30, 9, 50),
        postedBy = Channel("Home Hacks")
    ),
    Video(
        title = "Understanding Cryptocurrency: 2024 Trends",
        views = 530_000L,
        postedAt = LocalDateTime.of(2024, 2, 1, 12, 0),
        postedBy = Channel("Crypto Insights")
    ),
    Video(
        title = "Sustainable Living: Easy Tips for Beginners",
        views = 290_000L,
        postedAt = LocalDateTime.of(2024, 2, 3, 18, 30),
        postedBy = Channel("EcoLife")
    ),
    Video(
        title = "Exploring the Power of AI in Everyday Life",
        views = 410_000L,
        postedAt = LocalDateTime.of(2024, 2, 4, 10, 15),
        postedBy = Channel("Tech Tomorrow")
    ),
)