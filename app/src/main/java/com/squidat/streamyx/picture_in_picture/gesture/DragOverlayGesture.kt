package com.squidat.streamyx.picture_in_picture.gesture

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import com.bumble.appyx.interactions.core.ui.context.TransitionBounds
import com.bumble.appyx.interactions.core.ui.gesture.Drag
import com.bumble.appyx.interactions.core.ui.gesture.Drag.Direction4.DOWN
import com.bumble.appyx.interactions.core.ui.gesture.Drag.Direction4.UP
import com.bumble.appyx.interactions.core.ui.gesture.Gesture
import com.bumble.appyx.interactions.core.ui.gesture.GestureFactory
import com.bumble.appyx.interactions.core.ui.gesture.dragDirection4
import com.squidat.streamyx.picture_in_picture.PictureInPictureModel.State
import com.squidat.streamyx.picture_in_picture.operation.Maximize
import com.squidat.streamyx.picture_in_picture.operation.Minimize

class DragOverlayGesture<InteractionTarget : Any>(
    private val transitionBounds: TransitionBounds,
) : GestureFactory<InteractionTarget, State<InteractionTarget>> {

    override fun createGesture(
        state: State<InteractionTarget>,
        delta: Offset,
        density: Density
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
                operation = Minimize(),
                completeAt = Offset(x = 0f, y = transitionBounds.screenHeightPx.toFloat()),
            )

            else -> Gesture.Noop()
        }
    }

    private fun maximizeOnDragUp(direction: Drag.Direction4): Gesture<InteractionTarget, State<InteractionTarget>> {
        return when (direction) {
            UP -> Gesture(
                operation = Maximize(),
                completeAt = Offset(x = 0f, y = -transitionBounds.screenHeightPx.toFloat()),
            )

            else -> Gesture.Noop()
        }
    }
}