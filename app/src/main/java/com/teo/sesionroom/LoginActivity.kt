package com.teo.sesionroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.teo.sesionroom.model2.newUserDAO
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    var correo: String? = ""
    var contra: String? = ""
    lateinit var providers : List<AuthUI.IdpConfig>
    var googleSignInClient : GoogleSignInClient? = null

    val RC_SIGN_IN = 1000
    var mGoogleApiClient: GoogleApiClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)


        providers = Arrays.asList<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()

        )

        showSignInOptions()

        // ...
        bt_google.setOnClickListener {

            var signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent,RC_SIGN_IN)
        }
        //val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

     /*   var datosRecibidos : Bundle? =intent.extras
        if(datosRecibidos != null) {
            correo = datosRecibidos.getString("correo")
            contra = datosRecibidos.getString("contra")
            //Toast.makeText(this,"return main to login",Toast.LENGTH_SHORT).show()
        }*/


        tv_registrar.setOnClickListener {

            var intent =Intent(this,RegistroActivity::class.java)
            startActivityForResult(intent,1001)
            finish()
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////

        bt_iniciarsesion.setOnClickListener{

            var correo = et_Correo.text.toString()
            var contra = et_Contra.text.toString()

            val newuserDAO: newUserDAO = SesionROOM.dataBase2.newUserDAO()
            val usuario = newuserDAO.buscarCorreo(correo)

            if (correo!!.isEmpty()||contra!!.isEmpty())
                Toast.makeText(this,"Sus datos no coinciden",Toast.LENGTH_SHORT).show()

            else{
                if(usuario != null){
                    /*Esto iba en la condicion del id et_Correo.text.toString()==correo && et_Contra.text.toString()==contra*/
                    //Toast.makeText(this,"Valido ",Toast.LENGTH_SHORT).show()
                    //goToMainActivity()
                    email = usuario.email
                    pass = usuario.contra
                    if (pass == contra){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    else{
                        Toast.makeText(this, "Sus datos no coinciden", Toast.LENGTH_LONG).show()
                    }


                }

                else
                    Toast.makeText(this,"Sus datos no coinciden ",Toast.LENGTH_SHORT).show()
            }


        }



    }


  //////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////////////
   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if(requestCode==1001 && resultCode==Activity.RESULT_OK){
            var datosRecibidos : Bundle? =data!!.extras
            if(datosRecibidos != null) {
                correo = datosRecibidos.getString("correo")
                contra = datosRecibidos.getString("contra")
                //Toast.makeText(this,correo,Toast.LENGTH_SHORT).show()
            }

        }



    }*/


    /*private fun goToMainActivity(){
        var intent =Intent(this,MainActivity::class.java)
        intent.putExtra("correo",correo)
        intent.putExtra("contra",contra)


        startActivity(intent)
        finish()
    }*/





}
