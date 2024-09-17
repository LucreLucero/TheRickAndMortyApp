package ar.com.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResponseDTO(
    @field:SerializedName("results")
    val results: List<CharacterDTO>
)
