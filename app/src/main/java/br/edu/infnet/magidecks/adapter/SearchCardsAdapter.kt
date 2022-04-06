package br.edu.infnet.magidecks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.magidecks.R
import br.edu.infnet.magidecks.models.Card
import br.edu.infnet.magidecks.models.Cards

class SearchCardsAdapter(val cards: Cards,
                         val addCardToDeck: (Card) -> Unit,
                         val detailCard: (Card) -> Unit)
    : RecyclerView.Adapter<SearchCardsAdapter.SearchCardsAdapterViewHolder>() {

    class SearchCardsAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val lblNome = itemView.findViewById<TextView>(R.id.recycler_search_cards_item_lbl_nome)
        val btnAdd = itemView.findViewById<ImageButton>(R.id.recycler_search_cards_item_btn_add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCardsAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_search_cards_item, parent, false)
        return SearchCardsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchCardsAdapterViewHolder, position: Int) {
        val card = cards.cards[position]
        holder.lblNome.text = card.name
        holder.btnAdd.setOnClickListener {
            addCardToDeck(card)
        }
        holder.itemView.setOnClickListener {
            detailCard(card)
        }
    }

    override fun getItemCount(): Int = cards.cards.size

}