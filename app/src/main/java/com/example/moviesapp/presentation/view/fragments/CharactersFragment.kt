package com.example.moviesapp.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentCharactersBinding
import com.example.moviesapp.presentation.adapters.CharacterAdapter
import com.example.moviesapp.presentation.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var binding: FragmentCharactersBinding
    private val characterAdapter = CharacterAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter

            characterAdapter.onItemClickListener = { character ->

                // Replace the fragment from the CharactersFragment
                val newFragment = CharacterDetailFragment()

                // Pass the characterId to the new fragment
                val bundle = Bundle().apply {
                    putInt("characterId", character.id)
                }
                newFragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit()

            }
        }

        viewModel.characterListState.observe(viewLifecycleOwner, Observer { state ->
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
                characterAdapter.submitList(state.characters)
                hideProgressBar()
            }

//            when(resource) {
//                is Resource.Success -> {
//                    characterAdapter.submitList(resource.data)
//                    hideProgressBar()
//                }
//                is Resource.Error -> {
//                    Toast.makeText(context, "error: ${resource.message}", Toast.LENGTH_LONG).show()
//                    Log.d("TAG", "onViewCreated: " + resource.message)
//                    hideProgressBar()
//                }
//                is Resource.Loading -> {
//                    showProgressBar()
//                }
//            }
        })

    }

    private fun showProgressBar() {
        binding.progressBar.root.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.root.visibility = View.GONE
    }

}