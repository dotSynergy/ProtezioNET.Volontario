package it.insubria.protezionet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
//import it.insubria.protezionet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

// Pagina principale dell'app con il Login, associato a activity_main.xml

/**
 *
 *  Entry point dell'app (finestra di login)
 *
 **/
class LoginActivity : AppCompatActivity() {

    val TITOLO = "ProtezioNET Admin"

    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //val view = binding.root
        setContentView(R.layout.activity_main)



        title = TITOLO
    }

    fun checkLogin(v: View){
        val email: String = UsernameField.text.toString()  //binding.UsernameField.text.toString()    //editTextUsername.getText().toString()
        System.out.println("email inserita:$email")
        if(!isValidEmail(email)){
            UsernameField.error = "Invalid Email"       //binding.UsernameField.error = "Invalid Email"                    //editTextUsername.setError("Invalid email")
        }

        val password = PasswordField.text.toString()   //binding.PasswordField.text.toString()                             //editTextPassword.getText().toString()
        if(!isValidPassword(password)){
            System.out.println("password inserita:$password")

            PasswordField.error = "Invalid Password"   //binding.PasswordField.error = "Invalid Password"                 //editTextUsername.setError("Invalid Password")
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
