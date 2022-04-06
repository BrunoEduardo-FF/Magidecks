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

class CardsAdapter(val cards: List<Card>,
                   val deleteItem: (Int) -> Unit,
                   val detailCard: (Card) -> Unit)
    : RecyclerView.Adapter<CardsAdapter.CardsAdapterViewHolder>() {

    class CardsAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val lblNome = itemView.findViewById<TextView>(R.id.recycler_cards_item_lbl_nome)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.recycler_cards_item_btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsAdapterViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_cards_item, parent, false)
        return CardsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardsAdapterViewHolder, position: Int) {
        val card = cards[position]
        holder.lblNome.text = card.name
        holder.btnDelete.setOnClickListener {
            deleteItem(position)
        }
        holder.itemView.setOnClickListener {
            detailCard(card)
        }
    }

    override fun getItemCount(): Int = cards.size

}