package owlsdevelopers.org.passcoder.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by igorka on 22.01.2018.
 */
@Parcelize
data class Passcode(var value: String = "",
                    var description: String = "",
                    var fullyRedeemtionCounter: Int = 0,
                    var additionTime: Long = 0) : Parcelable