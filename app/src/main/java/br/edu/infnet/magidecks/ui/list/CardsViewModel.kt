package br.edu.infnet.magidecks.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.magidecks.database.dao.CardsDao
import br.edu.infnet.magidecks.models.Card

class CardsViewModel : ViewModel() {
    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    /*fun updateCards(deckId: String) {
        CardsDao
            .listCardsOnDeck(deckId)
            .addOnSuccessListener {
                _cards.value = it.toObjects(Card::class.java)
            }
            .addOnFailureListener {
                _msg.value = it.message
            }
    }*/

    fun setupListener(deckId: String) {
        CardsDao
            .listCardsOnDeck(deckId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    _msg.value = error.message
                } else if (snapshot != null && !snapshot.isEmpty) {
                    _cards.value = snapshot.toObjects(Card::class.java)
                } else
                    _msg.value = "Sem cartas no Deck."
            }
    }

    fun deleteItem(index: Int) {
        try {
            val card = _cards.value?.get(index)
            CardsDao.delete(card)
        } catch (e: Exception) {
            _msg.value = e.message.toString()
        }
    }
}