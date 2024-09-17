package ar.com.therickandmortyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import ar.com.data.domain.Character
import ar.com.therickandmortyapp.R
import ar.com.therickandmortyapp.adapter.CharacterRecyclerAdapter
import ar.com.therickandmortyapp.databinding.FragmentHomeBinding
import ar.com.therickandmortyapp.ext.navigate
import ar.com.therickandmortyapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var charactersAdapter: CharacterRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpObservers()
        setUpUi()
        setupRecyclerView(binding.charactersRecycler)

        return binding.root
    }

    private fun setUpUi() {
        updatePreviousButtonVisibility()

        binding.tvPreviousPage.setOnClickListener {
            viewModel.previousPage()
        }
        binding.tvNextPage.setOnClickListener {
            viewModel.nextPage()
        }
    }

    private fun setupRecyclerView(characterRecycler: RecyclerView) {
        charactersAdapter = CharacterRecyclerAdapter(
            requireContext(), listOf(), ::navigate
        )
        characterRecycler.adapter = charactersAdapter
    }

    private fun setUpObservers() {
        viewModel.characterList.observe(viewLifecycleOwner) {
            charactersAdapter.characterList = it
            charactersAdapter.notifyDataSetChanged()

            updatePreviousButtonVisibility()
        }

        viewModel.character.observe(viewLifecycleOwner) {
            navigateToCharacterDetail(it)
        }
    }

    private fun updatePreviousButtonVisibility() {
        binding.tvPreviousPage.visibility =
            if (viewModel.currentPage == 1) View.GONE else View.VISIBLE
    }

    private fun navigate(character: Character) {
        viewModel.getCharacterById(character.id)
    }

    private fun navigateToCharacterDetail(character: Character) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(character)
        navigate(action)
    }
}
