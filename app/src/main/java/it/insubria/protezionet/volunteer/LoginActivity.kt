package it.insubria.protezionet.volunteer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

/**
 *
 * Entry point dell'app (finestra di login),  associato
 * ad activity_main.xml
 *
 * - checklogin()
 * - isValidPassword()
 * - ]isValidUsername()
 */
class LoginActivity : AppCompatActivity() {

    // val TITOLO = "ProtezioNET"  -G:  teniamo come esempio commentato perchè potrebbe essere utile
    //                                  nelle altre Activity

    //private lateinit var binding: ActivityMainBinding

    var returnValue = 0.0
    val TAG ="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //val view = binding.root
        setContentView(R.layout.activity_login)

        // title = TITOLO
    }

    fun checkLogin(v: View) {
        val email: String = UsernameField.text.toString()  //binding.UsernameField.text.toString()    //editTextUsername.getText().toString()
        println("email inserita:$email") //stampa di debug
        if (!isValidEmail(email)) {
            UsernameField.error = getString(R.string.invalid_email)       //binding.UsernameField.error = "Invalid Email"                    //editTextUsername.setError("Invalid email")
        }

        val password = PasswordField.text.toString()   //binding.PasswordField.text.toString()                             //editTextPassword.getText().toString()
        if (!isValidPassword(password)) {
            println("password inserita:$password") //stampa di debug

            PasswordField.error = getString(R.string.invalid_password)   //binding.PasswordField.error = "Invalid Password"                 //editTextUsername.setError("Invalid Password")
        }

        if (isValidEmail(email) && isValidPassword(password)) {
            //validation complete, aprire una nuova activity e fare il finish() del login

            val intent = Intent(this@LoginActivity, MainActivity :: class.java) //Creiamo un Intent passandogli il Context ( this@MainActivity ) e in più passiamo l'informazione per rendere l'intent esplicito, l'activity che deve essere eseguita, dicendo che deve aprire l'activity la cui classe è SecondActivity
            startActivity(intent)       //creato l'intent con le informazioni da passare alla SecondActivity, inviamo un richiesta ad ART tramite lo startActivity() passandogli l'intent di tipo esplicito.
                                        //l'androidRuntime, sa che oggetto deve lanciare "MainActivity :: class.java" e quindi istanzia un opggetto di tipo MainActivity e chiama su di esso il metodo onCreate() che crea l'interfaccia e inserisce al suo interno il contenuto che abbiamo
                                        // inserito nel file activity_main.xml
            finish()
        }
    }

    fun isValidPassword(password: String): Boolean {
        return password != null && password.length >= 4
    }

    fun isValidEmail(email: String): Boolean {
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
