package ar.com.data.api

import ar.com.data.model.CharacterDTO
import ar.com.data.model.CharactersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharactersResponseDTO>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Int): Response<CharacterDTO>
}
