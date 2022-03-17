package owlsdevelopers.org.passcoder.ui.core

import android.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showAlertDialog(title: String = "", message: String?, action: () -> Unit) {
    val builder = AlertDialog.Builder(requireContext())
    builder.apply {
        setPositiveButton(
            android.R.string.ok
        ) { _, _ ->
            action()
        }
        setTitle(title)
        setMessage(message)
    }
    builder.show()
}

fun Fragment.showError(message: String?) {
    message?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
}

fun Fragment.showInfo(message: String?) {
    message?.let {
        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
    }
}