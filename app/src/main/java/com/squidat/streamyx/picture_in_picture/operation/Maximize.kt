package com.squidat.streamyx.picture_in_picture.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.streamyx.picture_in_picture.PictureInPicture
import com.squidat.streamyx.picture_in_picture.PictureInPictureModel.State
import kotlinx.parcelize.Parcelize

@Parcelize
data class Maximize<InteractionTarget : Any>(
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
            is State.MinimizedOverlay -> State.ActiveOverlay(
                stashedElement = fromState.activeElement,
                activeElement = fromState.minimizedElement,
            )

            else -> error("Invalid operation")
        }
    }
}

fun <InteractionTarget : Any> PictureInPicture<InteractionTarget>.maximize(
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null
) {
    operation(operation = Maximize(mode), animationSpec = animationSpec)
}
