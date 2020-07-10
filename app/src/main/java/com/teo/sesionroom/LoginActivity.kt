package com.teo.sesionroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.teo.sesionroom.model2.Deudor2DAO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var correo: String? = ""
    var contra: String? = ""

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

            val correo = et_Correo.text.toString()
            val contra = et_Contra.text.toString()

            //val deudor2DAO: Deudor2DAO = SesionROOM.database2.Deudor2DAO()
           // val email = deudor2DAO.buscarDeudor2(correo)

            if (correo!!.isEmpty()||contra!!.isEmpty())
                Toast.makeText(this,"Sus datos no coinciden",Toast.LENGTH_SHORT).show()

            else{
                if(et_Correo.text.toString()==correo && et_Contra.text.toString()==contra){
                    Toast.makeText(this,"Valido ",Toast.LENGTH_SHORT).show()
                    goToMainActivity()

                }

                else
                    Toast.makeText(this,"Sus datos no coinciden ",Toast.LENGTH_SHORT).show()
            }


        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)



        if(requestCode==1001 && resultCode==Activity.RESULT_OK){
            var datosRecibidos : Bundle? =data!!.extras
            if(datosRecibidos != null) {
                correo = datosRecibidos.getString("correo")
                contra = datosRecibidos.getString("contra")
                //Toast.makeText(this,correo,Toast.LENGTH_SHORT).show()
            }

        }



    }


    private fun goToMainActivity(){
        var intent =Intent(this,MainActivity::class.java)
        intent.putExtra("correo",correo)
        intent.putExtra("contra",contra)


        startActivity(intent)
        finish()
    }





}
