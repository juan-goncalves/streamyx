package com.squidat.streamyx.picture_in_picture.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.streamyx.picture_in_picture.PictureInPicture
import com.squidat.streamyx.picture_in_picture.PictureInPictureModel.State
import kotlinx.parcelize.Parcelize

@Parcelize
data class Minimize<InteractionTarget : Any>(
    override var mode: Operation.Mode = Operation.Mode.KEYFRAME
) : BaseOperation<State<InteractionTarget>>() {

    override fun isApplicable(state: State<InteractionTarget>): Boolean {
        return state is State.ActiveOverlay
    }

    override fun createFromState(baseLineState: State<InteractionTarget>): State<InteractionTarget> {
        return baseLineState
    }

    override fun createTargetState(fromState: State<InteractionTarget>): State<InteractionTarget> {
        return when (fromState) {
            is State.ActiveOverlay -> State.MinimizedOverlay(
                activeElement = fromState.stashedElement,
                minimizedElement = fromState.activeElement,
            )

            else -> error("Invalid operation")
        }
    }
}

fun <InteractionTarget : Any> PictureInPicture<InteractionTarget>.minimize(
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null
) {
    operation(operation = Minimize(mode), animationSpec = animationSpec)
}