package com.squidat.streamyx.nodes.root

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface RootNavigation : Parcelable {

    sealed interface Content : RootNavigation {
        @Parcelize
        data object Feed : Content

        @Parcelize
        data object Inbox : Content

        @Parcelize
        data object Favorites : Content

        @Parcelize
        data object You : Content
    }

    sealed interface Permanent : RootNavigation {
        @Parcelize
        data object NavigationBar : Permanent
    }
}