package com.ajoly.rich_text_poc

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.util.Log
import android.widget.TextView
import coil3.Canvas
import coil3.ImageLoader
import coil3.asDrawable
import coil3.request.ImageRequest

//from https://github.com/Commit451/coil-imagegetter/blob/main/coilimagegetter/src/main/java/com/commit451/coilimagegetter/CoilImageGetter.kt

open class CoilImageGetter(
    private val textView: TextView,
    private val imageLoader: ImageLoader,
    private val sourceModifier: ((source: String) -> String)? = null
) : Html.ImageGetter {

    override fun getDrawable(source: String): Drawable {

        Log.d("TEST", "getDrawable -> $source")

        val finalSource = sourceModifier?.invoke(source) ?: source

        Log.d("TEST", "getDrawable -> $finalSource")

        val drawablePlaceholder = DrawablePlaceHolder()
        imageLoader.enqueue(
            ImageRequest.Builder(textView.context)
                .data(finalSource).apply {
                    target { image ->
                        val drawable = image.asDrawable(Resources.getSystem())
                        drawablePlaceholder.updateDrawable(drawable)
                        // invalidating the drawable doesn't seem to be enough...
                        textView.text = textView.text
                    }
                }
                .build())
        // Since this loads async, we return a "blank" drawable, which we update
        // later
        return drawablePlaceholder
    }

    @Suppress("DEPRECATION")
    private class DrawablePlaceHolder : BitmapDrawable() {

        private var drawable: Drawable? = null

        override fun draw(canvas: Canvas) {
            drawable?.draw(canvas)
        }

        fun updateDrawable(drawable: Drawable) {
            this.drawable = drawable
            val width = drawable.intrinsicWidth
            val height = drawable.intrinsicHeight
            drawable.setBounds(0, 0, width, height)
            setBounds(0, 0, width, height)
        }
    }
}