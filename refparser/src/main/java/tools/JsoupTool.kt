package tools

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

import javax.inject.Inject

class JsoupTool @Inject constructor(private val textTool: TextTool) {

    fun textFromElement(e: Element): String {
        val s = Jsoup.parse(e.html()).text()
        return textTool.clearString(s)
    }

}
