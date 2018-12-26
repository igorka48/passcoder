package owlsdevelopers.org.passcoder.ui.passcodes.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import owlsdevelopers.org.passcoder.R
import owlsdevelopers.org.passcoder.ui.actions.ActionsFragment
import owlsdevelopers.org.passcoder.ui.addpasscode.AddPasscodeFragment
import owlsdevelopers.org.passcoder.ui.login.activities.LoginActivity

class PasscodesActivity : AppCompatActivity() {

    companion object {
        const val TAG = "PasscodesActivity"
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, PasscodesActivity::class.java)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        addCodeFab.setOnClickListener { showAddCodeDialog() }
    }



    private fun showAddCodeDialog() {
        val newFragment = AddPasscodeFragment.newInstance()
        newFragment.isCancelable = false
        newFragment.show(supportFragmentManager, "dialog")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        GoogleSignIn.getClient(this, gso).signOut()
        startActivity(LoginActivity.getIntent(this))
        finish()
    }
}
