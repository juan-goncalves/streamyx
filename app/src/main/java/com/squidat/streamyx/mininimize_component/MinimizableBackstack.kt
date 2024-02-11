package com.squidat.streamyx.mininimize_component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.bumble.appyx.interactions.core.model.BaseAppyxComponent
import com.bumble.appyx.interactions.core.model.backpresshandlerstrategies.BackPressHandlerStrategy
import com.bumble.appyx.interactions.core.ui.DefaultAnimationSpec
import com.bumble.appyx.interactions.core.ui.Visualisation
import com.bumble.appyx.interactions.core.ui.context.UiContext
import com.bumble.appyx.interactions.core.ui.property.impl.Alpha
import com.bumble.appyx.interactions.core.ui.property.impl.Height
import com.bumble.appyx.interactions.core.ui.property.impl.RoundedCorners
import com.bumble.appyx.interactions.core.ui.property.impl.Width
import com.bumble.appyx.interactions.core.ui.property.impl.ZIndex
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment.OutsideAlignment.Companion.OutsideTop
import com.bumble.appyx.interactions.core.ui.property.impl.position.PositionAlignment
import com.bumble.appyx.interactions.core.ui.property.impl.position.PositionOffset
import com.bumble.appyx.interactions.core.ui.state.MatchedTargetUiState
import com.bumble.appyx.transitionmodel.BaseVisualisation
import com.squidat.streamyx.mininimize_component.backpresshandler.MinimizeOnBackPressStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MinimizableBackstack<InteractionTarget : Any>(
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
    model: MinimizableBackstackModel<InteractionTarget>,
    visualisation: (UiContext) -> Visualisation<InteractionTarget, MinimizableBackstackModel.State<InteractionTarget>>,
    progressAnimationSpec: AnimationSpec<Float> = tween(durationMillis = 1_000),
    backPressStrategy: BackPressHandlerStrategy<InteractionTarget, MinimizableBackstackModel.State<InteractionTarget>> = MinimizeOnBackPressStrategy(
        scope
    ),
    animateSettle: Boolean = false
) : BaseAppyxComponent<InteractionTarget, MinimizableBackstackModel.State<InteractionTarget>>(
    scope = scope,
    model = model,
    visualisation = visualisation,
    defaultAnimationSpec = progressAnimationSpec,
    animateSettle = animateSettle,
    backPressStrategy = backPressStrategy,
)

class DefaultVisualisation<InteractionTarget : Any>(
    uiContext: UiContext,
    defaultAnimationSpec: SpringSpec<Float> = DefaultAnimationSpec
) : BaseVisualisation<InteractionTarget, MinimizableBackstackModel.State<InteractionTarget>, TargetUiState, MutableUiState>(
    uiContext = uiContext,
    defaultAnimationSpec = defaultAnimationSpec,
) {

    private val minimized = TargetUiState(
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

    private val active = TargetUiState(
        height = Height.Target(1f),
        width = Width.Target(1f),
        alpha = Alpha.Target(1f),
        roundedCorners = RoundedCorners.Target(0),
        zIndex = ZIndex.Target(1f),
    )

    private val hidden = TargetUiState(
        height = Height.Target(1f),
        width = Width.Target(1f),
        alpha = Alpha.Target(0f),
        roundedCorners = RoundedCorners.Target(0),
        zIndex = ZIndex.Target(1f),
    )

    override fun MinimizableBackstackModel.State<InteractionTarget>.toUiTargets(): List<MatchedTargetUiState<InteractionTarget, TargetUiState>> =
        listOfNotNull(
            matchMaximizedTargetUiState(),
            matchMinimizedTargetUiState(),
            *matchHiddenTargets(),
        )

    private fun MinimizableBackstackModel.State<InteractionTarget>.matchMaximizedTargetUiState(): MatchedTargetUiState<InteractionTarget, TargetUiState> {
        return MatchedTargetUiState(element = activeItem, targetUiState = active)
    }

    private fun MinimizableBackstackModel.State<InteractionTarget>.matchMinimizedTargetUiState(): MatchedTargetUiState<InteractionTarget, TargetUiState>? {
        return if (minimizedItem != null) MatchedTargetUiState(
            element = minimizedItem,
            targetUiState = minimized
        ) else null
    }

    private fun MinimizableBackstackModel.State<InteractionTarget>.matchHiddenTargets(): Array<MatchedTargetUiState<InteractionTarget, TargetUiState>> {
        return (created + stashed + destroyed)
            .map { element -> MatchedTargetUiState(element, hidden) }
            .toTypedArray()
    }

    override fun mutableUiStateFor(
        uiContext: UiContext,
        targetUiState: TargetUiState
    ): MutableUiState = targetUiState.toMutableUiState(uiContext)
}
