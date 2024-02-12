package com.squidat.streamyx.minimizable_backstack.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.streamyx.minimizable_backstack.MinimizableBackstack
import com.squidat.streamyx.minimizable_backstack.MinimizableBackstackModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dismiss<InteractionTarget>(
    override var mode: Operation.Mode = Operation.Mode.KEYFRAME
) : BaseOperation<MinimizableBackstackModel.State<InteractionTarget>>() {

    override fun isApplicable(state: MinimizableBackstackModel.State<InteractionTarget>): Boolean =
        state.minimizedItem != null

    override fun createFromState(
        baseLineState: MinimizableBackstackModel.State<InteractionTarget>,
    ): MinimizableBackstackModel.State<InteractionTarget> = baseLineState

    override fun createTargetState(
        fromState: MinimizableBackstackModel.State<InteractionTarget>,
    ): MinimizableBackstackModel.State<InteractionTarget> {
        return fromState.copy(
            minimizedItem = null,
            destroyed = fromState.destroyed + requireNotNull(fromState.minimizedItem),
        )
    }
}

fun <InteractionTarget : Any> MinimizableBackstack<InteractionTarget>.dismiss(
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null
) {
    operation(operation = Dismiss(mode), animationSpec = animationSpec)
}
