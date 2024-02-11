package com.squidat.streamyx.mininimize_component

import com.bumble.appyx.interactions.core.ui.property.impl.Alpha
import com.bumble.appyx.interactions.core.ui.property.impl.Height
import com.bumble.appyx.interactions.core.ui.property.impl.RoundedCorners
import com.bumble.appyx.interactions.core.ui.property.impl.Scale
import com.bumble.appyx.interactions.core.ui.property.impl.Width
import com.bumble.appyx.interactions.core.ui.property.impl.ZIndex
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment.InsideAlignment.Companion.BottomCenter
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment.InsideAlignment.Companion.BottomStart
import com.bumble.appyx.interactions.core.ui.property.impl.position.PositionAlignment
import com.bumble.appyx.interactions.core.ui.property.impl.position.PositionOffset
import com.bumble.appyx.interactions.core.ui.state.MutableUiStateSpecs

@MutableUiStateSpecs
data class TargetUiState(
    val height: Height.Target,
    val width: Width.Target,
    val alpha: Alpha.Target,
    val roundedCorners: RoundedCorners.Target,
    val zIndex: ZIndex.Target,
    val alignment: PositionAlignment.Target = PositionAlignment.Target(insideAlignment = BottomCenter),
)
