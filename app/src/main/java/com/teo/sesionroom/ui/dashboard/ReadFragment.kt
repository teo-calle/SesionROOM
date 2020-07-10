package com.teo.sesionroom.ui.dashboard;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.teo.sesionroom.R
import com.teo.sesionroom.SesionROOM
import com.teo.sesionroom.model.Deudor
import com.teo.sesionroom.model.DeudorDAO
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_read.*
import java.sql.Types.NULL

class ReadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_read, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_buscar.setOnClickListener {
            val nombre = et_nombre2.text.toString()

            val deudorDAO: DeudorDAO = SesionROOM.database.DeudorDAO()
            val deudor = deudorDAO.buscarDeudor(nombre)

            if (deudor != null){
                tv_resultado.text=
                    "\nNombre: ${deudor.nombre}\n" +
                            "Telefono: ${deudor.telefono}\n" +
                            "Cantidad: ${deudor.cantidad}"
            }else{
                Toast.makeText(context, "Deudor no existe", Toast.LENGTH_LONG).show() }
        }
    }


}