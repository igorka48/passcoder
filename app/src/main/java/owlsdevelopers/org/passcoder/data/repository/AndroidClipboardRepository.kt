package owlsdevelopers.org.passcoder.data.repository

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import owlsdevelopers.org.passcoder.model.repository.ClipboardRepository

class AndroidClipboardRepository(private val context: Context): ClipboardRepository {
    override fun copyTextToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(text, text)
        clipboard.primaryClip = clip
    }
}