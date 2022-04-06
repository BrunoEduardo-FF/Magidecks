package br.edu.infnet.magidecks.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.magidecks.database.dao.DecksDao
import br.edu.infnet.magidecks.models.Deck

class EditDeckViewModel : ViewModel() {
    private val _deck = MutableLiveData<Deck>()
    val deck: LiveData<Deck> = _deck

    fun getDeck(docId: String) {
        val snapshot = DecksDao.read(docId)
        snapshot.addOnSuccessListener {
            _deck.value = it.toObject(Deck::class.java)
        }
    }

    fun salvarEdicao(nome: String) {
        _deck.value?.nome = nome
        DecksDao.update(_deck.value!!)
    }
}