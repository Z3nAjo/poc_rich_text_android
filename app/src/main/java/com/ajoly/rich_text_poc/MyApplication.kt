package com.ajoly.rich_text_poc

import android.content.Context
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.request.crossfade

// On Android you can implement SingletonImageLoader.Factory on your
// Application class to have it create the singleton image loader.
class MyApplication : SingletonImageLoader.Factory {
    override fun newImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .build()
    }
}