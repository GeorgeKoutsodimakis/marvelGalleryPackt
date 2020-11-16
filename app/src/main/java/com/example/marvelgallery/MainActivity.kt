package com.example.marvelgallery

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelgallery.adapter.CharacterItemAdapter
import com.example.marvelgallery.adapter.MainListAdapter
import com.example.marvelgallery.data.models.ModelCharacter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val characters = listOf( // 1
        ModelCharacter(
            name = "3-D Man",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        ),
        ModelCharacter(
            name = "Abomination (Emil Blonsky)",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04.jpg"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // 2
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 3
        val categoryItemAdapters = characters
            .map(::CharacterItemAdapter) // 4
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

}