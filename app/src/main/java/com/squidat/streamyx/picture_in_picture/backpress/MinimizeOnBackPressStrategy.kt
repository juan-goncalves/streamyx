package com.squidat.streamyx.picture_in_picture.backpress

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.backpresshandlerstrategies.BaseBackPressHandlerStrategy
import com.bumble.appyx.mapState
import com.squidat.streamyx.picture_in_picture.PictureInPictureModel.State
import com.squidat.streamyx.picture_in_picture.operation.Minimize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

class MinimizeOnBackPressStrategy<InteractionTarget : Any>(
    private val scope: CoroutineScope,
    private val animationSpec: AnimationSpec<Float>? = null
) : BaseBackPressHandlerStrategy<InteractionTarget, State<InteractionTarget>>() {

    override val canHandleBackPress: StateFlow<Boolean> by lazy {
        transitionModel.output.mapState(scope) { output ->
            output.currentTargetState is State.ActiveOverlay
        }
    }

    override fun handleBackPress(): Boolean {
        val minimize = Minimize<InteractionTarget>()

        if (minimize.isApplicable(transitionModel.output.value.currentTargetState)) {
            appyxComponent.operation(operation = Minimize(), animationSpec)
        }

        return true
    }
}