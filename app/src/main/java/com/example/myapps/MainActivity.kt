package com.example.myapps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView

    private val list = ArrayList<Servant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1200)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()

    }

    private fun getListHeroes(): ArrayList<Servant> {
        val dataName = resources.getStringArray(R.array.servants_name_data)
        val dataDescription = resources.getStringArray(R.array.description_data)
        val dataPhoto = resources.getStringArray(R.array.servant_photos)
        val dataClass = resources.getStringArray(R.array.class_name_data)
        val listHero = ArrayList<Servant>()
        for (i in dataName.indices) {
            val hero = Servant(dataName[i], dataClass[i], dataPhoto[i], dataDescription[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListServantAdapter(list)
        rvHeroes.adapter = listHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
                val moveAbout = Intent(this@MainActivity, AboutMe::class.java)
                startActivity(moveAbout)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}