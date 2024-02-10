package com.squidat.streamyx.mininimize_component

import com.bumble.appyx.interactions.core.ui.property.impl.Scale
import com.bumble.appyx.interactions.core.ui.property.impl.ZIndex
import com.bumble.appyx.interactions.core.ui.state.MutableUiStateSpecs

@MutableUiStateSpecs
data class TargetUiState(
    val verticalScale: Scale.Target,
    val zIndex: ZIndex.Target,
)
