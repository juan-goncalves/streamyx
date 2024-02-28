package com.squidat.dock.gesture

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import com.bumble.appyx.interactions.core.ui.context.TransitionBounds
import com.bumble.appyx.interactions.core.ui.gesture.Drag
import com.bumble.appyx.interactions.core.ui.gesture.Drag.Direction4.DOWN
import com.bumble.appyx.interactions.core.ui.gesture.Drag.Direction4.UP
import com.bumble.appyx.interactions.core.ui.gesture.Gesture
import com.bumble.appyx.interactions.core.ui.gesture.GestureFactory
import com.bumble.appyx.interactions.core.ui.gesture.dragDirection4
import com.squidat.dock.DockModel.State
import com.squidat.dock.operation.Expand
import com.squidat.dock.operation.Dock

class DragOverlayGesture<InteractionTarget : Any>(
    private val transitionBounds: TransitionBounds,
) : GestureFactory<InteractionTarget, State<InteractionTarget>> {

    override fun createGesture(
        state: State<InteractionTarget>,
        delta: Offset,
        density: Density,
    ): Gesture<InteractionTarget, State<InteractionTarget>> {
        val direction = dragDirection4(delta)

        return when (state) {
            is State.ActiveOverlay -> minimizeOnDragDown(direction)
            is State.MinimizedOverlay -> maximizeOnDragUp(direction)
            is State.Standalone -> Gesture.Noop()
        }
    }

    private fun minimizeOnDragDown(direction: Drag.Direction4): Gesture<InteractionTarget, State<InteractionTarget>> {
        return when (direction) {
            DOWN -> Gesture(
                operation = Dock(),
                completeAt = Offset(x = 0f, y = transitionBounds.screenHeightPx.toFloat()),
            )

            else -> Gesture.Noop()
        }
    }

    private fun maximizeOnDragUp(direction: Drag.Direction4): Gesture<InteractionTarget, State<InteractionTarget>> {
        return when (direction) {
            UP -> Gesture(
                operation = Expand(),
                completeAt = Offset(x = 0f, y = -transitionBounds.screenHeightPx.toFloat()),
            )

            else -> Gesture.Noop()
        }
    }
}