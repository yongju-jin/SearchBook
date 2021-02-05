package com.yongju.lib.presentation.util

import android.graphics.drawable.Drawable
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy

@BindingAdapter("image")
fun ImageView.setImage(@DrawableRes imageRes: Int?) {
    if (imageRes == null) return
    load(imageRes)
}

@BindingAdapter("image")
fun ImageView.setImage(drawable: Drawable?) {
    if (drawable == null) return
    load(drawable)
}

@BindingAdapter("image", "placeholder", requireAll = false)
fun ImageView.setImage(url: String?, placeholder: Drawable? = null) {
    if (URLUtil.isHttpUrl(url) || URLUtil.isHttpsUrl(url)) {
        load(url) {
            networkCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            placeholder(placeholder)
        }
    } else setImage(placeholder)
}

