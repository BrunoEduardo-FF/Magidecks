package br.edu.infnet.magidecks.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.magidecks.R
import br.edu.infnet.magidecks.adapter.DecksAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class DecksFragment : Fragment() {

    private lateinit var viewModel: DecksViewModel
    private lateinit var recyclerDeck: RecyclerView
    private lateinit var fabAddDeck: FloatingActionButton
    val userId = Firebase.auth.currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.decks_fragment, container, false)
        setupWidgets(view)
        setupViewModel(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabAddDeck.setOnClickListener {
            findNavController().navigate(R.id.addDeckFragment)
        }
    }

    private fun setupViewModel(view: View) {
        viewModel = ViewModelProvider(this).get(DecksViewModel::class.java)
        if(!userId.isNullOrBlank()) viewModel.updateList(userId)
        viewModel.decks.observe(viewLifecycleOwner) {
            recyclerDeck.adapter = DecksAdapter(it, this::deleteItem, this::editItem, this::openDeck)
        }
        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setupWidgets(view: View) {
        recyclerDeck = view.findViewById(R.id.decks_fragment_recycler_decks)
        fabAddDeck = view.findViewById(R.id.decks_fragment_fab_add_deck)
    }

    private fun deleteItem(position: Int) {
        viewModel.deleteItem(position, userId!!)
    }

    private fun editItem(docId: String) {
        findNavController().navigate(
            R.id.editDeckFragment,
            bundleOf(getString(R.string.DOCUMENT_ID_REQUEST) to docId)
        )
    }

    private fun openDeck(docId: String, docName: String) {
        findNavController().navigate(
            R.id.cardsFragment,
            bundleOf(getString(R.string.DOCUMENT_ID_REQUEST) to docId, getString(R.string.DOCUMENT_NAME_REQUEST) to docName)
        )
    }
}