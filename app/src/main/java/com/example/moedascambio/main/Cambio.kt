package com.example.moedascambio.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.moedascambio.R
import com.example.moedascambio.SingletonSimulandoValores.buscaValorSimulado
import com.example.moedascambio.SingletonSimulandoValores.modificaValorPosOperacao
import com.example.moedascambio.SingletonSimulandoValores.totalsaldodisnponivel
import com.example.moedascambio.Utils.alteraCorDaVariacaoDaMoeda
import com.example.moedascambio.model.MoedaModel
import java.math.RoundingMode

class Cambio : AppCompatActivity() {

    private var moedaModel: MoedaModel? = null
    private lateinit var tvCambioSiglaEMoeda: TextView
    private lateinit var tvCambioVariacaoDaMoeda: TextView
    private lateinit var tvCambioValorCompraMoeda: TextView
    private lateinit var tvCambioValorVendaMoeda: TextView
    private lateinit var tvCamioSaldoDisponivel: TextView
    private lateinit var tvCambioSaldoEmCaixa: TextView
    private lateinit var btnCambioVoltarHome: Button
    private lateinit var btnCambioVender: Button
    private lateinit var btnCambioComprar: Button
    private lateinit var etxtCambioQuanditaDeMoeda: EditText
    private var quantidadeMoeda: Int = 0
    private var resultado: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_de_cambio)

        inicializaComponentes()
        btnCorAtivadoOuDesativado(boolean = false, btnCambioComprar)
        btnCorAtivadoOuDesativado(boolean = false, btnCambioVender)
        trazInformacaoMoedaParaTelaDeCambio()
        cliqueVoltarHome()
    }

    private fun setOnClickListenner() {
        btnCambioComprar.setOnClickListener {
            btnCalcularCompraMoeda()
        }
        btnCambioVender.setOnClickListener {
            btnCalcularVendaMoeda()
        }
    }

    private fun cliqueVoltarHome() {
        btnCambioVoltarHome.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        etxtCambioQuanditaDeMoeda.text.clear()
        trazInformacaoMoedaParaTelaDeCambio()
    }

    private fun trazInformacaoMoedaParaTelaDeCambio() {
        moedaModel = intent.getSerializableExtra("cambio") as? MoedaModel
        moedaModel?.let {

            preencherCamposNaTelaCambio(it)
            alteraCorDaVariacaoDaMoeda(moedaModel = it, tvCambioVariacaoDaMoeda)
            execucaoBtnVendaECompra(moedaModel = it)
        }
    }

    private fun preencherCamposNaTelaCambio(it: MoedaModel) {
        if (moedaModel?.totalsaldocaixa == 0) {
            buscaValorSimulado(it)
        }
        tvCambioSiglaEMoeda.text = buildString {
            append(it.isoMoeda + " - " + it.nome_moeda)

        }
        tvCambioVariacaoDaMoeda.text =
            buildString {
                append(it.variacao_moeda.toString().toBigDecimal().setScale(2, RoundingMode.UP))
                append("%")
            }
        tvCambioValorCompraMoeda.text =
            buildString {
                append("Compra: " + "R$  " + (it.valor_compra).toString().toBigDecimal().setScale(2,
                    RoundingMode.UP))
            }
        tvCambioValorVendaMoeda.text = buildString {
            append("Venda: " + "R$  " + (it.valor_venda).toString().toBigDecimal()
                .setScale(2, RoundingMode.UP))
        }
        tvCambioSaldoEmCaixa.text = buildString {
            append((moedaModel?.totalsaldocaixa))
            append(" ")
            append(it.nome_moeda)
            append(" em caixa")
        }
        tvCamioSaldoDisponivel.text = buildString {
            append("Saldo disponível: R$ " + ((totalsaldodisnponivel).toBigDecimal()
                .setScale(2, RoundingMode.UP)))
        }
    }

    private fun inicializaComponentes() {
        tvCambioSiglaEMoeda = findViewById(R.id.tv_Cambio_SiglaEMoeda)
        tvCambioVariacaoDaMoeda = findViewById(R.id.tv_Cambio_Variacao)
        tvCambioValorCompraMoeda = findViewById(R.id.tv_Cambio_ValorCompra)
        tvCambioValorVendaMoeda = findViewById(R.id.tv_Cambio_ValorVenda)
        btnCambioVoltarHome = findViewById(R.id.btn_CompraVenda_VoltarCambio)
        btnCambioVender = findViewById(R.id.btn_CompraVenda_Home)
        btnCambioComprar = findViewById(R.id.btn_Cambio_Comprar)
        etxtCambioQuanditaDeMoeda = findViewById(R.id.etxt_Cambio_QuantidadeMoeda)
        tvCamioSaldoDisponivel = findViewById(R.id.tv_Cambio_SdDisponivel)
        tvCambioSaldoEmCaixa = findViewById(R.id.tv_Cambio_SdCaixa)
    }

    private fun btnCalcularVendaMoeda() {
        calculaVendaDaMoeda()
        moedaModel?.let { modificaValorPosOperacao(it) }
    }

    private fun btnCalcularCompraMoeda() {
        calculaCompraDaMoeda()
        moedaModel?.let { modificaValorPosOperacao(it) }
    }
    
    private fun calculaVendaDaMoeda() {
        val valorVendaMoeda = moedaModel?.valor_venda.toString().toDouble()
        quantidadeMoeda = etxtCambioQuanditaDeMoeda.text.toString().toInt()
        resultado = valorVendaMoeda * quantidadeMoeda
        totalsaldodisnponivel = (totalsaldodisnponivel + resultado)
        moedaModel?.totalsaldocaixa?.let {
            moedaModel?.totalsaldocaixa = (it - quantidadeMoeda)

        }
        enviarDadosCompraEVendaMoedas("venda")
    }
    private fun calculaCompraDaMoeda() {
        val valorCompraMoeda = moedaModel?.valor_compra.toString().toDouble()
        quantidadeMoeda = etxtCambioQuanditaDeMoeda.text.toString().toInt()
        resultado = (valorCompraMoeda * quantidadeMoeda)
        totalsaldodisnponivel = (totalsaldodisnponivel - resultado)
        moedaModel?.totalsaldocaixa?.let {
            moedaModel?.totalsaldocaixa = (it + quantidadeMoeda)
        }
        enviarDadosCompraEVendaMoedas("compra")
    }
    
    private fun execucaoBtnVendaECompra(moedaModel: MoedaModel) {
        etxtCambioQuanditaDeMoeda.doOnTextChanged { text, _, _, _ ->
            if (text.toString().isNotBlank()) {
                val ativarbtn = text.toString().toInt()
                if (ativarbtn > 0) {
                    validaFuncaoAtivarBtnCompra(moedaModel, ativarbtn)
                    validaFuncaoAtivarBtnVenda(moedaModel, ativarbtn)
                    setOnClickListenner()
                }
            } else {
                btnCorAtivadoOuDesativado(boolean = false, btnCambioComprar)
                btnCorAtivadoOuDesativado(boolean = false, btnCambioVender)
            }
        }
    }

    private fun validaFuncaoAtivarBtnVenda(
        moedaModel: MoedaModel,
        ativarbtn: Int,
    ) {
        if (moedaModel.valor_venda != 0.0) {
            if (ativarbtn <= moedaModel.totalsaldocaixa) {
                btnCorAtivadoOuDesativado(boolean = true, btnCambioVender)
            } else {
                btnCorAtivadoOuDesativado(boolean = false, btnCambioVender)
            }
        }
    }
    private fun validaFuncaoAtivarBtnCompra(
        moedaModel: MoedaModel,
        ativarbtn: Int,
    ) {
        if (moedaModel.valor_compra != 0.0) {
            if (ativarbtn * moedaModel.valor_compra <= totalsaldodisnponivel) {
                btnCorAtivadoOuDesativado(boolean = true, btnCambioComprar)
            } else {
                btnCorAtivadoOuDesativado(boolean = false, btnCambioComprar)
            }
        }
    }
   
    private fun btnCorAtivadoOuDesativado(boolean: Boolean, btnAtivarDesativar: Button) {
        btnAtivarDesativar.isEnabled = boolean
        if (boolean) {
            btnAtivarDesativar.alpha = 1F
        } else {
            btnAtivarDesativar.alpha = 0.5F
        }
    }

    private fun enviarDadosCompraEVendaMoedas(operacao: String) {
        val infoMoedas = Intent(this, CompraVendaMoedas::class.java)
        infoMoedas.putExtra("operacao", operacao)
        infoMoedas.putExtra("quantidade", quantidadeMoeda)
        infoMoedas.putExtra("resultado", resultado)
        infoMoedas.putExtra("cambio", moedaModel)
        startActivity(infoMoedas)
    }

}