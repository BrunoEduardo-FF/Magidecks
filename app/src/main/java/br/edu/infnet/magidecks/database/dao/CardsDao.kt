package br.edu.infnet.magidecks.database.dao

import br.edu.infnet.magidecks.models.Card
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object CardsDao {

    val collection = Firebase.firestore.collection("cards")

    fun insert(card: Card): Task<DocumentReference> {
        return collection.add(card)
    }

    fun listCardsOnDeck(deckId: String): Query {
        return collection.whereEqualTo("deckId", deckId)
    }

    /*fun listCardsOnDeck(deckId: String): Task<QuerySnapshot> {
        return collection.whereEqualTo("deckId", deckId).get()
    }*/

    fun delete(card: Card?): Task<Void> {
        return collection.document(card?.id!!).delete()
    }
}