package com.squidat.streamyx.nodes.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface HomeNavigation : Parcelable {

    @Parcelize
    data object Feed : HomeNavigation

    @Parcelize
    data object Inbox : HomeNavigation

    @Parcelize
    data object Favorites : HomeNavigation

    @Parcelize
    data object You : HomeNavigation
}