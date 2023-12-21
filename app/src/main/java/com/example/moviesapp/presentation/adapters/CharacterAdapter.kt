package com.example.moviesapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.domain.model.Character

class CharacterAdapter: ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val characterImg: ImageView = itemView.findViewById(R.id.img_character)
        private val characterName: TextView = itemView.findViewById(R.id.tv_character_name)
        private val characterStatus: TextView = itemView.findViewById(R.id.tv_character_status)

        fun bind(character: Character) {
            Glide.with(itemView.context)
                .load(character.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(characterImg)

            characterName.text = character.name
            characterStatus.text = itemView.context.getString(R.string.status, character.getFormattedStatus())
        }
    }

}