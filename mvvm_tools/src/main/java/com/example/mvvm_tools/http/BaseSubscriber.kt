package com.example.mvvm_tools.http

import android.content.Context
import android.widget.Toast
import com.example.mvvm_tools.BaseResponse
import com.example.mvvm_tools.tool.KLog
import io.reactivex.observers.DisposableObserver

abstract class BaseSubscriber<T>(private val context: Context) : DisposableObserver<T>() {

    abstract fun onResult(t: T)

    private val isNeedCahe: Boolean = false


    override fun onError(e: Throwable) {
        KLog.e(e.message)
        if (e is ResponseThrowable) {
            onError(e)
        } else {
            onError(ResponseThrowable(e, ExceptionHandle.ERROR.UNKNOWN))
        }
    }


    public override fun onStart() {
        super.onStart()

        Toast.makeText(context, "http is start", Toast.LENGTH_SHORT).show()
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(context)) {
            Toast.makeText(context, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show()
            onComplete()
        }

    }

    override fun onComplete() {
        Toast.makeText(context, "http is Complete", Toast.LENGTH_SHORT).show()
    }


    abstract fun onError(e: ResponseThrowable)

    override fun onNext(t: T) {
        val baseResponse = t as BaseResponse<*>
        if (baseResponse.code === 200) {
            onResult(baseResponse.t as T)
        } else if (baseResponse.code === 330) {
            onError(Throwable(t.msg))
        } else if (baseResponse.code === 503) {
            KLog.e(baseResponse.msg)
        } else {
            onError(Throwable("操作失败！" + baseResponse.code))
        }
    }

}