package com.ajoly.rich_text_poc

import android.text.Editable
import android.text.Html
import android.util.Log
import org.xml.sax.XMLReader

class CustomTagHandler(
    val initialContent : String
) : Html.TagHandler {

    override fun handleTag(openable: Boolean, tag: String?, output: Editable?, xmlReader: XMLReader?) {
        //Log.d("TEST", "Tag custom handler")
        //Log.d("TEST", "openable $openable")
        //Log.d("TEST", "tag $tag")
        //Log.d("TEST", "output $output")
        //Log.d("TEST", "xmlReader $xmlReader")
    }

}