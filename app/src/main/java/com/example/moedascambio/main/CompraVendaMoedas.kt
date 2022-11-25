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

    private lateinit var tvCompraVendaDetalhes: TextView
    private lateinit var btnCompraVendaVoltarHome: Button
    private lateinit var btnCompraVendaVoltarCambio: Button
    private var trazerDadosMoeda: MoedaModel? = null
    private lateinit var tituloToolbar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_compra_venda_moedas)

        inicializaComponentes()
        btnCompraVendaVoltarHome()
        retornaResultadoDaMoeda()
    }

    private fun retornaResultadoDaMoeda() {
        trazerDadosMoeda = intent.getSerializableExtra("cambio") as MoedaModel
        trazerDadosMoeda?.let {
            recebeInfoVendaOuCompra(it)
            btnCompraVendaVoltarCambio()
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
        ) {

        if (operacao == "compra") {
            tituloToolbar.text = getString(R.string.comprar)
            tvCompraVendaDetalhes.text = buildString {
                append("Parabéns!\n Você acabou de comprar \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                append("\n R$ ${(resultado).toBigDecimal().setScale(2, RoundingMode.UP)}")
            }
        } else if (operacao == "venda") {
            tituloToolbar.text = getString(R.string.vender)
            tvCompraVendaDetalhes.text =
                buildString {
                    append("Parabéns!\n Você acabou de vender \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                    append("\n R$ ${(resultado).toBigDecimal().setScale(2, RoundingMode.UP)}")
                }
        }
    }

    private fun inicializaComponentes() {
        tvCompraVendaDetalhes = findViewById(R.id.tv_CompraVenda_Resultado)
        btnCompraVendaVoltarHome = findViewById(R.id.btn_CompraVenda_Home)
        btnCompraVendaVoltarCambio = findViewById(R.id.btn_CompraVenda_VoltarCambio)
        tituloToolbar = findViewById(R.id.toolbar_title_cambio)
    }

    private fun btnCompraVendaVoltarHome() {
        btnCompraVendaVoltarHome.setOnClickListener {
            intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    private fun btnCompraVendaVoltarCambio() {
        btnCompraVendaVoltarCambio.setOnClickListener {
            finish()
        }
    }
}


