package com.example.moviesapp.presentation

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.presentation.fragments.CharactersFragment
import com.example.moviesapp.presentation.fragments.EpisodesFragment
import com.example.moviesapp.presentation.fragments.FavouritesFragment
import com.example.moviesapp.presentation.fragments.LocationsFragment
import com.example.moviesapp.utils.constants.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        replaceFragment(CharactersFragment(), Navigation.CHARACTERS)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.menu_characters -> {
                    replaceFragment(CharactersFragment(), Navigation.CHARACTERS)
                    true
                }
                R.id.menu_episodes -> {
                    replaceFragment(EpisodesFragment(), Navigation.EPISODES)
                    true
                }
                R.id.menu_locations -> {
                    replaceFragment(LocationsFragment(), Navigation.LOCATIONS)
                    true
                }
                R.id.menu_favourites -> {
                    replaceFragment(FavouritesFragment(), Navigation.FAVOURITES)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        supportFragmentManager.apply {
            beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        replaceTitle(title)
    }

    private fun replaceTitle(title: String) {
        // note: please consider using lower cased title definitions, to make it searchable from strings
        try {
            val resourceId = resources.getIdentifier(title, "string", packageName)
            binding.toolBar.title = getString(resourceId)
        } catch (e: Resources.NotFoundException) {
            Log.e("MainActivity", "Invalid titleResId: $title", e)
        }
    }
}