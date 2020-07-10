package com.teo.sesionroom.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.sesionroom.R
import kotlinx.android.synthetic.main.item_deudor.view.*

class DeudoresRVAdapter (
    var context: Context,
            var deudoresList: ArrayList<Deudor>
) : RecyclerView.Adapter<DeudoresRVAdapter.DeudoresViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeudoresViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_deudor, parent, false)
        return DeudoresViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = deudoresList.size

    override fun onBindViewHolder(holder: DeudoresViewHolder, position: Int) {
        val deudor: Deudor = deudoresList[position]
        holder.bindDeudor(deudor)
    }

    class DeudoresViewHolder(itemView: View, context: Context): RecyclerView.ViewHolder(itemView) {
        fun bindDeudor(deudor:Deudor){
            itemView.tv_nombre.text = deudor.nombre
            itemView.tv_deuda.text = deudor.cantidad.toString()
        }
    }
}