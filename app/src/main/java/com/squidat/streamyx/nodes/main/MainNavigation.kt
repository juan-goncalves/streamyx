package com.squidat.streamyx.nodes.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface MainNavigation : Parcelable {

    @Parcelize
    data object Feed : MainNavigation

    @Parcelize
    data object Inbox : MainNavigation

    @Parcelize
    data object Favorites : MainNavigation

    @Parcelize
    data object You : MainNavigation
}