package owlsdevelopers.org.passcoder.ui.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.common.SignInButton

fun Button.bind(liveData: MutableLiveData<Boolean>) {
    this.setOnClickListener { liveData.value = true }
}
fun Button.bind(command: () -> Unit) {
    this.setOnClickListener { command() }
}

fun SignInButton.bind(liveData: MutableLiveData<Boolean>) {
    this.setOnClickListener { liveData.value = true }
}

fun SignInButton.bind(command: () -> Unit) {
    this.setOnClickListener { command() }
}

fun SwipeRefreshLayout.bind(command: () -> Unit) {
    this.setOnRefreshListener { command() }
}
fun EditText.bind(liveData: MutableLiveData<String>) {
    var mutatingFromWatcher = false
    var mutatingFromObserver = false

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            mutatingFromWatcher = true
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (!mutatingFromObserver) {
                liveData.value = s.toString()
            }
            mutatingFromWatcher = false
        }

    }
    liveData
        //.debounce(100L)
        .observeForever {
            mutatingFromObserver = true
            if (!mutatingFromWatcher)
                setTextKeepState(it)
            mutatingFromObserver = false
        }
    this.addTextChangedListener(textWatcher)
}
