package ar.com.data.mappers

import ar.com.data.domain.Character
import ar.com.data.domain.CharactersResponse
import ar.com.data.model.CharacterDTO
import ar.com.data.model.CharactersResponseDTO

fun CharacterDTO.asDomainModel(): Character {
    return Character(id, name, status, species, type, gender, image, episode, created)
}

fun CharactersResponseDTO.asDomainModel(): CharactersResponse {
    return CharactersResponse(results.map { it.asDomainModel() })
}
