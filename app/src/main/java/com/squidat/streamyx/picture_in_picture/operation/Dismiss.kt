package com.squidat.streamyx.picture_in_picture.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.streamyx.picture_in_picture.PictureInPicture
import com.squidat.streamyx.picture_in_picture.PictureInPictureModel.State
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dismiss<InteractionTarget : Any>(
    override var mode: Operation.Mode = Operation.Mode.KEYFRAME
) : BaseOperation<State<InteractionTarget>>() {

    override fun isApplicable(state: State<InteractionTarget>): Boolean {
        return state is State.MinimizedOverlay
    }

    override fun createFromState(baseLineState: State<InteractionTarget>): State<InteractionTarget> {
        return baseLineState
    }

    override fun createTargetState(fromState: State<InteractionTarget>): State<InteractionTarget> {
        return when (fromState) {
            is State.MinimizedOverlay -> State.Standalone(
                activeElement = fromState.activeElement,
                destroyed = fromState.destroyed + fromState.minimizedElement,
            )

            else -> error("Invalid operation")
        }
    }
}

fun <InteractionTarget : Any> PictureInPicture<InteractionTarget>.dismiss(
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null
) {
    operation(operation = Dismiss(mode), animationSpec = animationSpec)
}