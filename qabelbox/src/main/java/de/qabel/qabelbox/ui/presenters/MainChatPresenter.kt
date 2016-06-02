package de.qabel.qabelbox.ui.presenters

import de.qabel.qabelbox.interactor.ChatUseCase
import de.qabel.qabelbox.ui.views.ChatView
import javax.inject.Inject

class MainChatPresenter @Inject constructor(private val view: ChatView,
                                            private val useCase: ChatUseCase) : ChatPresenter {
    override val title: String
        get() = useCase.contact.alias

    init {
        view.showEmpty()
    }

    override fun refreshMessages() {
        useCase.retrieve().subscribe({messages -> view.showMessages(messages)})
    }
}
