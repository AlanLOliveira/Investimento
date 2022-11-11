package com.example.moedascambio.main


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.moedascambio.R
import com.example.moedascambio.model.MoedaModel
import java.text.DecimalFormat


class TelaDeCambio : AppCompatActivity() {

    private var cambiomoeda : MoedaModel? = null
    lateinit var siglaemoeda: TextView
    lateinit var variacao_da_moeda: TextView
    lateinit var valor_compra_moeda: TextView
    lateinit var valor_venda_moeda: TextView
    lateinit var btn_telacambio_voltar: Button
    lateinit var btn_telacambio_vender: Button
    lateinit var btn_telacambio_comprar: Button
    lateinit var etxt_quanditademoeda : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_de_cambio)

        InicializaComponentes()
        TrazerInformacoesMoedaModel()
        eventoCliqueVoltarHome()

    }

    private fun TrazerInformacoesMoedaModel() {
        cambiomoeda = intent.getSerializableExtra("cambio") as? MoedaModel
        cambiomoeda?.let {

            siglaemoeda.text = it.isoMoeda + " - " + it.nome_moeda
            valor_compra_moeda.text = "Compra: " + "R$ " + (it.valor_compra.toString())
            valor_venda_moeda.text = "Venda: " + "R$ " + it.valor_venda.toString()
            variacao_da_moeda.text = it.variacao_moeda.toString()

            formatarCampoQuandoForNulo()
            alterarFormatacaoMoeda(it)

        }
    }

    private fun formatarCampoQuandoForNulo() {
        if (cambiomoeda?.valor_venda == null) {
            valor_venda_moeda.text = "Sem cotação"
        }
    }

    private fun alterarFormatacaoMoeda(it: MoedaModel) {

        if (cambiomoeda?.variacao_moeda!! < 0) {
            variacao_da_moeda.setTextColor(Color.RED)
        } else if (cambiomoeda?.variacao_moeda!! > 0) {
            variacao_da_moeda.setTextColor(Color.GREEN)
        } else {
            variacao_da_moeda.setTextColor(Color.WHITE)
        }
        val formatarVariacao = DecimalFormat("#.##")
        variacao_da_moeda.text =
            formatarVariacao.format(cambiomoeda?.variacao_moeda).toString() + "%"
    }


    private fun eventoCliqueVoltarHome() {
        btn_telacambio_voltar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun InicializaComponentes() {
        siglaemoeda = findViewById(R.id.tv_TelaCambio_SiglaEMoeda)
        variacao_da_moeda = findViewById(R.id.tv_TelaCambio_Variacao)
        valor_compra_moeda = findViewById(R.id.tv_TelaCambio_ValorCompra)
        valor_venda_moeda = findViewById(R.id.tv_TelaCambio_ValorVenda)
        btn_telacambio_voltar = findViewById(R.id.btn_TelaCambio_Voltar)
        btn_telacambio_vender = findViewById(R.id.btn_TelaCambio_Vender)
        btn_telacambio_comprar = findViewById(R.id.btn_TelaCambio_Comprar)
        etxt_quanditademoeda = findViewById(R.id.etxt_TelaCambio_QuantidadeMoeda)


    }

     fun calcularVendaMoeda(view : View) {

         var valorvendamoeda : Double = cambiomoeda?.valor_venda.toString().toDouble()
         var quanditademoeda : Double







    }

}