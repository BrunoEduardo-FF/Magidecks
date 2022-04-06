package br.edu.infnet.magidecks.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.edu.infnet.magidecks.R
import br.edu.infnet.magidecks.models.Card
import com.squareup.picasso.Picasso

class DetailCardFragment : Fragment() {

    private lateinit var lblNome: TextView
    private lateinit var imgFoto: ImageView
    private lateinit var lblManaCost: TextView
    private lateinit var lblType: TextView
    private lateinit var lblRarity: TextView
    private lateinit var lblPower: TextView
    private lateinit var lblTough: TextView
    private lateinit var lblText: TextView
    private lateinit var btnVoltar: Button
    private lateinit var card: Card

    private lateinit var viewModel: DetailCardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_card_fragment, container, false)
        card = arguments?.getSerializable(getString(R.string.CARD_REQUEST))!! as Card
        viewModel = ViewModelProvider(this).get(DetailCardViewModel::class.java)
        setupWidgets(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        btnVoltar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadData() {
        lblNome.text = card.name
        //Picasso.get().load(card.imageUrl).into(imgFoto)
        lblManaCost.text = card.manaCost
        lblType.text = card.type
        lblRarity.text = card.rarity
        lblPower.text = card.power
        lblTough.text = card.toughness
        lblText.text = card.text
    }

    private fun setupWidgets(view: View) {
        lblNome = view.findViewById(R.id.detail_card_fragment_lbl_card_name)
        imgFoto = view.findViewById(R.id.detail_card_fragment_card_image)
        lblManaCost = view.findViewById(R.id.detail_card_fragment_lbl_mana_cost)
        lblType = view.findViewById(R.id.detail_card_fragment_lbl_type)
        lblRarity = view.findViewById(R.id.detail_card_fragment_lbl_rarity)
        lblPower = view.findViewById(R.id.detail_card_fragment_lbl_power)
        lblTough = view.findViewById(R.id.detail_card_fragment_lbl_toughness)
        lblText = view.findViewById(R.id.detail_card_fragment_lbl_text)
        btnVoltar = view.findViewById(R.id.detail_card_fragment_btn_voltar)
    }

}