package com.squidat.streamyx.nodes.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface MainNavigation : Parcelable {

    sealed interface Content : MainNavigation {
        @Parcelize
        data object Feed : Content

        @Parcelize
        data object Inbox : Content

        @Parcelize
        data object Favorites : Content

        @Parcelize
        data object You : Content
    }

    sealed interface Permanent : MainNavigation {
        @Parcelize
        data object NavigationBar : Permanent
    }
}