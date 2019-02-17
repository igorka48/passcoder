package owlsdevelopers.org.passcoder.ui.util

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.common.SignInButton

fun Button.bind(liveData: MutableLiveData<Boolean>){
    this.setOnClickListener { liveData.value = true }
}
fun SignInButton.bind(liveData: MutableLiveData<Boolean>){
    this.setOnClickListener { liveData.value = true }
}
