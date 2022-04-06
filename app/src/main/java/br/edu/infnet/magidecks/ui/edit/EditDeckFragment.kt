package br.edu.infnet.magidecks.ui.edit

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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EditDeckFragment : Fragment() {

    private lateinit var txtNome: EditText
    private lateinit var btnSalvar: Button
    private lateinit var viewModel: EditDeckViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.edit_deck_fragment, container, false)
        setupWidgets(view)
        setupViewModel()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSalvar.setOnClickListener {
            val nome = txtNome.text.toString()
            viewModel.salvarEdicao(nome)
            findNavController().popBackStack()
        }
    }

    private fun setupViewModel() {
        val docId = arguments?.getString(getString(R.string.DOCUMENT_ID_REQUEST))
        viewModel = ViewModelProvider(this).get(EditDeckViewModel::class.java)
        if (!docId.isNullOrBlank()) viewModel.getDeck(docId)
        viewModel.deck.observe(viewLifecycleOwner) {
            txtNome.setText(it.nome)
        }
    }

    private fun setupWidgets(view: View) {
        txtNome = view.findViewById(R.id.edit_deck_fragment_txt_nome_deck)
        btnSalvar = view.findViewById(R.id.edit_deck_fragment_btn_salvar)
    }

}