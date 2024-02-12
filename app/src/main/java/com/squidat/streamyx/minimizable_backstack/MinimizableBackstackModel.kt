package com.squidat.streamyx.minimizable_backstack

import com.bumble.appyx.interactions.core.Element
import com.bumble.appyx.interactions.core.Elements
import com.bumble.appyx.interactions.core.asElement
import com.bumble.appyx.interactions.core.model.transition.BaseTransitionModel
import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.RawValue
import com.bumble.appyx.utils.multiplatform.SavedStateMap
import kotlinx.parcelize.Parcelize

class MinimizableBackstackModel<InteractionTarget : Any>(
    defaultItem: InteractionTarget,
    savedStateMap: SavedStateMap?,
) : BaseTransitionModel<InteractionTarget, MinimizableBackstackModel.State<InteractionTarget>>(
    savedStateMap = savedStateMap,
) {
    @Parcelize
    data class State<InteractionTarget>(
        val activeItem: @RawValue Element<InteractionTarget>,
        val minimizedItem: @RawValue Element<InteractionTarget>? = null,
        val created: Elements<InteractionTarget> = emptyList(),
        val stashed: Elements<InteractionTarget> = emptyList(),
        val destroyed: @RawValue Elements<InteractionTarget> = emptyList(),
    ) : Parcelable

    override val initialState: State<InteractionTarget> = State(
        activeItem = defaultItem.asElement(),
    )

    override fun State<InteractionTarget>.availableElements(): Set<Element<InteractionTarget>> {
        return (created + activeItem + minimizedItem + stashed + destroyed)
            .filterNotNull()
            .toSet()
    }

    override fun State<InteractionTarget>.destroyedElements(): Set<Element<InteractionTarget>> {
        return destroyed.toSet()
    }

    override fun State<InteractionTarget>.removeDestroyedElements(): State<InteractionTarget> {
        return copy(destroyed = emptyList())
    }

    override fun State<InteractionTarget>.removeDestroyedElement(element: Element<InteractionTarget>): State<InteractionTarget> {
        return copy(destroyed = destroyed.filterNot { it == element })
    }
}