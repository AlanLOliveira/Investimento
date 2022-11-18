package com.example.moedascambio.main


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.moedascambio.R
import com.example.moedascambio.Singleton.buscaValorSimuladoParaModel
import com.example.moedascambio.Singleton.modificaValorPosOperacao
import com.example.moedascambio.Singleton.totalsaldocaixa
import com.example.moedascambio.Singleton.totalsaldodisnponivel
import com.example.moedascambio.model.MoedaModel
import java.math.RoundingMode


class TelaDeCambio : AppCompatActivity() {

    private var moedaModel : MoedaModel? = null

    private lateinit var tvTelaCambioSiglaEMoeda: TextView
    private lateinit var tvTelaCambioVariacaoDaMoeda: TextView
    private lateinit var tvTelaCambioValorCompraMoeda: TextView
    private lateinit var tvTelaCambioValorVendaMoeda: TextView
    private lateinit var tvTelaCamioSaldoDisponivel : TextView
    private lateinit var tvTelaCambioSaldoEmCaixa : TextView

    private lateinit var btnTelaCambioVoltarHome: Button
    private lateinit var btnTelaCambioVender: Button
    private lateinit var btnTelaCambioComprar: Button

    private lateinit var etxtTelaCambioQuanditaDeMoeda : EditText


    private var quantidadeMoeda: Int? = null
    private var valorCompraMoeda: Double? = null
    private var valorVendaMoeda: Double? = null
    private var resultadoCompra: Double? = null
    private var resultadoVenda: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_de_cambio)

        inicializaComponentes()
        acoesAtivarDesativarBtnCompra(false)
        acoesAtivarDesativarBtnVenda(false)
        trazerInformacoesMoedaModel()
        eventoCliqueVoltarHome()

     }

    override fun onResume() {
        super.onResume()
        trazerInformacoesMoedaModel()
    }

    private fun trazerInformacoesMoedaModel() {
        moedaModel = intent.getSerializableExtra("cambio") as? MoedaModel
        moedaModel?.let {
             if (totalsaldocaixa == 0){
                 buscaValorSimuladoParaModel(it)
             }
            tvTelaCambioSiglaEMoeda.text = buildString {
        append(it.isoMoeda)
        append(" - ")
        append(it.nome_moeda)
    }
            tvTelaCambioVariacaoDaMoeda.text =
                buildString {
        append(it.variacao_moeda.toString().toBigDecimal().setScale(2, RoundingMode.UP))
        append("%")
    }
            tvTelaCambioValorCompraMoeda.text =
                buildString {
        append("Compra: ")
        append("R$  ")
        append(
            (it.valor_compra).toString().toBigDecimal()
            .setScale(2, RoundingMode.UP)
        )
    }
            tvTelaCambioValorVendaMoeda.text = buildString {
        append("Venda: ")
        append("R$  ")
        append(
            (it.valor_venda).toString().toBigDecimal()
            .setScale(2, RoundingMode.UP)
        )
    }
            tvTelaCambioSaldoEmCaixa.text = buildString {
        append((totalsaldocaixa))
        append(" ")
        append(it.nome_moeda)
        append(" em caixa")
    }
            tvTelaCamioSaldoDisponivel.text = buildString {
        append("Saldo disponível: R$ ")
        append((totalsaldodisnponivel).toBigDecimal().setScale(2, RoundingMode.UP))
    }

            formatarCampoQuandoForNulo()
            alterarCoresMoeda(it)
            ativarBtnVendaECompra(moedaModel = it)

        }
    }

    private fun formatarCampoQuandoForNulo() {
        if (moedaModel?.valor_venda == 0.0) {
            tvTelaCambioValorVendaMoeda.text = "0.0"
        } else if (moedaModel?.valor_compra == 0.0) {
            tvTelaCambioValorCompraMoeda.text = "0.0"
        }
    }

    private fun alterarCoresMoeda(moedaModel: MoedaModel) {

        if (moedaModel.variacao_moeda!! < 0) {
            tvTelaCambioVariacaoDaMoeda.setTextColor(Color.RED)
        } else if (moedaModel.variacao_moeda > 0) {
            tvTelaCambioVariacaoDaMoeda.setTextColor(Color.GREEN)
        } else {
            tvTelaCambioVariacaoDaMoeda.setTextColor(Color.WHITE)
        }
    }


    private fun eventoCliqueVoltarHome() {
        btnTelaCambioVoltarHome.setOnClickListener {
           finish()
        }
    }

    private fun inicializaComponentes() {
        tvTelaCambioSiglaEMoeda = findViewById(R.id.tv_TelaCambio_SiglaEMoeda)
        tvTelaCambioVariacaoDaMoeda = findViewById(R.id.tv_TelaCambio_Variacao)
        tvTelaCambioValorCompraMoeda = findViewById(R.id.tv_TelaCambio_ValorCompra)
        tvTelaCambioValorVendaMoeda = findViewById(R.id.tv_TelaCambio_ValorVenda)
        btnTelaCambioVoltarHome = findViewById(R.id.btn_TelaCompraVenda_VoltarCambio)
        btnTelaCambioVender = findViewById(R.id.btn_TelaCompraVenda_Home)
        btnTelaCambioComprar = findViewById(R.id.btn_TelaCambio_Comprar)
        etxtTelaCambioQuanditaDeMoeda = findViewById(R.id.etxt_TelaCambio_QuantidadeMoeda)
        tvTelaCamioSaldoDisponivel = findViewById(R.id.tv_TelaCambio_SdDisponivel)
        tvTelaCambioSaldoEmCaixa = findViewById(R.id.tv_TelaCambio_SdCaixa)

    }

    private fun btnCalcularVendaMoeda() {

        quantidadeMoeda = etxtTelaCambioQuanditaDeMoeda.text.toString().toInt()
        valorVendaMoeda = moedaModel?.valor_venda.toString().toDouble()
        resultadoVenda = valorVendaMoeda!! * quantidadeMoeda!!
        totalsaldodisnponivel = (totalsaldodisnponivel + resultadoVenda!!)
        totalsaldocaixa = ( totalsaldocaixa - quantidadeMoeda!!)
        moedaModel?.let { modificaValorPosOperacao(it) }

    }

    private fun btnCalcularCompraMoeda() {

        quantidadeMoeda = etxtTelaCambioQuanditaDeMoeda.text.toString().toInt()
        valorCompraMoeda = moedaModel?.valor_compra.toString().toDouble()
        resultadoCompra = (valorCompraMoeda!! * quantidadeMoeda!!)
        totalsaldodisnponivel = (totalsaldodisnponivel - resultadoCompra!!)
        totalsaldocaixa = ( totalsaldocaixa + quantidadeMoeda!!)
        moedaModel?.let { modificaValorPosOperacao(it) }
    }

    private fun acoesAtivarDesativarBtnCompra(boolean: Boolean) {
        btnTelaCambioComprar.isEnabled = boolean
        if (boolean) {
            btnTelaCambioComprar.alpha = 1F
        } else {
            btnTelaCambioComprar.alpha = 0.5F
        }
    }

    private fun acoesAtivarDesativarBtnVenda(boolean: Boolean) {
        btnTelaCambioVender.isEnabled = boolean
        if (boolean) {
            btnTelaCambioVender.alpha = 1F
        } else {
            btnTelaCambioVender.alpha = 0.5F
        }
    }

    private fun ativarBtnVendaECompra(moedaModel: MoedaModel) {

        etxtTelaCambioQuanditaDeMoeda.doOnTextChanged { text, _, _, _ ->
            if (text.toString().isNotBlank()) {
                val ativarbtn = text.toString().toInt()
                if (ativarbtn > 0) {

                    if (moedaModel.valor_compra != 0.0) {
                        if (ativarbtn * moedaModel.valor_compra <= totalsaldodisnponivel) {
                            acoesAtivarDesativarBtnCompra(true)
                        } else {
                            acoesAtivarDesativarBtnCompra(false)
                        }
                    }

                    if (moedaModel.valor_venda != 0.0) {
                        if (ativarbtn <= totalsaldocaixa) {
                            acoesAtivarDesativarBtnVenda(true)
                        } else {
                            acoesAtivarDesativarBtnVenda(false)
                        }
                    }
                    setOnClickListenner()
                }
            } else {
                acoesAtivarDesativarBtnCompra(false)
                acoesAtivarDesativarBtnVenda(false)
            }
        }
    }

    private fun setOnClickListenner() {
        btnTelaCambioComprar.setOnClickListener {
            btnCalcularCompraMoeda()
            val infoCompra = Intent(this, CompraVendaMoedas::class.java)
            infoCompra.putExtra("compra", true)
            infoCompra.putExtra("quantidade", quantidadeMoeda)
            infoCompra.putExtra("resultado", resultadoCompra)
            infoCompra.putExtra("cambio", moedaModel)
            startActivity(infoCompra)
        }
        btnTelaCambioVender.setOnClickListener {
            btnCalcularVendaMoeda()
            val infoVenda = Intent(this, CompraVendaMoedas::class.java)
            infoVenda.putExtra("venda", true)
            infoVenda.putExtra("quantidade", quantidadeMoeda)
            infoVenda.putExtra("resultado", resultadoVenda)
            infoVenda.putExtra("cambio", moedaModel)
            startActivity(infoVenda)

        }
    }




}