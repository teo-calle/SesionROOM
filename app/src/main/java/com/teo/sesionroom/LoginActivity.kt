package com.teo.sesionroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.teo.sesionroom.model2.newUserDAO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

   // var correo: String? = ""
    //var contra: String? = ""
    var email = ""
    var pass =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


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
