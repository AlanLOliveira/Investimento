package com.example.moedascambio.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.moedascambio.R

class CompraVendaMoedas : AppCompatActivity() {

    lateinit var tv_telacambio_resultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compra_venda_moedas)


        tv_telacambio_resultado = findViewById(R.id.tv_TelaCompraVenda_Resultado)
    }
}