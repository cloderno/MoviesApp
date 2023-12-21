package com.example.moviesapp.presentation.fragments

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
import com.example.moviesapp.utils.Resource
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
        }

        viewModel.characters.observe(viewLifecycleOwner, Observer { resource ->
            when(resource) {
                is Resource.Success -> {
                    characterAdapter.submitList(resource.data)
                    hideProgressBar()
                }
                is Resource.Error -> {
                    Toast.makeText(context, "error: ${resource.message}", Toast.LENGTH_LONG).show()
                    Log.d("TAG", "onViewCreated: " + resource.message)
                    hideProgressBar()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.loadCharacters()
    }

    private fun showProgressBar() {
        binding.progressBar.root.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.root.visibility = View.GONE
    }

}