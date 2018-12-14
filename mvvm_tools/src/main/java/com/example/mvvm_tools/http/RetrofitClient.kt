package com.example.mvvm_tools.http

class RetrofitClient private constructor() {

    companion object {
        val api_instal = RetrofitClient()
    }

    lateinit var url: String

    fun init() {



    }

}