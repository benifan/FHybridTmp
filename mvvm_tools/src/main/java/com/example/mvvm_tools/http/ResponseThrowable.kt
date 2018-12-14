package com.example.mvvm_tools.http

class ResponseThrowable(throwable: Throwable, var code: Int) : Exception(throwable) {
    override var message: String? = null
}
