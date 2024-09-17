package ar.com.data.repository

import ar.com.data.domain.CharactersResponse
import ar.com.data.model.CharacterDTO

interface IRickAndMortyRepository {
    suspend fun getCharacters(page: Int): CharactersResponse
    suspend fun getCharacterById(characterId: Int): CharacterDTO
}
