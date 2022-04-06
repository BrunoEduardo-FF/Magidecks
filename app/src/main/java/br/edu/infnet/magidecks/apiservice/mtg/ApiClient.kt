package br.edu.infnet.magidecks.apiservice.mtg

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var instance: Retrofit? = null

    private fun getInstance(): Retrofit {
        if (instance == null)
            instance = Retrofit.Builder() // conf o construtor
                .baseUrl("https://api.magicthegathering.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return instance as Retrofit
    }

    fun getCardsService() = getInstance().create(CardsService::class.java)
}