package owlsdevelopers.org.passcoder.ui.login.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.ui.core.ViewEvent
import owlsdevelopers.org.passcoder.ui.login.viewmodels.LoginViewModel
import owlsdevelopers.org.passcoder.ui.passcodes.activities.PasscodesActivity
import owlsdevelopers.org.passcoder.ui.util.bind


class LoginActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    companion object {
        const val TAG = "LoginActivity"
        const val RC_SIGN_IN = 1
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            return intent
        }
    }

    val viewModel by viewModel<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        viewModel.viewEvent.observe(this, Observer {
            when(it){
                is ViewEvent.Error -> showError(it.message)
                is ViewEvent.Info -> showInfo(it.message)
                //is ViewEvent.ShowLoading -> progressView.visibility = View.VISIBLE
                //is ViewEvent.HideLoading -> progressView.visibility = View.VISIBLE
            }
        })

        viewModel.loginIntent.observe(this, Observer { value ->
            value?.let {
                startActivityForResult(it, RC_SIGN_IN)
            }
        })

        viewModel.userData.observe(this, Observer {
            value -> value?.let {
                gotoMainScreen()
            }
        })

        loginButton.bind{viewModel.loginCommand()}
    }


//    private fun loginWithGoogle() {
//        // Configure Google Sign In
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build()
//        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//        val signInIntent = mGoogleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            viewModel.googleSignInResultIntent.value = data
        }
    }

//    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
//
//        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "signInWithCredential:success")
//                        val user = mAuth.currentUser
//                        //updateUI(user)
//                        gotoMainScreen()
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "signInWithCredential:failure", task.exception)
//                        //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
//                        //updateUI(null)
//                    }
//                    // ...
//                }
//    }

    private fun showError(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showInfo(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun gotoMainScreen() {
        startActivity(PasscodesActivity.getIntent(this))
    }


}
