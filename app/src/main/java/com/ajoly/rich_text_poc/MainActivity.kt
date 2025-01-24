package com.ajoly.rich_text_poc

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.ImageLoader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val htmls = HtmlContentProvider.all_html

        var currentText : Int = 0
        var currentFlag : Int = 0

        // HTMLs
        val nextButton: Button = findViewById(R.id.next_richView)
        val prevButton: Button = findViewById(R.id.previous_richView)
        nextButton.setOnClickListener {
            if (currentText < htmls.size-1)
                currentText += 1
            renderText(htmls[currentText].trimIndent(), currentFlag)
        }
        prevButton.setOnClickListener {
            if (currentText > 0)
                currentText -= 1
            renderText(htmls[currentText].trimIndent(), currentFlag)
        }

        // FLAGS
        val nextFlagButton: Button = findViewById(R.id.next_flag)
        val previousFlagButton: Button = findViewById(R.id.previous_flag)
        nextFlagButton.setOnClickListener {
            if (currentFlag < HtmlFlags.entries.size-1)
                currentFlag += 1
            renderText(htmls[currentText], currentFlag)
        }
        previousFlagButton.setOnClickListener {
            if (currentFlag > 0)
                currentFlag -= 1
            renderText(htmls[currentText],  currentFlag)
        }
        renderText(htmls[currentText],  currentFlag)

        // ADD behaviours
        addCopyBehaviour(htmls[currentText]);
        addClickBehaviour()

    }

    private fun renderText(htmlContent : String, flag : Int) {

        val currentFlagText : TextView = findViewById(R.id.current_flag)

        renderContentTextView(htmlContent, flag)
        currentFlagText.text = HtmlFlags.entries[flag].name
    }

    private fun renderContentTextView(htmlContent : String, flag : Int) {
        val richTextView: TextView = findViewById(R.id.rich_textview)

        //Log.d("TEST", "Full html before formatting")
        //Log.d("TEST", htmlContent)
        var formattedContent = HtmlFormatter.replaceLineReturnByBr(htmlContent)
        formattedContent = HtmlFormatter.replaceMultipleSpaceWithNbsp(formattedContent)
        formattedContent = HtmlFormatter.wrapCodeTagWithFont(formattedContent)

        Log.d("TEST", "Full html after formatting")
        Log.d("TEST", formattedContent)
        richTextView.text = HtmlCompat.fromHtml(
            formattedContent,
                HtmlCompat.FROM_HTML_MODE_LEGACY,
                CoilImageGetter(
                    richTextView,
                    ImageLoader(this)
                ),
                CustomTagHandler(htmlContent)
        )
    }

    private fun addCopyBehaviour(htmlContent : String) {
        val richTextView: TextView = findViewById(R.id.rich_textview)

        // richTextView.text should be the plainText in normal text
        //val clip = ClipData.newPlainText("html", richTextView.text)

        // richTextView.text should be the plainText in normal text
        val clip = ClipData.newHtmlText("html", richTextView.text, htmlContent)

        richTextView.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(clip)
        }
    }

    private fun addClickBehaviour() {
        val richTextView: TextView = findViewById(R.id.rich_textview)

        // https://stackoverflow.com/questions/4303160/how-can-i-make-links-in-fromhtml-clickable-android
        // Fix the link logic to open on click for html link
        richTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}