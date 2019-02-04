package owlsdevelopers.org.passcoder.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import owlsdevelopers.org.passcoder.domain.models.Passcode
import owlsdevelopers.org.passcoder.domain.models.repository.PasscodeRepository
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class FirebasePasscodeRepository(private val databaseReference: DatabaseReference) : PasscodeRepository {
    override suspend fun addPasscode(passcode: Passcode): Boolean = suspendCoroutine { continuation ->
        databaseReference.push().setValue(passcode).addOnCompleteListener {
            t -> if(t.isSuccessful) continuation.resume(true) else continuation.resumeWithException(t.exception!!)
        }
    }

    override suspend fun getPasscodes(fromKey: String, limit: Int): List<Passcode> = suspendCoroutine { continuation ->
        databaseReference.startAt(fromKey).limitToFirst(limit).addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(databaseError: DatabaseError) {
                        continuation.resumeWithException(databaseError.toException())
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val result = mutableListOf<Passcode>()
                        for (ds in snapshot.children) {
                            val code = ds.getValue(Passcode::class.java)
                            code?.let {
                                result.add(it)
                            }
                        }
                        continuation.resume(result)
                    }
                }
        )
    }


    override suspend fun getPasscodes(): List<Passcode> = suspendCoroutine { continuation ->
        databaseReference.addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onCancelled(databaseError: DatabaseError) {
                        continuation.resumeWithException(databaseError.toException())
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val result = mutableListOf<Passcode>()
                        for (ds in snapshot.children) {
                            val code = ds.getValue(Passcode::class.java)
                            code?.let {
                                result.add(it)
                            }
                        }
                        continuation.resume(result)
                    }
                }
        )
    }
}