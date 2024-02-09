package com.squidat.streamyx.data

import com.bumble.appyx.navigation.collections.immutableListOf
import com.squidat.streamyx.models.Channel
import com.squidat.streamyx.models.Comment
import com.squidat.streamyx.models.User
import com.squidat.streamyx.models.Video
import java.time.LocalDateTime

val Videos = immutableListOf(
    Video(
        title = "New Year's Tech Resolutions",
        views = 300_000L,
        postedAt = LocalDateTime.of(2024, 1, 1, 10, 0),
        postedBy = Channel("Tech Daily"),
        comments = immutableListOf(
            Comment(
                author = User("@TechGuru1"),
                content = "Incredible insights for the new year!",
                likes = 1023,
                postedAt = LocalDateTime.of(2024, 1, 1, 11, 15)
            ),
            Comment(
                author = User("@InnovatorMike"),
                content = "Can't wait to implement these.",
                likes = 875,
                postedAt = LocalDateTime.of(2024, 1, 1, 12, 30)
            ),
            Comment(
                author = User("@DigitalNomad"),
                content = "This is exactly what I was looking for!",
                likes = 940,
                postedAt = LocalDateTime.of(2024, 1, 1, 13, 45)
            ),
            Comment(
                author = User("@FutureVisionary"),
                content = "Excited for what tech will bring in 2024!",
                likes = 789,
                postedAt = LocalDateTime.of(2024, 1, 1, 14, 0)
            ),
            Comment(
                author = User("@CodeMaster"),
                content = "Time to dive deeper into coding.",
                likes = 657,
                postedAt = LocalDateTime.of(2024, 1, 1, 15, 30)
            ),
            Comment(
                author = User("@DesignThinker"),
                content = "Hoping to see more on UX trends!",
                likes = 832,
                postedAt = LocalDateTime.of(2024, 1, 1, 16, 45)
            ),
            Comment(
                author = User("@AgileAdventurer"),
                content = "Agile is the way to go, great mention.",
                likes = 921,
                postedAt = LocalDateTime.of(2024, 1, 1, 17, 0)
            ),
            Comment(
                author = User("@TechTrendsetter"),
                content = "Setting the trends for 2024!",
                likes = 1100,
                postedAt = LocalDateTime.of(2024, 1, 1, 18, 30)
            ),
            Comment(
                author = User("@InnovationInsider"),
                content = "Innovation is key, well said!",
                likes = 1205,
                postedAt = LocalDateTime.of(2024, 1, 1, 19, 45)
            ),
            Comment(
                author = User("@CloudComputingChamp"),
                content = "Cloud computing is my 2024 focus too.",
                likes = 1322,
                postedAt = LocalDateTime.of(2024, 1, 1, 20, 0)
            )
        )
    ),
    Video(
        title = "Ultimate Guide to Android Development",
        views = 250_000L,
        postedAt = LocalDateTime.of(2024, 1, 2, 9, 0),
        postedBy = Channel("Dev Essentials"),
        comments = immutableListOf(
            Comment(
                author = User("@AppWizard"),
                content = "This guide is everything I needed!",
                likes = 987,
                postedAt = LocalDateTime.of(2024, 1, 2, 10, 30)
            ),
            Comment(
                author = User("@KotlinKing"),
                content = "Kotlin is love, Kotlin is life.",
                likes = 854,
                postedAt = LocalDateTime.of(2024, 1, 2, 11, 0)
            ),
            Comment(
                author = User("@JavaJunkie"),
                content = "Great to see Java getting some love too.",
                likes = 765,
                postedAt = LocalDateTime.of(2024, 1, 2, 12, 30)
            ),
            Comment(
                author = User("@UIUXUnicorn"),
                content = "The UI/UX tips are gold!",
                likes = 1120,
                postedAt = LocalDateTime.of(2024, 1, 2, 13, 0)
            ),
            Comment(
                author = User("@BugHunter"),
                content = "Debugging tips are super helpful.",
                likes = 999,
                postedAt = LocalDateTime.of(2024, 1, 2, 14, 30)
            ),
            Comment(
                author = User("@MobileMogul"),
                content = "Mobile development is indeed the future.",
                likes = 1203,
                postedAt = LocalDateTime.of(2024, 1, 2, 15, 0)
            ),
            Comment(
                author = User("@StartupSavvy"),
                content = "Perfect guide for my startup project.",
                likes = 1001,
                postedAt = LocalDateTime.of(2024, 1, 2, 16, 30)
            ),
            Comment(
                author = User("@TechTrailblazer"),
                content = "Blazing through the Android development!",
                likes = 876,
                postedAt = LocalDateTime.of(2024, 1, 2, 17, 0)
            ),
            Comment(
                author = User("@InnovativeDeveloper"),
                content = "Loved the innovative approach here.",
                likes = 945,
                postedAt = LocalDateTime.of(2024, 1, 2, 18, 30)
            ),
            Comment(
                author = User("@DevOpsDynamo"),
                content = "App deployment section was insightful.",
                likes = 788,
                postedAt = LocalDateTime.of(2024, 1, 2, 19, 0)
            )
        )
    ),
    Video(
        title = "The Future of Virtual Reality",
        views = 180_000L,
        postedAt = LocalDateTime.of(2024, 1, 3, 11, 0),
        postedBy = Channel("VR Visions"),
        comments = immutableListOf(
            Comment(
                author = User("@VREnthusiast"),
                content = "VR is going to change the world!",
                likes = 1050,
                postedAt = LocalDateTime.of(2024, 1, 3, 12, 15)
            ),
            Comment(
                author = User("@TechFuturist"),
                content = "The possibilities are endless with VR.",
                likes = 995,
                postedAt = LocalDateTime.of(2024, 1, 3, 13, 30)
            ),
            Comment(
                author = User("@GamerGal"),
                content = "Can't wait for more immersive games.",
                likes = 1103,
                postedAt = LocalDateTime.of(2024, 1, 3, 14, 45)
            ),
            Comment(
                author = User("@SciFiFan"),
                content = "Feels like we're living in a sci-fi novel!",
                likes = 1207,
                postedAt = LocalDateTime.of(2024, 1, 3, 15, 0)
            ),
            Comment(
                author = User("@InnovationAddict"),
                content = "VR in education would be revolutionary.",
                likes = 1328,
                postedAt = LocalDateTime.of(2024, 1, 3, 16, 30)
            ),
            Comment(
                author = User("@DesignDiva"),
                content = "Imagine the design possibilities!",
                likes = 987,
                postedAt = LocalDateTime.of(2024, 1, 3, 17, 45)
            ),
            Comment(
                author = User("@RealityExplorer"),
                content = "Exploring new realities every day.",
                likes = 865,
                postedAt = LocalDateTime.of(2024, 1, 3, 18, 0)
            ),
            Comment(
                author = User("@TechVisionary"),
                content = "VR is the future of technology.",
                likes = 943,
                postedAt = LocalDateTime.of(2024, 1, 3, 19, 30)
            ),
            Comment(
                author = User("@DigitalDreamer"),
                content = "Dreaming of digital worlds.",
                likes = 756,
                postedAt = LocalDateTime.of(2024, 1, 3, 20, 0)
            ),
            Comment(
                author = User("@VirtualVoyager"),
                content = "Voyaging into virtuality.",
                likes = 834,
                postedAt = LocalDateTime.of(2024, 1, 3, 21, 15)
            )
        )
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