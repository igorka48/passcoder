package owlsdevelopers.org.passcoder.model

/**
 * Created by igorka on 22.01.2018.
 */
data class Passcode(var value: String = "",
                    var description: String = "",
                    var fullyRedeemtionCounter: Int = 0,
                    var additionTime: Long = 0) {
}