package com.squidat.dock.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.asElement
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.dock.DockComponent
import com.squidat.dock.DockModel.State
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Open<InteractionTarget : Any>(
    private val target: @RawValue InteractionTarget,
    override var mode: Operation.Mode = Operation.Mode.KEYFRAME
) : BaseOperation<State<InteractionTarget>>() {

    override fun isApplicable(state: State<InteractionTarget>): Boolean {
        return state is State.Standalone
                || (state is State.MinimizedOverlay && target == state.minimizedElement.interactionTarget)
    }

    override fun createFromState(baseLineState: State<InteractionTarget>): State<InteractionTarget> {
        return when (baseLineState) {
            is State.Standalone -> baseLineState.copy(created = target.asElement())
            is State.MinimizedOverlay -> baseLineState
            else -> error("Invalid operation")
        }
    }

    override fun createTargetState(fromState: State<InteractionTarget>): State<InteractionTarget> {
        return when (fromState) {

            is State.Standalone -> State.ActiveOverlay(
                stashedElement = fromState.activeElement,
                activeElement = requireNotNull(fromState.created),
            )

            is State.MinimizedOverlay -> State.ActiveOverlay(
                stashedElement = fromState.activeElement,
                activeElement = fromState.minimizedElement,
            )

            else -> error("Invalid operation")
        }
    }
}

fun <InteractionTarget : Any> DockComponent<InteractionTarget>.open(
    interactionTarget: InteractionTarget,
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null,
) {
    operation(operation = Open(interactionTarget, mode), animationSpec = animationSpec)
}
