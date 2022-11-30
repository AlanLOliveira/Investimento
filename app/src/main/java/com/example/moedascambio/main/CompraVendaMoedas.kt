package com.example.moedascambio.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.moedascambio.R
import com.example.moedascambio.model.MoedaModel
import java.text.NumberFormat
import java.util.*

class CompraVendaMoedas : AppCompatActivity() {

    private lateinit var tvCompraVendaDetalhes: TextView
    private lateinit var btnCompraVendaVoltarHome: Button
    private var trazerDadosMoeda: MoedaModel? = null
    private lateinit var tituloToolbar: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_compra_venda_moedas)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_CompraEVenda)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        inicializaComponentes()
        btnCompraVendaVoltarHome()
        retornaResultadoDaMoeda()
    }

    private fun retornaResultadoDaMoeda() {
        trazerDadosMoeda = intent.getSerializableExtra("cambio") as MoedaModel
        trazerDadosMoeda?.let {
            recebeInfoVendaOuCompra(it)
        }
    }

    private fun recebeInfoVendaOuCompra(moeda: MoedaModel) {
        val operacao = intent.getStringExtra("operacao")
        val quantidadeMoeda = intent.getIntExtra("quantidade", 0)
        val resultado = intent.getDoubleExtra("resultado", 0.0)

        configuraExibicaoCompraOuVenda(operacao, quantidadeMoeda, moeda, resultado)
    }

    private fun configuraExibicaoCompraOuVenda(
        operacao: String?,
        quantidadeMoeda: Int,
        moeda: MoedaModel,
        resultado: Double,
        brasil : Locale = Locale("pt", "BR"),
        br : NumberFormat = NumberFormat.getCurrencyInstance(brasil)
        ) {

        if (operacao == "compra") {
            tituloToolbar.text = getString(R.string.comprar)
            tvCompraVendaDetalhes.text = buildString {
                append("Parabéns!\n Você acabou de comprar \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                append("\n${br.format(resultado)}")
            }
        } else if (operacao == "venda") {
            tituloToolbar.text = getString(R.string.vender)
            tvCompraVendaDetalhes.text =
                buildString {
                    append("Parabéns!\n Você acabou de vender \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                    append("\n${br.format(resultado)}")
                }
        }
    }

    private fun inicializaComponentes() {
        tvCompraVendaDetalhes = findViewById(R.id.tv_CompraVenda_Resultado)
        btnCompraVendaVoltarHome = findViewById(R.id.btn_CompraVenda_Home)
        tituloToolbar = findViewById(R.id.tv_Toolbar)
    }

    private fun btnCompraVendaVoltarHome() {
        btnCompraVendaVoltarHome.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}


