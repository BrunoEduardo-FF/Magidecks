package br.edu.infnet.magidecks.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.infnet.magidecks.database.dao.DecksDao
import br.edu.infnet.magidecks.models.Deck

class AddDeckViewModel : ViewModel() {
    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg
    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    init {
        _msg.value = ""
        _status.value = false
    }

    /*private fun deckNameExists(deckName: String): Boolean {
        var bool = false
        DecksDao
            .readDeckByName(deckName)
            .addOnFailureListener {
                Log.d("Teste", "falhou")
                bool = false
            }
        return bool
    }*/

    fun addDeck(uid: String, deckName: String) {
        /*if(!deckNameExists(deckName)) {*/
        DecksDao
            .insert(Deck(uid, deckName))
            .addOnSuccessListener {
                _msg.value = "Deck adicionado com sucesso!"
                _status.value = true
            }
    }

}