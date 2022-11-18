package com.example.moedascambio.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.moedascambio.R
import com.example.moedascambio.model.MoedaModel
import java.math.RoundingMode

class CompraVendaMoedas : AppCompatActivity() {



    private lateinit var tvTelaCompraVendaDetalhes: TextView
    private lateinit var btnTelaCompraVendaVoltarHome: Button
    private lateinit var btnTelaCompraVendaVoltarCambio: Button
    private var trazerDadosMoeda : MoedaModel? = null
    private lateinit var tituloToolbar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_compra_venda_moedas)

        inicializaComponentes()
        btnTelaCompraVendaVoltarHome()

        trazerDadosMoeda = intent.getSerializableExtra("cambio") as MoedaModel
        trazerDadosMoeda?.let {
            escolherVendaOuCompra(it)
            btnTelaCompraVendaVoltarCambio()
        }

   }

    private fun escolherVendaOuCompra(moeda: MoedaModel){
        val compra = intent.getBooleanExtra("compra", false)
        val venda = intent.getBooleanExtra("venda", false)
        val quantidadeMoeda = intent.getIntExtra("quantidade", 0)
        val resultado = intent.getDoubleExtra("resultado", 0.0)

        if (compra) {
            tituloToolbar.text = getString(R.string.comprar)
            tvTelaCompraVendaDetalhes.text = buildString {
                append("Parabéns!\n Você acabou de comprar \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                append("\n R$ ${(resultado).toBigDecimal().setScale(2, RoundingMode.UP)}")
            }
        } else if (venda) {
            tituloToolbar.text = getString(R.string.vender)
            tvTelaCompraVendaDetalhes.text =
                buildString {
                    append("Parabéns!\n Você acabou de vender \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                    append("\n R$ ${(resultado).toBigDecimal().setScale(2, RoundingMode.UP)}")
                }
        }
    }

    private fun inicializaComponentes() {
        tvTelaCompraVendaDetalhes = findViewById(R.id.tv_TelaCompraVenda_Resultado)
        btnTelaCompraVendaVoltarHome = findViewById(R.id.btn_TelaCompraVenda_Home)
        btnTelaCompraVendaVoltarCambio = findViewById(R.id.btn_TelaCompraVenda_VoltarCambio)
        tituloToolbar = findViewById(R.id.toolbar_title_cambio)
    }

    private fun btnTelaCompraVendaVoltarHome() {
        btnTelaCompraVendaVoltarHome.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
    private fun btnTelaCompraVendaVoltarCambio() {
        btnTelaCompraVendaVoltarCambio.setOnClickListener {
                    finish()
            }
        }


}


