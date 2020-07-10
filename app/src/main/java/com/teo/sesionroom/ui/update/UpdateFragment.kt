package com.teo.sesionroom.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.teo.sesionroom.R
import com.teo.sesionroom.SesionROOM
import com.teo.sesionroom.model.Deudor
import com.teo.sesionroom.model.DeudorDAO
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_update, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_telefono2.visibility = View.GONE
        et_cantidad2.visibility = View.GONE
        bt_actualizar.visibility = View.GONE
        var idDeudor = 0
        val deudorDAO: DeudorDAO = SesionROOM.database.DeudorDAO()

        bt_buscar2.setOnClickListener {
            val nombre = et_nombre3.text.toString()
            val deudor = deudorDAO.buscarDeudor(nombre)

            if(deudor != null){
                idDeudor = deudor.id
                et_telefono2.visibility = View.VISIBLE
                et_cantidad2.visibility = View.VISIBLE
                et_telefono2.setText(deudor.telefono)
                et_cantidad2.setText(deudor.cantidad.toString())
                bt_buscar2.visibility = View.GONE
                bt_actualizar.visibility = View.VISIBLE
            }
        }

        bt_actualizar.setOnClickListener {
            val deudor = Deudor(idDeudor, et_nombre3.text.toString(), et_telefono2.text.toString(), et_cantidad2.text.toString().toLong())
            deudorDAO.actualizarDeudor(deudor)
            et_telefono2.visibility = View.GONE
            et_cantidad2.visibility = View.GONE
            bt_actualizar.visibility = View.GONE
            bt_buscar2.visibility = View.VISIBLE
        }
    }
}