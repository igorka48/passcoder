package owlsdevelopers.org.passcoder.presentation.util

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.common.SignInButton

fun Button.bind(liveData: MutableLiveData<Boolean>) {
    this.setOnClickListener { liveData.value = true }
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