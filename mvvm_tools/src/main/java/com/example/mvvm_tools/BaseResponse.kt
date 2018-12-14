package com.example.mvvm_tools

data class BaseResponse<T>(val code: Int, val msg: String, val t: T)