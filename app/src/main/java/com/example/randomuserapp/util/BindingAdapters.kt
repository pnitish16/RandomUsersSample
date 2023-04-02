package com.example.randomuserapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("profilePhoto")
    fun loadProfilePhoto(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Picasso.get().load(url).transform(CircleTransform())
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("normalPhoto")
    fun loadNormalPhoto(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Picasso.get().load(url).into(view)
        }
    }


}