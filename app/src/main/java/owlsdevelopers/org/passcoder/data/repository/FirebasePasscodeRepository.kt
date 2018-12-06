package owlsdevelopers.org.passcoder.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import owlsdevelopers.org.passcoder.model.Passcode
import owlsdevelopers.org.passcoder.model.repository.PasscodeRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


class FirebasePasscodeRepository(private val databaseReference: DatabaseReference) : PasscodeRepository {

    override suspend fun getPasscodes(fromKey: String, limit: Int): List<Passcode> = suspendCancellableCoroutine { continuation ->
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


    override suspend fun getPasscodes(): List<Passcode> = suspendCancellableCoroutine { continuation ->
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