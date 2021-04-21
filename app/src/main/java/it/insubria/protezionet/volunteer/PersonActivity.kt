package it.insubria.protezionet.volunteer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

/**
 *
 *  Activity per registrare gli utenti
 *
 **/
class PersonActivity : AppCompatActivity() {

    private val TITOLO = "REGISTRAZIONE UTENTI"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)
        title = TITOLO
    }
        //todo da riga 26 a 61 è lo stesso codice della LoginActivity e non dovrebbe stare in PersonActivity
    fun checkLogin(v: View){
        val email: String = UsernameField.text.toString()  //binding.UsernameField.text.toString()    //editTextUsername.getText().toString()
        println("email inserita:$email") //stampa di debug
        if(!isValidEmail(email)){
            UsernameField.error = resources.getText(R.string.Username_it)       //binding.UsernameField.error = "Invalid Email"                    //editTextUsername.setError("Invalid email")
        }

        val password = PasswordField.text.toString()   //binding.PasswordField.text.toString()                             //editTextPassword.getText().toString()
        if(!isValidPassword(password)){
            println("password inserita:$password") //stampa di debug

            PasswordField.error = resources.getText(R.string.invalid_password)   //binding.PasswordField.error = "Invalid Password"                 //editTextUsername.setError("Invalid Password")
        }

        if(isValidEmail(email) && isValidPassword(password)){
            //validation complete, aprire una nuova activity e fare il finish() del login
        }
    }

    fun isValidPassword(password: String): Boolean {
        return password != null && password.length >= 4
    }

    fun isValidEmail(email: String): Boolean{
        val emailPattern = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")     /*"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
        "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"*/
        val pattern = Pattern.compile(emailPattern)
        val matcher = pattern.matcher(email)
        return matcher.matches()  //se l'indirizzo email soddisfa la reg-ex ritornerà un valore true altrimenti false
    }
}