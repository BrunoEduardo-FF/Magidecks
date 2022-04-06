package br.edu.infnet.magidecks.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.infnet.magidecks.apiservice.mtg.ApiClient
import br.edu.infnet.magidecks.database.dao.CardsDao
import br.edu.infnet.magidecks.models.Card
import br.edu.infnet.magidecks.models.Cards
import kotlinx.coroutines.launch

class AddCardViewModel : ViewModel() {
    private val _cards = MutableLiveData<Cards>()
    val cards: LiveData<Cards> = _cards
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun addCardToDeck(card: Card, deckId: String) {
        card.deckId = deckId
        viewModelScope.launch {
            CardsDao.insert(card)
            _msg.value = "Carta ${card.name} inserida."
        }
    }

    fun searchCards(name: String) {
        viewModelScope.launch {
            _cards.value = ApiClient.getCardsService().list(name)
        }
    }
}