package com.squidat.dock.backpress

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.core.model.backpresshandlerstrategies.BaseBackPressHandlerStrategy
import com.bumble.appyx.mapState
import com.squidat.dock.DockModel.State
import com.squidat.dock.operation.Dock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

class DockOnBackPressStrategy<InteractionTarget : Any>(
    private val scope: CoroutineScope,
    private val animationSpec: AnimationSpec<Float>? = null
) : BaseBackPressHandlerStrategy<InteractionTarget, State<InteractionTarget>>() {

    override val canHandleBackPress: StateFlow<Boolean> by lazy {
        transitionModel.output.mapState(scope) { output ->
            output.currentTargetState is State.ActiveOverlay
        }
    }

    override fun handleBackPress(): Boolean {
        val dock = Dock<InteractionTarget>()

        if (dock.isApplicable(transitionModel.output.value.currentTargetState)) {
            appyxComponent.operation(operation = Dock(), animationSpec)
        }

        return true
    }
}