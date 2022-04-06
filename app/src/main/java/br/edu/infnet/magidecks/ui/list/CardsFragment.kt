package br.edu.infnet.magidecks.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.magidecks.R
import br.edu.infnet.magidecks.adapter.CardsAdapter
import br.edu.infnet.magidecks.adapter.DecksAdapter
import br.edu.infnet.magidecks.models.Card
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class CardsFragment : Fragment() {

    private lateinit var viewModel: CardsViewModel
    private lateinit var recyclerCards: RecyclerView
    private lateinit var lblDeckName: TextView
    private lateinit var fabAddCard: FloatingActionButton
    private lateinit var docId: String
    private lateinit var docName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cards_fragment, container, false)
        docId = arguments?.getString(getString(R.string.DOCUMENT_ID_REQUEST))!!
        docName = arguments?.getString(getString(R.string.DOCUMENT_NAME_REQUEST))!!
        setupWidgets(view)
        setupViewModel(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lblDeckName.text = docName
        fabAddCard.setOnClickListener {
            findNavController().navigate(R.id.addCardFragment,
                bundleOf(getString(R.string.DOCUMENT_ID_REQUEST) to docId)
            )
        }
    }

    private fun setupWidgets(view: View) {
        recyclerCards = view.findViewById(R.id.cards_fragment_recycler_cards)
        fabAddCard = view.findViewById(R.id.cards_fragment_fab_add_card)
        lblDeckName = view.findViewById(R.id.cards_fragment_lbl_deck_name)
    }


    private fun setupViewModel(view: View) {
        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        //viewModel.updateCards(docId)
        viewModel.setupListener(docId)
        viewModel.cards.observe(viewLifecycleOwner) {
            recyclerCards.adapter = CardsAdapter(it, this::deleteItem, this::detailCard)
        }
        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun deleteItem(index: Int) {
        viewModel.deleteItem(index)
    }

    fun detailCard(card: Card) {
        findNavController().navigate(R.id.detailCardFragment,
            bundleOf(getString(R.string.CARD_REQUEST) to card))
    }

}