package ar.com.data.repository

import ar.com.data.api.RickAndMortyApi
import ar.com.data.domain.CharactersResponse
import ar.com.data.mappers.asDomainModel
import ar.com.data.model.CharacterDTO
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val api: RickAndMortyApi
) : IRickAndMortyRepository {

    override suspend fun getCharacters(page: Int): CharactersResponse {
        val response = api.getCharacters(page)

        when (response.code()) {
            in 200..299 -> return response.body()?.asDomainModel() ?: throw Exception()
            else -> throw Exception()
        }
    }

    override suspend fun getCharacterById(characterId: Int): CharacterDTO {
        val response = api.getCharacterById(characterId)

        when (response.code()) {
            in 200..299 -> return response.body() ?: throw Exception()
            else -> throw Exception()
        }
    }
}
