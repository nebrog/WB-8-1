package com.example.wb81.ui.heroes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wb81.App
import com.example.wb81.R
import com.example.wb81.data.model.HeroesItem
import com.example.wb81.ui.hero.FragmentHero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.IOException

@ExperimentalStdlibApi

class HeroesFragment : Fragment(), OnHeroClickListener {


    private val adapter = HeroesAdapter(this)
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_heroes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val repository = (requireContext().applicationContext as App).repository
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
        scope.launch {
            try {
                adapter.setData(repository.getHeroes())
            } catch (e: IllegalStateException) {
                Log.e("Nebrog", e.message, e)
                Toast.makeText(context, e.message.orEmpty(), Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Log.e("Nebrog", e.message, e)
                Toast.makeText(
                    context,
                    "Отсутсвует подключение к интеренету",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    override fun onClick(item: HeroesItem) {
        val bundle = Bundle()
        bundle.putSerializable(HeroesActivity.HERO_ARG, item)
        val heroFragment = FragmentHero()
        heroFragment.arguments = bundle
        val transaction = parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, heroFragment)
            .addToBackStack(null)
            .commit()

    }

}