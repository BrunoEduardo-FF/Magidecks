package br.edu.infnet.magidecks.database.dao

import br.edu.infnet.magidecks.models.Deck
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object DecksDao {
    val collection = Firebase.firestore.collection("decks")

    fun insert(deck: Deck): Task<DocumentReference> {
        return collection.add(deck)
    }

    fun listUserDecks(userId: String): Task<QuerySnapshot> {
        return collection.whereEqualTo("userId", userId).get()
    }
/*
    fun readDeckByName(deckName: String): Task<QuerySnapshot> {
        return collection
            .whereEqualTo("nome", deckName)
            .get()
    }*/

    fun delete(deck: Deck?): Task<Void> {
        return collection.document(deck?.id!!).delete()
    }

    fun read(docId: String):Task<DocumentSnapshot> {
        return collection.document(docId).get()
    }

    fun update(deck: Deck):Task<Void> {
        return collection.document(deck.id!!).set(deck)
    }

}