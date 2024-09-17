package ar.com.data.repository

import ar.com.data.api.RickAndMortyApi
import ar.com.data.model.CharacterDTO
import ar.com.data.model.CharactersResponseDTO
import ar.com.data.repository.RickAndMortyRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

class RickAndMortyRepositoryTest {
    private val api: RickAndMortyApi = mockk()
    private val repository = RickAndMortyRepository(api)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get character list onSuccess`() = runTest {
        val mockCharacterResponse = getDummyResponseDTO()
        coEvery { api.getCharacters(any()) } answers { mockCharacterResponse }

        repository.getCharacters(1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get character by id onSuccess`() = runTest {
        val mockCharacterResponse = getDummyCharacterDTO()
        coEvery { api.getCharacterById(any()) } answers { mockCharacterResponse }

        repository.getCharacterById(1)
    }

    private fun getDummyResponseDTO(): Response<CharactersResponseDTO> {
        return Response.success(CharactersResponseDTO(results = getDummyCharacterList()))
    }

    private fun getDummyCharacterList() = listOf(
        CharacterDTO(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
                "https://rickandmortyapi.com/api/episode/3",
                "https://rickandmortyapi.com/api/episode/4",
                "https://rickandmortyapi.com/api/episode/5"
            ),
            created = "2017-11-04T18:48:46.250Z"
        )
    )

    private fun getDummyCharacterDTO(): Response<CharacterDTO> {
        return Response.success(
            CharacterDTO(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                type = "",
                gender = "Male",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                episode = listOf(
                    "https://rickandmortyapi.com/api/episode/1",
                    "https://rickandmortyapi.com/api/episode/2",
                    "https://rickandmortyapi.com/api/episode/3",
                    "https://rickandmortyapi.com/api/episode/4",
                    "https://rickandmortyapi.com/api/episode/5"
                ),
                created = "2017-11-04T18:48:46.250Z"
            )
        )
    }
}
