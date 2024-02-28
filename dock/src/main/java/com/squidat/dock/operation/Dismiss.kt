package com.squidat.dock.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.dock.Dock
import com.squidat.dock.DockModel.State
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
                dismissed = fromState.minimizedElement,
            )

            else -> error("Invalid operation")
        }
    }
}

fun <InteractionTarget : Any> Dock<InteractionTarget>.dismiss(
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null,
) {
    operation(operation = Dismiss(mode), animationSpec = animationSpec)
}