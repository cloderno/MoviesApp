package com.example.moviesapp.presentation.view.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentCharacterDetailBinding
import com.example.moviesapp.databinding.FragmentCharactersBinding
import com.example.moviesapp.domain.model.Character
import com.example.moviesapp.presentation.viewmodel.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    // init viewmodel
    // init binding
    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId: Int = arguments?.getInt("characterId", 1) ?: 1

        viewModel.getCharacterById(characterId)

        viewModel.characterDetailState.observe(viewLifecycleOwner) { state ->
            if (state.isLoading) {
                // Show loading indicator
                showProgressBar()
            } else if (!state.error.isNullOrEmpty()) {
                // Show error message
                Toast.makeText(context, "error: ${state.error}", Toast.LENGTH_LONG).show()
                Log.d("TAG", "onViewCreated: " + state.error)
                hideProgressBar()
            } else {
                // Update UI with characters list (state.characters)

                populateData(state.character)

                hideProgressBar()
            }
        }

        binding.floatingButton.setOnClickListener {
            Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show()


            binding.floatingButton.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_heart_green))
            binding.floatingButton.foregroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.nephritis))
        }
    }

    private fun populateData(character: Character?) {
        view?.let {
            Glide.with(it)
                .load(character!!.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.imgCharacter)
        }

        binding.tvCharacterName.text        = getString(R.string.name, character?.name ?: "Unknown")
        binding.tvCharacterGender.text      = getString(R.string.gender, character!!.getFormattedGender())
        binding.tvCharacterOrigin.text      = getString(R.string.origin, character?.origin?.name ?: "Unknown")
        binding.tvCharacterLocation.text    = getString(R.string.location, character?.location?.name ?: "Unknown")
        binding.tvCharacterSpecies.text     = getString(R.string.species, character?.species ?: "Unknown")
        binding.tvCharacterStatus.text      = getString(R.string.status, character!!.getFormattedStatus())
    }

    private fun showProgressBar() {
        binding.progressBar.root.visibility = View.VISIBLE
        binding.layout.visibility = View.GONE
        binding.floatingButton.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.layout.visibility = View.VISIBLE
        binding.floatingButton.visibility = View.VISIBLE
        binding.progressBar.root.visibility = View.GONE
    }

}