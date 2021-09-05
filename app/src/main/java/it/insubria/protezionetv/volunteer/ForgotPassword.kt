package it.insubria.protezionetv.volunteer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import it.insubria.protezionetv.volunteer.R
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*

class ForgotPassword : AppCompatActivity() {

    private lateinit var fAuth: FirebaseAuth
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        fAuth = FirebaseAuth.getInstance()
        progressBar = progressBarForgetPassword
    }


    //quando viene premuto il botone di resetPassword
    fun resetPassword(v: View) {
        if (v.id == R.id.resetPasswordButton) {
            val email: String = emailAddress.text.toString().trim()

            if (email.isEmpty()) {
                emailAddress.error = getString(R.string.email_is_required)
                emailAddress.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailAddress.error =
                    getString(R.string.invalid_email)       //binding.UsernameField.error = "Invalid Email"                    //editTextUsername.setError("Invalid email")
                emailAddress.requestFocus()
            }
            //nel caso in cui la mail inserita sia valida
            else {
                progressBar.visibility = View.VISIBLE

                fAuth.sendPasswordResetEmail(email).addOnCompleteListener {

                    if (it.isSuccessful) {
                        //se ha avuo successo vuol dire che l'email e stata mandata
                        emailAddress.setText("")

                        val intent = Intent(this@ForgotPassword, LoginActivity::class.java)
                        startActivity(intent)

                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@ForgotPassword,
                            "Check your email o reset your password!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@ForgotPassword,
                            "Try again! Something wrong happened",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }
    }
}