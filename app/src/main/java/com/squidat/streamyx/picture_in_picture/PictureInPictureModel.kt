package com.squidat.streamyx.picture_in_picture

import android.os.Parcelable
import com.bumble.appyx.interactions.core.Element
import com.bumble.appyx.interactions.core.Elements
import com.bumble.appyx.interactions.core.asElement
import com.bumble.appyx.interactions.core.model.transition.BaseTransitionModel
import com.bumble.appyx.utils.multiplatform.SavedStateMap
import kotlinx.parcelize.Parcelize

class PictureInPictureModel<InteractionTarget : Any>(
    defaultItem: InteractionTarget,
    savedStateMap: SavedStateMap?,
) : BaseTransitionModel<InteractionTarget, PictureInPictureModel.State<InteractionTarget>>(
    savedStateMap = savedStateMap,
) {

    sealed interface State<InteractionTarget> : Parcelable {

        val created: Element<InteractionTarget>?
        val destroyed: Elements<InteractionTarget>

        val coreElements: Set<Element<InteractionTarget>>
            get() = setOfNotNull(created, *destroyed.toTypedArray())

        @Parcelize
        data class Standalone<InteractionTarget>(
            val activeElement: Element<InteractionTarget>,
            override val created: Element<InteractionTarget>? = null,
            override val destroyed: Elements<InteractionTarget> = emptyList(),
        ) : State<InteractionTarget>

        @Parcelize
        data class ActiveOverlay<InteractionTarget>(
            val stashedElement: Element<InteractionTarget>,
            val activeElement: Element<InteractionTarget>,
            override val created: Element<InteractionTarget>? = null,
            override val destroyed: Elements<InteractionTarget> = emptyList(),
        ) : State<InteractionTarget>

        @Parcelize
        data class MinimizedOverlay<InteractionTarget>(
            val activeElement: Element<InteractionTarget>,
            val minimizedElement: Element<InteractionTarget>,
            override val created: Element<InteractionTarget>? = null,
            override val destroyed: Elements<InteractionTarget> = emptyList(),
        ) : State<InteractionTarget>
    }

    override val initialState: State<InteractionTarget> = State.Standalone(defaultItem.asElement())

    override fun State<InteractionTarget>.availableElements(): Set<Element<InteractionTarget>> {
        return when (this) {
            is State.Standalone -> setOf(activeElement) + coreElements
            is State.ActiveOverlay -> setOf(activeElement, stashedElement) + coreElements
            is State.MinimizedOverlay -> setOf(activeElement, minimizedElement) + coreElements
        }
    }

    override fun State<InteractionTarget>.destroyedElements(): Set<Element<InteractionTarget>> {
        return destroyed.toSet()
    }

    override fun State<InteractionTarget>.removeDestroyedElements(): State<InteractionTarget> {
        return when (this) {
            is State.ActiveOverlay -> copy(destroyed = emptyList())
            is State.MinimizedOverlay -> copy(destroyed = emptyList())
            is State.Standalone -> copy(destroyed = emptyList())
        }
    }

    override fun State<InteractionTarget>.removeDestroyedElement(element: Element<InteractionTarget>): State<InteractionTarget> {
        val withoutDestroyedElement = destroyed.filterNot { it == element }

        return when (this) {
            is State.ActiveOverlay -> copy(destroyed = withoutDestroyedElement)
            is State.MinimizedOverlay -> copy(destroyed = withoutDestroyedElement)
            is State.Standalone -> copy(destroyed = withoutDestroyedElement)
        }
    }
}