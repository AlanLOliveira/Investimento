package com.example.moedascambio.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moedascambio.R
import com.example.moedascambio.adapter.MoedaAdapter
import com.example.moedascambio.repository.RepositorioMoeda
import com.example.moedascambio.viewmodel.MoedaViewModel
import com.example.moedascambio.viewmodel.ViewModelIF

class HomeActivity : AppCompatActivity() {

    lateinit var rvMoedas: RecyclerView
    lateinit var moedaViewModel: MoedaViewModel
    private val moedaAdapter by lazy {
        MoedaAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_home)


// Instancia viewmodel com a lista

        moedaViewModel = ViewModelProvider(this, ViewModelIF(RepositorioMoeda()))[MoedaViewModel::class.java]
        moedaViewModel.listaDeMoedas.observe(this) {
            moedaAdapter.refresh(it)

            configuraRecyclerView()

        }
        //atualizar as moedas
        moedaViewModel.atualizaMoedas()

        moedaViewModel.errorTest.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }

    }

    private fun configuraRecyclerView() {
        rvMoedas = findViewById(R.id.rv_Moedas)
        rvMoedas.layoutManager = LinearLayoutManager(this)
        rvMoedas.adapter = moedaAdapter
        moedaAdapter.onclick = {
            Intent(this, TelaDeCambio::class.java).apply {
                putExtra("cambio", it)
                startActivity(this)
            }
        }

    }


}





