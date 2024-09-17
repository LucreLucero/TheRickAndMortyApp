package ar.com.therickandmortyapp.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ar.com.data.domain.Character
import ar.com.data.mappers.asDomainModel
import ar.com.data.model.CharacterDTO
import ar.com.data.model.CharactersResponseDTO
import ar.com.data.repository.IRickAndMortyRepository
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private var repository: IRickAndMortyRepository = mockk(relaxed = true)
    private val mockCharacterListObserver: Observer<List<Character>> = mockk(relaxed = true)
    private val mockCharacterObserver: Observer<Character> = mockk(relaxed = true)
    private val mockCurrentPageObserver: Observer<Int> = mockk(relaxed = true)

    private val currentPage = 1
    private val characterId = 1

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        mockkStatic(Log::class)
        every { Log.w(any(), any(), any()) } returns 0

        viewModel = HomeViewModel(repository)
        viewModel.characterList.observeForever(mockCharacterListObserver)
        viewModel.character.observeForever(mockCharacterObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun `get all characters onSuccess`() {
        val mockCharacterResponse = getDummyResponse().asDomainModel()
        coEvery { repository.getCharacters(1) } returns mockCharacterResponse

        viewModel.getCharacterList(currentPage)

        verify { mockCharacterListObserver.onChanged(mockCharacterResponse.results) }
        assertEquals(mockCharacterResponse.results, viewModel.characterList.value)
    }

    @Test
    fun `get character by id onSuccess`() {
        val mockCharacterResponse = getDummyCharacter()
        coEvery { repository.getCharacterById(characterId) } returns mockCharacterResponse

        viewModel.getCharacterById(characterId)

        verify { mockCharacterObserver.onChanged(mockCharacterResponse.asDomainModel()) }
        assertEquals(mockCharacterResponse.asDomainModel(), viewModel.character.value)
    }

    @Test
    fun `get previous page`() {
        viewModel.previousPage()

        assertEquals( viewModel.currentPage,1 )
    }

    @Test
    fun `get next page`() {
        viewModel.nextPage()

        assertEquals( viewModel.currentPage,2 )
    }

    private fun getDummyResponse() = CharactersResponseDTO(results = getDummyCharacterList())

    private fun getDummyCharacterList() = listOf(
        CharacterDTO(
            id = characterId,
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

    private fun getDummyCharacter() =
        CharacterDTO(
            id = characterId,
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
}
