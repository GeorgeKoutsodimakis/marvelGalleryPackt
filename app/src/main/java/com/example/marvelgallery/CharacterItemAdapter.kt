package com.example.marvelgallery

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelgallery.extensions.bindView
import com.example.marvelgallery.extensions.loadImage
import com.example.marvelgallery.models.ModelCharachter
import com.example.marvelgallery.recyclerAstraction.ItemAdapter

class CharacterItemAdapter(
    private val character: ModelCharachter
) : ItemAdapter<CharacterItemAdapter.ViewHolder>(R.layout.item_character) {
    override fun onCreateViewHolder(itemView: View): ViewHolder = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        textView.text = character.name
        imageView.loadImage(character.image)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView by bindView(R.id.textView)
        val imageView: ImageView by bindView(R.id.imageView)
    }

}

