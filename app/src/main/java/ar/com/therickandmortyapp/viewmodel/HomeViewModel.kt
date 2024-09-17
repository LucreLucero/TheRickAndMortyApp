package ar.com.therickandmortyapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.data.domain.Character
import ar.com.data.mappers.asDomainModel
import ar.com.data.repository.IRickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IRickAndMortyRepository) :
    ViewModel() {

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character>
        get() = _character

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>>
        get() = _characterList

    private var _currentPage = 1
    val currentPage: Int get() = _currentPage

    init {
        getCharacterList(_currentPage)
    }

    fun getCharacterList(page: Int) {
        viewModelScope.launch {
            runCatching {
                repository.getCharacters(page)
            }.onSuccess { response ->
                _characterList.postValue(response.results.map { it })
            }.onFailure {
                Log.w(
                    HomeViewModel::class.simpleName,
                    "Cannot get the list of characters: exception ${it.message}", it
                )
            }
        }
    }

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            runCatching {
                repository.getCharacterById(characterId)
            }.onSuccess { response ->
                response.let { _character.postValue(it.asDomainModel()) }
            }.onFailure {
                Log.w(
                    HomeViewModel::class.simpleName,
                    "Cannot get selected character: exception ${it.message}", it
                )
            }
        }
    }

    fun previousPage() {
        if (_currentPage != 1) {
            _currentPage--

            getCharacterList(_currentPage)
        }
    }

    fun nextPage() {
        _currentPage++
        getCharacterList(_currentPage)
    }
}
