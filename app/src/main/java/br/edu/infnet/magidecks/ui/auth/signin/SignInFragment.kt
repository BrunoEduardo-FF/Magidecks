package br.edu.infnet.magidecks.ui.auth.signin

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.infnet.magidecks.R

class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel
    private lateinit var txtEmail: EditText
    private lateinit var txtSenha: EditText
    private lateinit var btnAcessar: Button
    private lateinit var cbLembrar: CheckBox
    private lateinit var lblCadastrarse: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sign_in_fragment, container, false)
        setupViewModel()
        setupWidgets(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verificarPrefUserEmail()
        loadListeners()
    }

    private fun loadListeners() {
        btnAcessar.setOnClickListener {
            val email = txtEmail.text.toString()
            val pass = txtSenha.text.toString()
            if (email.isNotBlank() && pass.isNotBlank()) {
                viewModel.signIn(email, pass)
                if (cbLembrar.isChecked) {
                    salvarPrefUserEmail(email)
                }
            } else {
                makeToast(getString(R.string.faltaEmailSenha))
            }
        }
        lblCadastrarse.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    private fun setupWidgets(view: View) {
        txtEmail = view.findViewById(R.id.sign_in_fragment_txt_email)
        txtSenha = view.findViewById(R.id.sign_in_fragment_txt_senha)
        lblCadastrarse = view.findViewById(R.id.sign_in_fragment_lbl_cadastrarse)
        btnAcessar = view.findViewById(R.id.sign_in_fragment_btn_acessar)
        cbLembrar = view.findViewById(R.id.sign_in_fragment_cb_lembrar_email)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        viewModel.status.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.decksFragment)
            }
        }
        viewModel.msg.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                makeToast(it)
            }
        }
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun salvarPrefUserEmail(email: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        val editPref = sharedPref.edit()
        editPref.putString("user_email", email)
        Log.d("Email Preference", "set user_email")
        editPref.apply()

    }

    private fun verificarPrefUserEmail() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val email = sharedPref?.getString("user_email", null)
        txtEmail.setText(email)
    }

}