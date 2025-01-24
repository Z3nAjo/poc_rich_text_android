package com.ajoly.rich_text_poc

import android.util.Log

object HtmlFormatter {

    fun replaceMultipleSpaceWithNbsp(htmlContent : String) =
        htmlContent.replace("    ", "&nbsp;")

    fun replaceLineReturnByBr(htmlContent : String) =
        htmlContent.replace("\\n", "<br>")

    fun wrapCodeTagWithFont(html: String): String {
        //Log.d("TEST", "<code> wrapped")
        val codeTagPattern = Regex("<code>(.*?)</code>", RegexOption.DOT_MATCHES_ALL)
        return codeTagPattern.replace(html) { matchResult ->
            val content = matchResult.groupValues[1]
            """<span style="background-color: gray;"><font color="LightGray" face="monospace">$content</font></span>"""
        }
    }


}