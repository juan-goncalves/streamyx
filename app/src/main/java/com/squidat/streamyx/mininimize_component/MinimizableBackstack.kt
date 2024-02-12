package com.squidat.streamyx.mininimize_component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import com.bumble.appyx.interactions.core.model.BaseAppyxComponent
import com.bumble.appyx.interactions.core.model.backpresshandlerstrategies.BackPressHandlerStrategy
import com.bumble.appyx.interactions.core.ui.DefaultAnimationSpec
import com.bumble.appyx.interactions.core.ui.Visualisation
import com.bumble.appyx.interactions.core.ui.context.TransitionBounds
import com.bumble.appyx.interactions.core.ui.context.UiContext
import com.bumble.appyx.interactions.core.ui.gesture.Drag.Direction8.DOWN
import com.bumble.appyx.interactions.core.ui.gesture.Drag.Direction8.UP
import com.bumble.appyx.interactions.core.ui.gesture.Gesture
import com.bumble.appyx.interactions.core.ui.gesture.GestureFactory
import com.bumble.appyx.interactions.core.ui.gesture.GestureSettleConfig
import com.bumble.appyx.interactions.core.ui.gesture.dragDirection8
import com.bumble.appyx.interactions.core.ui.property.impl.Alpha
import com.bumble.appyx.interactions.core.ui.property.impl.Height
import com.bumble.appyx.interactions.core.ui.property.impl.RoundedCorners
import com.bumble.appyx.interactions.core.ui.property.impl.Width
import com.bumble.appyx.interactions.core.ui.property.impl.ZIndex
import com.bumble.appyx.interactions.core.ui.property.impl.position.BiasAlignment
import com.bumble.appyx.interactions.core.ui.property.impl.position.PositionAlignment
import com.bumble.appyx.interactions.core.ui.state.MatchedTargetUiState
import com.bumble.appyx.transitionmodel.BaseVisualisation
import com.squidat.streamyx.mininimize_component.MinimizableBackstackModel.State
import com.squidat.streamyx.mininimize_component.backpresshandler.MinimizeOnBackPressStrategy
import com.squidat.streamyx.mininimize_component.operation.Maximize
import com.squidat.streamyx.mininimize_component.operation.Minimize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MinimizableBackstack<InteractionTarget : Any>(
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
    model: MinimizableBackstackModel<InteractionTarget>,
    visualisation: (UiContext) -> Visualisation<InteractionTarget, State<InteractionTarget>> = { DefaultVisualisation(it) },
    gestureFactory: (TransitionBounds) -> GestureFactory<InteractionTarget, State<InteractionTarget>> = { DefaultGesture(it) },
    gestureSettleConfig: GestureSettleConfig = GestureSettleConfig(completionThreshold = 0.3f),
    progressAnimationSpec: AnimationSpec<Float> = tween(),
    backPressStrategy: BackPressHandlerStrategy<InteractionTarget, State<InteractionTarget>> = MinimizeOnBackPressStrategy(scope),
    animateSettle: Boolean = false
) : BaseAppyxComponent<InteractionTarget, State<InteractionTarget>>(
    scope = scope,
    model = model,
    visualisation = visualisation,
    defaultAnimationSpec = progressAnimationSpec,
    animateSettle = animateSettle,
    backPressStrategy = backPressStrategy,
    gestureFactory = gestureFactory,
    gestureSettleConfig = gestureSettleConfig,
)

class DefaultVisualisation<InteractionTarget : Any>(
    uiContext: UiContext,
    defaultAnimationSpec: SpringSpec<Float> = DefaultAnimationSpec
) : BaseVisualisation<InteractionTarget, State<InteractionTarget>, TargetUiState, MutableUiState>(
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

    override fun State<InteractionTarget>.toUiTargets(): List<MatchedTargetUiState<InteractionTarget, TargetUiState>> =
        listOfNotNull(
            matchMaximizedTargetUiState(),
            matchMinimizedTargetUiState(),
            *matchHiddenTargets(),
        )

    private fun State<InteractionTarget>.matchMaximizedTargetUiState(): MatchedTargetUiState<InteractionTarget, TargetUiState> {
        return MatchedTargetUiState(element = activeItem, targetUiState = active)
    }

    private fun State<InteractionTarget>.matchMinimizedTargetUiState(): MatchedTargetUiState<InteractionTarget, TargetUiState>? {
        return if (minimizedItem != null) MatchedTargetUiState(
            element = minimizedItem,
            targetUiState = minimized
        ) else null
    }

    private fun State<InteractionTarget>.matchHiddenTargets(): Array<MatchedTargetUiState<InteractionTarget, TargetUiState>> {
        return (created + stashed + destroyed)
            .map { element -> MatchedTargetUiState(element, hidden) }
            .toTypedArray()
    }

    override fun mutableUiStateFor(
        uiContext: UiContext,
        targetUiState: TargetUiState
    ): MutableUiState = targetUiState.toMutableUiState(uiContext)
}

class DefaultGesture<InteractionTarget : Any>(
    private val transitionBounds: TransitionBounds,
) : GestureFactory<InteractionTarget, State<InteractionTarget>> {

    override fun createGesture(
        state: State<InteractionTarget>,
        delta: Offset,
        density: Density
    ): Gesture<InteractionTarget, State<InteractionTarget>> {
        val direction = dragDirection8(delta)

        return when {
            state.minimizedItem != null && direction == UP -> Gesture(
                operation = Maximize(),
                completeAt = Offset(x = 0f, y = -transitionBounds.screenHeightPx * 0.9f)
            )

            state.minimizedItem == null && direction == DOWN -> Gesture(
                operation = Minimize(),
                completeAt = Offset(x = 0f, y = transitionBounds.screenHeightPx * 0.9f)
            )

            else -> Gesture.Noop()
        }
    }
}