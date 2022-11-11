package com.example.moedascambio.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//utilitário para instanciar retrofit chamada no sevidor

class MoedaRetrofit {

    private val URL_FINANCE = "https://api.hgbrasil.com/"

    fun moedaRetrofitInstance( ) : MoedaService {
        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

           val retrofit : Retrofit = Retrofit.Builder()
               .client(client)
               .baseUrl(URL_FINANCE)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
            return retrofit.create(MoedaService::class.java)
        }
    }

