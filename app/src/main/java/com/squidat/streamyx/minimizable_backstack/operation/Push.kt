package com.squidat.streamyx.minimizable_backstack.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.asElement
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.squidat.streamyx.minimizable_backstack.MinimizableBackstack
import com.squidat.streamyx.minimizable_backstack.MinimizableBackstackModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Push<InteractionTarget : Any>(
    private val target: @RawValue InteractionTarget,
    override var mode: Operation.Mode = Operation.Mode.KEYFRAME
) : BaseOperation<MinimizableBackstackModel.State<InteractionTarget>>() {

    override fun isApplicable(
        state: MinimizableBackstackModel.State<InteractionTarget>,
    ): Boolean = true

    override fun createFromState(
        baseLineState: MinimizableBackstackModel.State<InteractionTarget>,
    ): MinimizableBackstackModel.State<InteractionTarget> {
        return baseLineState.copy(
            created = baseLineState.created + target.asElement()
        )
    }

    override fun createTargetState(
        fromState: MinimizableBackstackModel.State<InteractionTarget>,
    ): MinimizableBackstackModel.State<InteractionTarget> {
        return fromState.copy(
            activeItem = fromState.created.last(),
            stashed = fromState.stashed + fromState.activeItem,
            created = fromState.created.dropLast(1),
        )
    }
}

fun <InteractionTarget : Any> MinimizableBackstack<InteractionTarget>.push(
    interactionTarget: InteractionTarget,
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float>? = null
) {
    operation(operation = Push(interactionTarget, mode), animationSpec = animationSpec)
}
