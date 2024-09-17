package ar.com.therickandmortyapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ar.com.data.domain.Character
import ar.com.therickandmortyapp.R
import ar.com.therickandmortyapp.databinding.FragmentCharacterDetailsBinding
import ar.com.therickandmortyapp.databinding.FragmentCharacterDetailsBinding.bind
import ar.com.therickandmortyapp.ext.navigate
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details) {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val character: Character
        get() = args.character

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        setUpUi()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            handleBackNavigation()
        }
    }

    private fun setUpUi() {
        binding.apply {
            Glide.with(requireContext())
                .load(character.image)
                .into(ivCharacter)

            tvName.text = character.name
            tvCreationInfo.text = getString(R.string.character_creation_date, character.created)
            tvStatus.text =
                getString(
                    R.string.character_type_description,
                    character.status,
                    character.type
                )
            tvGender.text = getString(R.string.character_gender_description, character.gender)

            val episodes = character.episode.joinToString(separator = "\n") { it }
            tvEpisodeList.text = episodes
        }
    }

    private fun handleBackNavigation() {
        navigate(CharacterDetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
    }
}
