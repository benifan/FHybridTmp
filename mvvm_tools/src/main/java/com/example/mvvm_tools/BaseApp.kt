package com.example.mvvm_tools

import android.app.Activity
import android.app.Application
import android.os.Bundle
import javax.annotation.Nullable

class BaseApp : Application() {

    companion object {

        private var instance: Application? = null

        fun instance() = instance!!

        /**
         * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
         */
        fun setApplication(@Nullable app: Application) {

            instance = app

            app.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {

                }

                override fun onActivityResumed(activity: Activity?) {
                }

                override fun onActivityStarted(activity: Activity?) {
                }

                override fun onActivityDestroyed(activity: Activity?) {
                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                }

                override fun onActivityStopped(activity: Activity?) {
                }

                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                }

            })
        }
    }

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
    }


}