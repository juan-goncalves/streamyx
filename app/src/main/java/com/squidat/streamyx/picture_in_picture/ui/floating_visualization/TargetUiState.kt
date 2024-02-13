package com.squidat.streamyx.picture_in_picture.ui.floating_visualization

import com.bumble.appyx.interactions.core.ui.property.impl.Alpha
import com.bumble.appyx.interactions.core.ui.property.impl.Height
import com.bumble.appyx.interactions.core.ui.property.impl.RoundedCorners
import com.bumble.appyx.interactions.core.ui.property.impl.Width
import com.bumble.appyx.interactions.core.ui.property.impl.ZIndex
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment.InsideAlignment.Companion.BottomCenter
import com.bumble.appyx.interactions.core.ui.property.impl.position.PositionAlignment
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

val Minimized = TargetUiState(
    height = Height.Target(0.1f),
    width = Width.Target(0.97f),
    alpha = Alpha.Target(1f),
    roundedCorners = RoundedCorners.Target(12),
    alignment = PositionAlignment.Target(
        insideAlignment = BiasAlignment.InsideAlignment.fractionAlignment(
            horizontalBiasFraction = 0.5f,
            verticalBiasFraction = 0.88f,
        )
    ),
    zIndex = ZIndex.Target(2f),
)

val Active = TargetUiState(
    height = Height.Target(1f),
    width = Width.Target(1f),
    alpha = Alpha.Target(1f),
    roundedCorners = RoundedCorners.Target(0),
    zIndex = ZIndex.Target(1f),
)

val Hidden = TargetUiState(
    height = Height.Target(1f),
    width = Width.Target(1f),
    alpha = Alpha.Target(0f),
    roundedCorners = RoundedCorners.Target(0),
    zIndex = ZIndex.Target(1f),
)

val Dismissed = TargetUiState(
    height = Height.Target(0.1f),
    width = Width.Target(0.97f),
    alpha = Alpha.Target(0f),
    roundedCorners = RoundedCorners.Target(12),
    alignment = PositionAlignment.Target(
        outsideAlignment = BiasAlignment.OutsideAlignment(
            horizontalBias = -1f,
            verticalBias = 0.79f,
        ),
    ),
    zIndex = ZIndex.Target(2f),
)
