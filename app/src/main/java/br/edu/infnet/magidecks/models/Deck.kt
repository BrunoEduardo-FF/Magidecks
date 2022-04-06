package br.edu.infnet.magidecks.models

import com.google.firebase.firestore.DocumentId

data class Deck(
    val userId: String = "",
    var nome: String = "",
    @DocumentId val id: String? = null
)
