package com.squidat.streamyx.picture_in_picture.ui.floating_visualization

import androidx.compose.animation.core.SpringSpec
import com.bumble.appyx.interactions.core.ui.DefaultAnimationSpec
import com.bumble.appyx.interactions.core.ui.context.UiContext
import com.bumble.appyx.interactions.core.ui.state.MatchedTargetUiState
import com.bumble.appyx.transitionmodel.BaseVisualisation
import com.squidat.streamyx.picture_in_picture.PictureInPictureModel.State

class FloatingVisualization<InteractionTarget : Any>(
    uiContext: UiContext,
    defaultAnimationSpec: SpringSpec<Float> = DefaultAnimationSpec,
) : BaseVisualisation<InteractionTarget, State<InteractionTarget>, TargetUiState, MutableUiState>(
    uiContext = uiContext,
    defaultAnimationSpec = defaultAnimationSpec,
) {

    override fun State<InteractionTarget>.toUiTargets(): List<MatchedTargetUiState<InteractionTarget, TargetUiState>> {
        return when (this) {
            is State.ActiveOverlay -> toUiTargets()
            is State.MinimizedOverlay -> toUiTargets()
            is State.Standalone -> toUiTargets()
        }
    }

    override fun mutableUiStateFor(
        uiContext: UiContext,
        targetUiState: TargetUiState,
    ): MutableUiState = targetUiState.toMutableUiState(uiContext)

    private fun State.ActiveOverlay<InteractionTarget>.toUiTargets(): List<MatchedTargetUiState<InteractionTarget, TargetUiState>> {
        return listOfNotNull(
            MatchedTargetUiState(activeElement, Active),
            MatchedTargetUiState(stashedElement, Hidden),
            *toCoreUiTargets(),
        )
    }

    private fun State.MinimizedOverlay<InteractionTarget>.toUiTargets(): List<MatchedTargetUiState<InteractionTarget, TargetUiState>> {
        return listOfNotNull(
            MatchedTargetUiState(activeElement, Active),
            MatchedTargetUiState(minimizedElement, Minimized),
            *toCoreUiTargets(),
        )
    }

    private fun State.Standalone<InteractionTarget>.toUiTargets(): List<MatchedTargetUiState<InteractionTarget, TargetUiState>> {
        return listOfNotNull(
            MatchedTargetUiState(activeElement, Active),
            *toCoreUiTargets(),
        )
    }

    private fun State<InteractionTarget>.toCoreUiTargets(): Array<MatchedTargetUiState<InteractionTarget, TargetUiState>> {
        return (destroyed + created)
            .filterNotNull()
            .map { element -> MatchedTargetUiState(element, Hidden) }
            .toTypedArray()
    }
}