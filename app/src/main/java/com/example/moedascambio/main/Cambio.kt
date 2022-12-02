package com.example.moedascambio.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.example.moedascambio.R
import com.example.moedascambio.SingletonSimulandoValores.alteraValorSimulado
import com.example.moedascambio.SingletonSimulandoValores.hashmapPegarValor
import com.example.moedascambio.SingletonSimulandoValores.totalsaldodisnponivel
import com.example.moedascambio.Utils.alteraCorDaVariacaoDaMoeda
import com.example.moedascambio.Utils.formataMoedaBrasileira
import com.example.moedascambio.Utils.formatarPorcentage
import com.example.moedascambio.model.MoedaModel



const val COMPRA = "compra"
const val VENDA = "venda"
const val CAMBIO = "cambio"
const val OPERACAO = "operacao"
const val QUANTIDADE = "quantidade"
const val RESULTADO = "resultado"

class Cambio :  BasicActivity()  {

    private var moedaModel: MoedaModel? = null
    private lateinit var tvToolbar: TextView
    private lateinit var tvCambioSiglaEMoeda: TextView
    private lateinit var tvCambioVariacaoDaMoeda: TextView
    private lateinit var tvCambioValorCompraMoeda: TextView
    private lateinit var tvCambioValorVendaMoeda: TextView
    private lateinit var tvCambioSaldoDisponivel: TextView
    private lateinit var tvCambioSaldoEmCaixa: TextView
    private lateinit var btnCambioVender: Button
    private lateinit var btnCambioComprar: Button
    private lateinit var etxtCambioQuanditaDeMoeda: EditText
    private var quantidadeMoeda: Int = 0
    private var resultado: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_de_cambio)
        inicializaComponentes()
        btnCorAtivadoOuDesativado(boolean = false, btnCambioComprar)
        btnCorAtivadoOuDesativado(boolean = false, btnCambioVender)
        trazInformacaoMoedaParaTelaDeCambio()

    }

    private fun setOnClickListenner() {
        btnCambioComprar.setOnClickListener {
            btnCalcularCompraMoeda()
        }
        btnCambioVender.setOnClickListener {
            btnCalcularVendaMoeda()
        }
    }

    override fun onResume() {
        super.onResume()
        etxtCambioQuanditaDeMoeda.text.clear()
        trazInformacaoMoedaParaTelaDeCambio()
    }

    private fun trazInformacaoMoedaParaTelaDeCambio() {
        tvToolbar.text = getString(R.string.cambio)
        moedaModel = intent.getSerializableExtra(CAMBIO) as? MoedaModel
        moedaModel?.let {

            preencherCamposNaTelaCambio(it)
            alteraCorDaVariacaoDaMoeda(moedaModel = it, tvCambioVariacaoDaMoeda)
            execucaoBtnVendaECompra(moedaModel = it)
        }
    }

    private fun preencherCamposNaTelaCambio(it: MoedaModel) {

        tvCambioSiglaEMoeda.text = buildString {
            append(it.isoMoeda + getString(R.string.hifen) + it.nome_moeda)
        }
        tvCambioVariacaoDaMoeda.text =
            buildString {
              append (formatarPorcentage(it.variacao_moeda)).toString()
               }
        tvCambioValorCompraMoeda.text =
            buildString {
                append(getString(R.string.compra) +  formataMoedaBrasileira(it.valor_compra))
            }
        tvCambioValorVendaMoeda.text = buildString {
            append(getString(R.string.venda) + formataMoedaBrasileira(it.valor_venda))
        }
        tvCambioSaldoEmCaixa.text = buildString {
            append(hashmapPegarValor(it.isoMoeda))
            append(getString(R.string.espaco))
            append(it.nome_moeda)
            append(getString(R.string.em_caixa))
        }
        tvCambioSaldoDisponivel.text = buildString {
            append(getString(R.string.saldoDisponivel) + (getString(R.string.espaco)) + formataMoedaBrasileira(totalsaldodisnponivel))
       }
    }

    private fun inicializaComponentes() {
        tvCambioSiglaEMoeda = findViewById(R.id.tv_Cambio_SiglaEMoeda)
        tvCambioVariacaoDaMoeda = findViewById(R.id.tv_Cambio_Variacao)
        tvCambioValorCompraMoeda = findViewById(R.id.tv_Cambio_ValorCompra)
        tvCambioValorVendaMoeda = findViewById(R.id.tv_Cambio_ValorVenda)
        btnCambioVender = findViewById(R.id.btn_CompraVenda_Home)
        btnCambioComprar = findViewById(R.id.btn_Cambio_Comprar)
        etxtCambioQuanditaDeMoeda = findViewById(R.id.etxt_Cambio_QuantidadeMoeda)
        tvCambioSaldoDisponivel = findViewById(R.id.tv_Cambio_SdDisponivel)
        tvCambioSaldoEmCaixa = findViewById(R.id.tv_Cambio_SdCaixa)
        tvToolbar = findViewById(R.id.tv_Toolbar)
    }

    private fun btnCalcularVendaMoeda(){
        calculaVendaDaMoeda()
        moedaModel?.let { alteraValorSimulado(it.isoMoeda, COMPRA, quantidadeMoeda) }
    }

    private fun btnCalcularCompraMoeda() {
        calculaCompraDaMoeda()
        moedaModel?.let { alteraValorSimulado(it.isoMoeda, COMPRA, quantidadeMoeda) }
    }
    
    private fun calculaVendaDaMoeda() {
        val valorVendaMoeda = moedaModel?.valor_venda.toString().toDouble()
        quantidadeMoeda = etxtCambioQuanditaDeMoeda.text.toString().toInt()
        resultado = valorVendaMoeda * quantidadeMoeda
        totalsaldodisnponivel = (totalsaldodisnponivel + resultado)
        moedaModel?.quantidade?.let {
            moedaModel?.quantidade = (it - quantidadeMoeda)
        }
        enviarDadosCompraEVendaMoedas(VENDA)
    }

    private fun calculaCompraDaMoeda() {
        val valorCompraMoeda = moedaModel?.valor_compra.toString().toDouble()
        quantidadeMoeda = etxtCambioQuanditaDeMoeda.text.toString().toInt()
        resultado = (valorCompraMoeda * quantidadeMoeda)
        totalsaldodisnponivel = (totalsaldodisnponivel - resultado)
        moedaModel?.quantidade?.let {
            moedaModel?.quantidade = (it + quantidadeMoeda)
        }
        enviarDadosCompraEVendaMoedas(COMPRA)
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
            if (ativarbtn <= moedaModel.quantidade) {
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
        infoMoedas.putExtra(OPERACAO, operacao)
        infoMoedas.putExtra(QUANTIDADE, quantidadeMoeda)
        infoMoedas.putExtra(RESULTADO, resultado)
        infoMoedas.putExtra(CAMBIO, moedaModel)
        startActivity(infoMoedas)
    }


}