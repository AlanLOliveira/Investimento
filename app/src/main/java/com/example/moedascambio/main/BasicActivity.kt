package com.example.moedascambio.main


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.example.moedascambio.R

open class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }

    protected open fun visibilidadeBotaoToolbar(tvTitulo: TextView, titulo: String, toolbar : androidx.appcompat.widget.Toolbar ){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tvTitulo.text = titulo
        setIsHeading(tvTitulo)
        if (titulo == MOEDAS) {
           supportActionBar?.setDisplayHomeAsUpEnabled(false)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setIsHeading(textView: TextView) {
        ViewCompat.setAccessibilityDelegate(textView, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    host.isAccessibilityHeading = true
                } else {
                    info.isHeading = true
                }
            }
        })
    }

}
























//protected open fun configuraToolbar(tvTitulo: TextView, btnVoltar: ImageButton, titulo: String) {
//    setSupportActionBar(binding.toolbar)
//    supportActionBar?.setDisplayShowTitleEnabled(false)
//    setIsHeading(tvTitulo)
//    tvTitulo.text = titulo
//    if (titulo == MOEDAS) {
//        btnVoltar.visibility = View.GONE
//    } else {
//        btnVoltar.visibility = View.VISIBLE
//        btnVoltar.setOnClickListener { finish() }
//    }
//}
//
//protected fun modificaNomeTelaAnteriorToolbar(textView: TextView, texto: String) {
//    textView.text = texto
//}
//
//private fun setIsHeading(textView: TextView) {
//    ViewCompat.setAccessibilityDelegate(textView, object : AccessibilityDelegateCompat() {
//        override fun onInitializeAccessibilityNodeInfo(
//            host: View,
//            info: AccessibilityNodeInfoCompat
//        ) {
//            super.onInitializeAccessibilityNodeInfo(host, info)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                host.isAccessibilityHeading = true
//            } else {
//                info.isHeading = true
//            }
//        }
//    })
//}
