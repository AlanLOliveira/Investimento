package com.example.moedascambio.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.moedascambio.R
import com.example.moedascambio.Utils.formataMoedaBrasileira
import com.example.moedascambio.model.MoedaModel

class CompraVendaMoedas : BasicActivity() {

    private lateinit var tvCompraVendaDetalhes: TextView
    private lateinit var btnCompraVendaVoltarHome: Button
    private var trazerDadosMoeda: MoedaModel? = null
    private lateinit var tituloToolbar: TextView
    private var operacao : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_compra_venda_moedas)

        inicializaComponentes()
        btnCompraVendaVoltarHome()
        retornaResultadoDaMoeda()
        operacao?.let { visibilidadeBotaoToolbar(tvTitulo = findViewById(R.id.tv_Toolbar), it, toolbarT = findViewById(R.id.toolbar_CompraEVenda)) }
    }
    private fun retornaResultadoDaMoeda() {
        trazerDadosMoeda = intent.getSerializableExtra(CAMBIO) as MoedaModel
        trazerDadosMoeda?.let {
            operacao = intent.getStringExtra(OPERACAO)
            val quantidadeMoeda = intent.getIntExtra(QUANTIDADE, 0)
            val resultado = intent.getDoubleExtra(RESULTADO, 0.0)

            finalizaDetalhesCompraOuVenda(operacao, quantidadeMoeda,it, resultado)
        }
    }
    private fun finalizaDetalhesCompraOuVenda(
        operacao: String?,
        quantidadeMoeda: Int,
        moeda: MoedaModel,
        resultado: Double,
        ) {

        if (operacao == COMPRAR) {
             tvCompraVendaDetalhes.text = buildString {
                append("Parabéns!\n Você acabou de comprar \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                append("\n${formataMoedaBrasileira(resultado)}")
            }
        } else if (operacao == VENDER) {
             tvCompraVendaDetalhes.text =
                buildString {
                    append("Parabéns!\n Você acabou de vender \n$quantidadeMoeda ${moeda.isoMoeda} - ${moeda.nome_moeda},\ntotalizando")
                    append("\n${formataMoedaBrasileira(resultado)}")
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


