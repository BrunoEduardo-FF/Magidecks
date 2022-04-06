package br.edu.infnet.magidecks.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.magidecks.database.dao.DecksDao
import br.edu.infnet.magidecks.models.Deck

class DecksViewModel : ViewModel() {
    private val _decks = MutableLiveData<List<Deck>>()
    val decks: LiveData<List<Deck>> = _decks
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun updateList(userId: String) {
        DecksDao
            .listUserDecks(userId)
            .addOnSuccessListener {
                _decks.value = it.toObjects(Deck::class.java)
            }
            .addOnFailureListener {
                _msg.value = it.message
            }
    }

    fun deleteItem(index: Int, userId: String) {
        try {
            val deck = _decks.value?.get(index)
            DecksDao.delete(deck)
            updateList(userId)
        } catch (e: Exception) {
            _msg.value = e.message.toString()
        }
    }
}