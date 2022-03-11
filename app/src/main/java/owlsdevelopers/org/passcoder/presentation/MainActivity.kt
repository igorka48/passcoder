package owlsdevelopers.org.passcoder.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import owlsdevelopers.org.passcoder.R


class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    companion object {
        const val TAG = "MainActivity"
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

//    val viewModel by viewModel<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        var resultLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                Log.d("Tag", "SignIn success")
//
//                if (result.resultCode == Activity.RESULT_OK) {
//                    // There are no request codes
//                    val data: Intent? = result.data
//                    val response = IdpResponse.fromResultIntent(data)
//                    gotoMainScreen()
//                } else {
//                    // Sign in failed. If response is null the user canceled the
//                    // sign-in flow using the back button. Otherwise check
//                    // response.getError().getErrorCode() and handle the error.
//                    // ...
//                }
//            }
//
//        viewModel.viewEvent.observe(this, Observer {
//            when (it) {
//                is ViewEvent.Error -> showError(it.message)
//                is ViewEvent.Info -> showInfo(it.message)
//                //is ViewEvent.ShowLoading -> progressView.visibility = View.VISIBLE
//                //is ViewEvent.HideLoading -> progressView.visibility = View.VISIBLE
//            }
//        })

//        viewModel.loginIntent.observe(this, Observer { value ->
//            value?.let {
//                startActivityForResult(it, RC_SIGN_IN)
//            }
//        })

//        viewModel.userData.observe(this, Observer { value ->
//            value?.let {
//                gotoMainScreen()
//            }
//        })

//        loginButton.setOnClickListener {
//            // Choose authentication providers
//            val providers = arrayListOf(
//                AuthUI.IdpConfig.GoogleBuilder().build(),
//                AuthUI.IdpConfig.AnonymousBuilder().build(),
//                AuthUI.IdpConfig.AppleBuilder().build()
//            )
//
//// Create and launch sign-in intent
////            startActivityForResult(
////                AuthUI.getInstance()
////                    .createSignInIntentBuilder()
////                    .enableAnonymousUsersAutoUpgrade()
////                    .setAvailableProviders(providers)
////                    .build(),
////                RC_SIGN_IN
////            )
//
//
//            resultLauncher.launch(AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .enableAnonymousUsersAutoUpgrade()
//                .setAvailableProviders(providers)
//                .build())
//        }
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


//    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN) {
//            viewModel.googleSignInResultIntent.value = data
//        }
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == RC_SIGN_IN) {
//            val response = IdpResponse.fromResultIntent(data)
//
//            if (resultCode == Activity.RESULT_OK) {
//                // Successfully signed in
//                val user = FirebaseAuth.getInstance().currentUser
//                gotoMainScreen()
//                // ...
//            } else {
//                // Sign in failed. If response is null the user canceled the
//                // sign-in flow using the back button. Otherwise check
//                // response.getError().getErrorCode() and handle the error.
//                // ...
//            }
//        }
//    }

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

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showInfo(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun gotoMainScreen() {
       // startActivity(HomeFragment.getIntent(this))
    }


}
