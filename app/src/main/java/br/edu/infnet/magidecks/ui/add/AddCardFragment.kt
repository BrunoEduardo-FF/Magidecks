package br.edu.infnet.magidecks.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.magidecks.R
import br.edu.infnet.magidecks.adapter.SearchCardsAdapter
import br.edu.infnet.magidecks.models.Card
import com.google.android.material.snackbar.Snackbar

class AddCardFragment : Fragment() {

    private lateinit var viewModel: AddCardViewModel
    private lateinit var recyclerApiCards: RecyclerView
    private lateinit var btnBuscar: Button
    private lateinit var txtName: EditText
    private lateinit var deckId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_card_fragment, container, false)
        deckId = arguments?.getString(getString(R.string.DOCUMENT_ID_REQUEST))!!
        setupWidgets(view)
        setupViewModel(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBuscar.setOnClickListener {
            viewModel.searchCards(txtName.text.toString())
        }
    }

    private fun setupWidgets(view: View) {
        recyclerApiCards = view.findViewById(R.id.add_card_fragment_recycler_cards)
        btnBuscar = view.findViewById(R.id.add_card_fragment_btn_buscar)
        txtName = view.findViewById(R.id.add_card_fragment_txt_nome_carta_buscar)
    }

    private fun setupViewModel(view: View) {
        viewModel = ViewModelProvider(this).get(AddCardViewModel::class.java)
        viewModel.cards.observe(viewLifecycleOwner) {
            recyclerApiCards.adapter = SearchCardsAdapter(it, this::addCardToDeck, this::detailCard)
        }
        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    fun addCardToDeck(card: Card) {
        viewModel.addCardToDeck(card, deckId)
    }

    fun detailCard(card: Card) {
        findNavController().navigate(R.id.detailCardFragment,
            bundleOf(getString(R.string.CARD_REQUEST) to card))
    }

}