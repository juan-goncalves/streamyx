package com.squidat.streamyx.picture_in_picture

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import com.bumble.appyx.interactions.core.model.BaseAppyxComponent
import com.bumble.appyx.interactions.core.model.backpresshandlerstrategies.BackPressHandlerStrategy
import com.bumble.appyx.interactions.core.ui.Visualisation
import com.bumble.appyx.interactions.core.ui.context.TransitionBounds
import com.bumble.appyx.interactions.core.ui.context.UiContext
import com.bumble.appyx.interactions.core.ui.gesture.GestureFactory
import com.bumble.appyx.interactions.core.ui.gesture.GestureSettleConfig
import com.squidat.streamyx.picture_in_picture.backpress.MinimizeOnBackPressStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class PictureInPicture<InteractionTarget : Any>(
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
    model: PictureInPictureModel<InteractionTarget>,
    visualisation: (UiContext) -> Visualisation<InteractionTarget, PictureInPictureModel.State<InteractionTarget>>,
    gestureFactory: (TransitionBounds) -> GestureFactory<InteractionTarget, PictureInPictureModel.State<InteractionTarget>>,
    gestureSettleConfig: GestureSettleConfig = GestureSettleConfig(completionThreshold = 0.3f),
    progressAnimationSpec: AnimationSpec<Float> = tween(),
    backPressStrategy: BackPressHandlerStrategy<InteractionTarget, PictureInPictureModel.State<InteractionTarget>> = MinimizeOnBackPressStrategy(
        scope
    ),
    animateSettle: Boolean = false
) : BaseAppyxComponent<InteractionTarget, PictureInPictureModel.State<InteractionTarget>>(
    scope = scope,
    model = model,
    visualisation = visualisation,
    defaultAnimationSpec = progressAnimationSpec,
    animateSettle = animateSettle,
    backPressStrategy = backPressStrategy,
    gestureFactory = gestureFactory,
    gestureSettleConfig = gestureSettleConfig,
)