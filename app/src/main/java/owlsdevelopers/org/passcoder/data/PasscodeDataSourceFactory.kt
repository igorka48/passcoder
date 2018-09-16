package owlsdevelopers.org.passcoder.data

import android.arch.paging.DataSource
import com.google.firebase.database.DatabaseReference
import owlsdevelopers.org.passcoder.model.Passcode

class PasscodeDataSourceFactory(
        private val databaseReference: DatabaseReference,
        private val userName: String) : DataSource.Factory<Long, Passcode>() {
    override fun create(): DataSource<Long, Passcode> {
        val source  = ItemKeyedPasscodeDataSource(databaseReference, userName)
        return source
    }
}
