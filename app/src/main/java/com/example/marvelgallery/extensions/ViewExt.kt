package com.example.marvelgallery.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : View> RecyclerView.ViewHolder.bindView(viewId: Int) =
    lazy { itemView.findViewById<T>(viewId) }

fun ImageView.loadImage(photoUrl: String) {
    Glide.with(context)
        .load(photoUrl)
        .into(this)
}

fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

fun Activity.bindToSwipeRefresh(@IdRes swipeRefreshLayoutId: Int): ReadWriteProperty<Any?, Boolean> =
    SwipeRefreshBinding(lazy { findViewById<SwipeRefreshLayout>(swipeRefreshLayoutId) })

private class SwipeRefreshBinding(lazyViewProvider: Lazy<SwipeRefreshLayout>) :
    ReadWriteProperty<Any?, Boolean> {

    val view by lazyViewProvider

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): Boolean {
        return view.isRefreshing
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>, value: Boolean
    ) {
        view.isRefreshing = value
    }
}