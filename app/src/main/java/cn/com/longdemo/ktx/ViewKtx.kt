package cn.com.longdemo.ktx

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

/**
 * View相关
 */

fun <ViewT : View> Activity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ViewT>(idRes)
    }
}

fun <ViewT : View> Fragment.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        requireView().findViewById<ViewT>(idRes)
    }
}

fun <ViewT : View> RecyclerView.ViewHolder.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        itemView.findViewById<ViewT>(idRes)
    }
}

fun <ViewT : View> View.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ViewT>(idRes)
    }
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}