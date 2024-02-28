package com.squidat.dock

import android.os.Parcelable
import com.bumble.appyx.interactions.core.Element
import com.bumble.appyx.interactions.core.asElement
import com.bumble.appyx.interactions.core.model.transition.BaseTransitionModel
import com.bumble.appyx.utils.multiplatform.SavedStateMap
import kotlinx.parcelize.Parcelize

class DockModel<InteractionTarget : Any>(
    defaultItem: InteractionTarget,
    savedStateMap: SavedStateMap?,
) : BaseTransitionModel<InteractionTarget, DockModel.State<InteractionTarget>>(
    savedStateMap = savedStateMap,
) {

    sealed interface State<InteractionTarget> : Parcelable {

        @Parcelize
        data class Standalone<InteractionTarget>(
            val activeElement: Element<InteractionTarget>,
            val created: Element<InteractionTarget>? = null,
            val dismissed: Element<InteractionTarget>? = null,
        ) : State<InteractionTarget>

        @Parcelize
        data class ActiveOverlay<InteractionTarget>(
            val stashedElement: Element<InteractionTarget>,
            val activeElement: Element<InteractionTarget>,
        ) : State<InteractionTarget>

        @Parcelize
        data class MinimizedOverlay<InteractionTarget>(
            val activeElement: Element<InteractionTarget>,
            val minimizedElement: Element<InteractionTarget>,
        ) : State<InteractionTarget>
    }

    override val initialState: State<InteractionTarget> = State.Standalone(defaultItem.asElement())

    override fun State<InteractionTarget>.availableElements(): Set<Element<InteractionTarget>> {
        return when (this) {
            is State.Standalone -> setOfNotNull(activeElement, created, dismissed)
            is State.ActiveOverlay -> setOf(activeElement, stashedElement)
            is State.MinimizedOverlay -> setOf(activeElement, minimizedElement)
        }
    }

    override fun State<InteractionTarget>.destroyedElements(): Set<Element<InteractionTarget>> {
        return when (this) {
            is State.Standalone -> setOfNotNull(dismissed)
            is State.ActiveOverlay -> emptySet()
            is State.MinimizedOverlay -> emptySet()
        }
    }

    override fun State<InteractionTarget>.removeDestroyedElements(): State<InteractionTarget> {
        return when (this) {
            is State.Standalone -> copy(dismissed = null)
            is State.ActiveOverlay -> this
            is State.MinimizedOverlay -> this
        }
    }

    override fun State<InteractionTarget>.removeDestroyedElement(element: Element<InteractionTarget>): State<InteractionTarget> {
        return when (this) {
            is State.Standalone -> copy(dismissed = null)
            is State.ActiveOverlay -> this
            is State.MinimizedOverlay -> this
        }
    }
}