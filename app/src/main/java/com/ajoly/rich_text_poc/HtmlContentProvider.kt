package com.ajoly.rich_text_poc

import android.graphics.Color
import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
import android.graphics.fonts.FontStyle

object HtmlContentProvider {

    val htmlTitles = """
        <p>
            <h1>h1 Welcome to Android Development</h1>
            <h2>h2 Welcome to Android Development</h2>
            <h3>h3 Welcome to Android Development</h3>
            <h4>h4 Welcome to Android Development</h4>
            <h5>h5 Welcome to Android Development</h5>
            <h6>h6 Welcome to Android Development</h6>
        </p>
        """
    val htmlLinks = "<a href=\"https://www.google.com\">https link</a>"
    val htmlLinksInTheme = """
        <a href=\"https://www.google.com\">https link</a>"
        <a href=\"https://www.google.com\"><b><i>https link</b></i></a>"
        <a href=\"https://www.google.com\"><b><i>https link</b></i></a>"
        <a href=\"https://www.google.com\"><b><i>https link</b></i></a>"
    """
    val htmlBasic = """
        <p>
            <div>div here</div>
            <span style="background-color: yellow;">span here with a br after</span><br>
            <a href="https://www.google.com">https link</a>
            <a href="https://issuetracker.google.com/issues/36907309">2nd link</a>
            <a href="www.google.com">www link</a>
            <p color="red">p tag here</p>
            <span class="marker">highlights</span>
            <mark>highlights</mark>
            <s>Strikethrough</s>
            <ul> 
                <li> List 
                    <ul> 
                        <li> Sublist </li> 
                    </ul> 
                </li>
            </ul>
            <blockquote>This is a quote</blockquote>
            <span style="color:rgb(212,31,28);">colored</span>.
        </p>
        """
    val htmlMultipleTag = """
        <p><strong> Bold </strong></p>
        <p><i> Italic </i></p>
        <p><u> Underline </u></p>
        <p><s> Strikethrough </s></p>
        <p>
            <strong>
            <i>
            <u>
            <s>Bold and italic and underline and strikethrough</s>
            </u>
            </i>
            </strong>
        </p>
        """
    val htmlSpecialCharacters = """
        <p>Ampersand: &amp;</p>
        <p>Less-than sign: &lt;</p>
        <p>Greater-than sign: &gt;</p>
        <p>Double quotation mark: &quot;</p>
        <p>Single quotation mark: &apos;</p>
        <p>Non-breaking space: &nbsp;</p>
        <p>Copyright symbol: &copy;</p>
        <p>Section sign: &sect;</p>
        <p>Three-fourths fraction: &frac34;</p>
        <p>	"ni: &#8715;"</p>
        """
    val htmlLists = """
        <p>
        <ul> 
            <li> Bulleted 1 </li> 
            <li> Bulleted 2 </li> 
        </ul>
        <ol> 
            <li> Numbered text 1</li> 
            <li> Numbered text 2</li> 
        </ol>
        <ul> 
            <li> List 
                <ul> 
                    <li> Sublist </li> 
                </ul> 
            </li>
        </ul>
        <ul> <li> <ul> Sublist </ul> </li> </ul>
        </p>
        """
    val htmlColors = """
        <p>
        <span style="background-color: red"><font>Background color</font></span>
        <font color='green'>Colored Green font</font>
        <p color='green'>Colored Green P</p>
        <span style="color:rgb(255,255,255);">white font colored span</span>
        <span style="color:rgb(0,0,0);">black font colored span</span>
        </p>
        """
    val htmlCodeExample = "<p>Here's a multiline codeblock\n</p>" +
            "<pre><code> fun method() {\n" +
            "    otherMethod();\n" +
            "}</code></pre>\n"
    val htmlInlineImg = """
        <p>This is a before img <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/1200px-Google_2015_logo.svg.png"/> This is after</p>
        """
    val htmlEmoji = """<h1>HTML Emoji Example</h1><h2>&#128514;</h2>"""

    val fullhtml = """
<p>This is <strong>strong</strong> <b>bold</b>,
<i>italics</i> and <u>underline</u> linked
 <i><a href="example.com">text</a></i> with
 <mark>highlights</mark>.
 <s>Strikethrough</s> and &nbsp;<span style="color:rgb(212,31,28);">colored</span>.
</p>
<h1>heading 1</h1>
<h2>heading 2</h2>
<h3>heading 3</h3>
<p>Here's a multiline codeblock\n</p><pre><code>fun method() { \n    otherMethod(); \n}</code></pre>\n
<img src="https://images.pexels.com/photos/102104/pexels-photo-102104.jpeg?auto=compress&cs=tinysrgb&w=100"/>
<ul>
    <li>
        Bulleted
        <ul>
            <li>
                sub
                <ol>
                    <li>
                        Numbered text
                    </li>
                </ol>
            </li>
        </ul>
    </li>
</ul>
"""

    val all_html = buildList {
        this.add(fullhtml)
        //this.add(htmlTitles)
        this.add(htmlLinks)
        this.add(htmlLinksInTheme)
        this.add(htmlBasic)
        //this.add(htmlMultipleTag)
        //this.add(htmlSpecialCharacters)
        this.add(htmlLists)
        //this.add(htmlColors)
        this.add(htmlCodeExample)
        this.add(htmlInlineImg)
        this.add(htmlEmoji)
    }
}

public enum class HtmlFlags(val flagValue : Int) {
    FROM_HTML_MODE_LEGACY(0), // This is the value to maintain consistency with lower APIs
    FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH(1),
    FROM_HTML_SEPARATOR_LINE_BREAK_HEADING(2),
    FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM(4),
    FROM_HTML_SEPARATOR_LINE_BREAK_LIST(8),
    FROM_HTML_SEPARATOR_LINE_BREAK_DIV(16),
    FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE(32),
    FROM_HTML_MODE_COMPACT(63),
    FROM_HTML_OPTION_USE_CSS_COLORS(256),
}