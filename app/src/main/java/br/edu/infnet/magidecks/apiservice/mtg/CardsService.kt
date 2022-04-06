package br.edu.infnet.magidecks.apiservice.mtg
import br.edu.infnet.magidecks.models.Card
import br.edu.infnet.magidecks.models.Cards
import retrofit2.http.GET
import retrofit2.http.Query

interface CardsService {

    @GET("/v1/cards")
    suspend fun list(@Query("name") name: String = ""): Cards
}