package com.squidat.dock

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import com.bumble.appyx.interactions.core.model.BaseAppyxComponent
import com.bumble.appyx.interactions.core.model.backpresshandlerstrategies.BackPressHandlerStrategy
import com.bumble.appyx.interactions.core.ui.Visualisation
import com.bumble.appyx.interactions.core.ui.context.TransitionBounds
import com.bumble.appyx.interactions.core.ui.context.UiContext
import com.bumble.appyx.interactions.core.ui.gesture.GestureFactory
import com.bumble.appyx.interactions.core.ui.gesture.GestureSettleConfig
import com.bumble.appyx.utils.multiplatform.SavedStateMap
import com.squidat.dock.DockModel.State
import com.squidat.dock.backpress.DockOnBackPressStrategy
import com.squidat.dock.gesture.DragOverlayGesture
import com.squidat.dock.ui.floating_visualization.FloatingVisualization
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class DockComponent<InteractionTarget : Any>(
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
    model: DockModel<InteractionTarget>,
    visualisation: (UiContext) -> Visualisation<InteractionTarget, State<InteractionTarget>>,
    gestureFactory: (TransitionBounds) -> GestureFactory<InteractionTarget, State<InteractionTarget>>,
    gestureSettleConfig: GestureSettleConfig,
    progressAnimationSpec: AnimationSpec<Float> = tween(),
    backPressStrategy: BackPressHandlerStrategy<InteractionTarget, State<InteractionTarget>>,
    animateSettle: Boolean = false,
) : BaseAppyxComponent<InteractionTarget, State<InteractionTarget>>(
    scope = scope,
    model = model,
    visualisation = visualisation,
    defaultAnimationSpec = progressAnimationSpec,
    animateSettle = animateSettle,
    backPressStrategy = backPressStrategy,
    gestureFactory = gestureFactory,
    gestureSettleConfig = gestureSettleConfig,
) {
    companion object
}

fun <InteractionTarget : Any> DockComponent.Companion.create(
    defaultItem: InteractionTarget,
    savedStateMap: SavedStateMap?,
): DockComponent<InteractionTarget> {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    return DockComponent(
        scope = scope,
        model = DockModel(defaultItem, savedStateMap),
        visualisation = { FloatingVisualization(it) },
        gestureFactory = { DragOverlayGesture(it) },
        gestureSettleConfig = GestureSettleConfig(completionThreshold = 0.3f),
        backPressStrategy = DockOnBackPressStrategy(scope),
    )
}