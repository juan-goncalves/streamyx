package com.squidat.streamyx.mininimize_component.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.streamyx.mininimize_component.MinimizableBackstack
import com.squidat.streamyx.mininimize_component.MinimizableBackstackModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pop<InteractionTarget>(
    override var mode: Operation.Mode = Operation.Mode.KEYFRAME
) : BaseOperation<MinimizableBackstackModel.State<InteractionTarget>>() {

    override fun isApplicable(state: MinimizableBackstackModel.State<InteractionTarget>): Boolean =
        state.stashed.isNotEmpty()

    override fun createFromState(
        baseLineState: MinimizableBackstackModel.State<InteractionTarget>,
    ): MinimizableBackstackModel.State<InteractionTarget> = baseLineState

    override fun createTargetState(
        fromState: MinimizableBackstackModel.State<InteractionTarget>,
    ): MinimizableBackstackModel.State<InteractionTarget> {
        return fromState.copy(
            activeItem = fromState.stashed.last(),
            stashed = fromState.stashed.dropLast(1),
            destroyed = fromState.destroyed + fromState.activeItem,
        )
    }
}

fun <InteractionTarget : Any> MinimizableBackstack<InteractionTarget>.pop(
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null
) {
    operation(operation = Pop(mode), animationSpec = animationSpec)
}
