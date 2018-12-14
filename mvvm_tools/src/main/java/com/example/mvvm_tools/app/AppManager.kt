package com.example.mvvm_tools.app

import android.app.Activity
import android.support.v4.app.Fragment
import java.util.*

//actvity管理栈堆集合
class AppManager private constructor() {

    companion object {
        //单列
        val instance = AppManager()

        lateinit var activityStack: Stack<Activity>

        lateinit var frgmentStack: Stack<Fragment>
    }

    //添加到stack
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    //添加frgment
    fun addFrgment(activity: Fragment) {
        frgmentStack.add(activity)
    }

    fun removeFrgment(activity: Fragment) {
        frgmentStack.remove(activity)
    }

    //栈是是否空
    fun isActivity(): Boolean {
        return activityStack.isEmpty()
    }

    fun isFrgment(): Boolean {
        return frgmentStack.isEmpty()
    }

    //获取当前activity
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    //结束指定acitivity
    fun <T> finshActivity(classz: Class<T>) {
        for (activity in activityStack) {
            if (activity::class == classz) {
                finish(activity)
            }
        }
    }

    //关闭当前activity
    fun finish(activity: Activity) {
        if (!activity.isFinishing) {
            activity.finish()
        }
    }

    //结束所以activity
    fun closeAllActivity() {
        for (activity in activityStack) {
            activity!!.finish()
        }
        activityStack.clear()

    }

    //结束app
    fun exit() {
        try {
            closeAllActivity()
        } catch (e: Error) {
            e.printStackTrace()
            activityStack.clear()
        }
    }

}