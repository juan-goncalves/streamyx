package com.squidat.streamyx.minimizable_backstack.backpresshandler

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.backpresshandlerstrategies.BaseBackPressHandlerStrategy
import com.bumble.appyx.mapState
import com.squidat.streamyx.minimizable_backstack.MinimizableBackstackModel
import com.squidat.streamyx.minimizable_backstack.operation.Minimize
import com.squidat.streamyx.minimizable_backstack.operation.Pop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

class MinimizeOnBackPressStrategy<InteractionTarget : Any>(
    private val scope: CoroutineScope,
    private val animationSpec: AnimationSpec<Float>? = null
) : BaseBackPressHandlerStrategy<InteractionTarget, MinimizableBackstackModel.State<InteractionTarget>>() {

    override val canHandleBackPress: StateFlow<Boolean> by lazy {
        transitionModel.output.mapState(scope) { output ->
            val hasStashedItem = output.currentTargetState.stashed.isNotEmpty()
            val canMinimizeItem = output.currentTargetState.minimizedItem == null && hasStashedItem
            canMinimizeItem || hasStashedItem
        }
    }

    override fun handleBackPress(): Boolean {
        val minimize = Minimize<InteractionTarget>()
        val pop = Pop<InteractionTarget>()

        return when {
            minimize.isApplicable(transitionModel.output.value.currentTargetState) -> {
                appyxComponent.operation(operation = Minimize(), animationSpec)
                true
            }

            pop.isApplicable(transitionModel.output.value.currentTargetState) -> {
                appyxComponent.operation(operation = Pop(), animationSpec)
                true
            }

            else -> false
        }
    }
}