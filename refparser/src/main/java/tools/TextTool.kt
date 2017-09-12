package tools

import javax.inject.Inject

class TextTool
@Inject
constructor() {

    companion object {
        internal val STRANGE_SPACE = 160
    }

    fun clearString(stringWithShittyStuff: String): String {
        return stringWithShittyStuff.replace(STRANGE_SPACE.toChar().toString().toRegex(), "")
                .trim()
    }
}
