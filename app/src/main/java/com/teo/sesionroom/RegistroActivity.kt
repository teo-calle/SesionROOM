package com.teo.sesionroom

import android.R.attr.password
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.teo.sesionroom.model2.newUser
import com.teo.sesionroom.model2.newUserDAO
import kotlinx.android.synthetic.main.activity_registro.*
import java.sql.Types
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val mAuth : FirebaseAuth = FirebaseAuth.getInstance()

        val c = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes = c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        iv_calendar.setOnClickListener {
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                    tvFecha.setText("" + mDay + "/" + (mMonth + 1) + "/" + mYear)
                }, ano, mes, dia
            )
            dpd.show()
        }

        btRegistrar.setOnClickListener {

            val nombre: String = et_Nombre.text.toString()
            val correo: String = et_Correo.text.toString()
            val contra: String = et_Contrasena.text.toString()
            val rcontra: String = et_Rcontrasena.text.toString()
            var genero: String = rb_Maculino.text.toString()
            val fecha: String = tvFecha.text.toString()


           /* mAuth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        crearUsuarioEnBaseDeDatos()
                    } else {

                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }*/


            if (rb_Femenino.isChecked) {
                genero = rb_Femenino.text.toString()
            }

            if (nombre.isEmpty() || correo.isEmpty() || contra.isEmpty() || rcontra.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show()
                if (nombre.isEmpty()) {
                    et_Nombre.setError("Este campo no puede estar vacio")
                }

                if (correo.isEmpty()) {
                    et_Correo.setError("Este campo no puede estar vacio")
                }

                if (contra.isEmpty()) {
                    et_Contrasena.setError("Este campo no puede estar vacio")
                }

                if (rcontra.isEmpty()) {
                    et_Rcontrasena.setError("Este campo no puede estar vacio")
                }

            } else {
                if (contra.length > 5) {
                    if (contra != rcontra)
                        Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    /*//val usuario = newUser(Types.NULL, nombre, correo, contra)

                    //val userDAO : newUserDAO = SesionROOM.dataBase2.newUserDAO()

                    //userDAO.crearUsuario(usuario)

                    //val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    //inish()*/
                    else {
                        var intent = Intent()
                        intent.putExtra("correo", correo)
                        intent.putExtra("contra", contra)
                        setResult(Activity.RESULT_OK, intent)
                        RegisterFirebase(correo,contra)
                        finish()
                    }
                } else
                    Toast.makeText(
                        this,
                        "La contraseña debe ser de almenos 6 caracteres",
                        Toast.LENGTH_SHORT
                    ).show()

            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun RegisterFirebase(correo:String,contra:String)
    {
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        if (isEmailValid(correo)){
            auth.createUserWithEmailAndPassword(
                correo,
                contra
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("loginactivity", "signInWithEmail:success")
                        val user = auth.currentUser
                        createUserDatabase(user)


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("loginactivity", "signInWithEmail:failure", task.exception)

                        if (task.exception!!.message!!.equals("The email address is already in use by another account.")) {
                            Toast.makeText(this, "Ya existe un usuario con esa cuenta", Toast.LENGTH_SHORT).show()
                        }
                    }

                    // ...
                }
        }else{Toast.makeText(this, "Ingrese un correo valido", Toast.LENGTH_SHORT).show()}
    }

    fun isEmailValid(email: String?): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun createUserDatabase(user: FirebaseUser?){
        val nombre: String = et_Nombre.text.toString()
        var genero: String = rb_Maculino.text.toString()
        val fecha: String = tvFecha.text.toString()


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")



        var usuario = Usuario(user!!.uid,user!!.email.toString(),nombre,genero,fecha)
        myRef.child(usuario.id).setValue(usuario)
    }

}