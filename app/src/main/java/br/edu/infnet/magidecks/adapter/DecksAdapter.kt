package br.edu.infnet.magidecks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.magidecks.R
import br.edu.infnet.magidecks.models.Deck

class DecksAdapter(val decks: List<Deck>,
                   val deleteItem: (Int) -> Unit,
                   val editItem: (String) -> Unit,
                   val openDeck: (String, String) -> Unit
                   )
    : RecyclerView.Adapter<DecksAdapter.DecksAdapterViewHolder>() {

    class DecksAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblNome = itemView.findViewById<TextView>(R.id.recycler_deck_item_lbl_nome)
        val btnDelete = itemView.findViewById<ImageView>(R.id.recycler_deck_item_btn_delete)
        val btnEdit = itemView.findViewById<ImageView>(R.id.recycler_deck_item_btn_edit)
        /*val lblData = itemView.findViewById<TextView>(R.id.recycler_anotacoes_item_lbl_data)
        val lblTitulo = itemView.findViewById<TextView>(R.id.recycler_anotacoes_item_lbl_titulo)*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecksAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_deck_item, parent, false)
        return DecksAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DecksAdapterViewHolder, position: Int) {
        val deck = decks[position]
        holder.lblNome.text = deck.nome
        holder.btnDelete.setOnClickListener {
            deleteItem(position)
        }
        holder.btnEdit.setOnClickListener {
            if(!deck.id.isNullOrBlank()) editItem(deck.id)
        }
        holder.itemView.setOnClickListener {
            if(!deck.id.isNullOrBlank()) openDeck(deck.id, deck.nome)
        }
    }

    override fun getItemCount(): Int = decks.size
}