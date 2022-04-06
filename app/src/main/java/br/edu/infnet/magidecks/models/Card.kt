package br.edu.infnet.magidecks.models

import com.google.firebase.firestore.DocumentId
import java.io.Serializable

class Card(
    val name: String = "",
    val type: String = "",
    val manaCost: String = "",
    val rarity: String = "",
    val power: String = "",
    val toughness: String = "",
    //val imageUrl: String = "",
    val text: String = "",
    var deckId: String = "",
    @DocumentId val id: String? = null
): Serializable {
}