package br.edu.infnet.magidecks.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.infnet.magidecks.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AddDeckFragment : Fragment() {

    private lateinit var txtNome: EditText
    private lateinit var btnSalvar: Button
    private lateinit var viewModel: AddDeckViewModel
    val userId = Firebase.auth.currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_deck_fragment, container, false)
        setupWidgets(view)
        setupViewModel(view)
        return view
    }

    private fun setupViewModel(view: View) {
        viewModel = ViewModelProvider(this).get(AddDeckViewModel::class.java)
        viewModel.msg.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) Snackbar.make(view, it, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.status.observe(viewLifecycleOwner) {
            if (it) findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSalvar.setOnClickListener {
            val nomeDeck = txtNome.text.toString()
            if(!userId.isNullOrBlank()) viewModel.addDeck(userId, nomeDeck)
        }
    }

    private fun setupWidgets(view: View) {
        txtNome = view.findViewById(R.id.add_deck_fragment_txt_nome_deck)
        btnSalvar = view.findViewById(R.id.add_deck_fragment_btn_criar)
    }

}