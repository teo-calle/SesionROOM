package com.teo.sesionroom.model

import androidx.room.*


@Dao
interface DeudorDAO {

    @Insert
    fun crearDeudor(deudor: Deudor)


    @Query("SELECT * FROM tabla_deudor WHERE email LIKE :nombre")
    fun buscarDeudor(nombre: String): Deudor


    @Update
    fun actualizarDeudor(deudor: Deudor)

    @Delete
    fun borrarDeudor(deudor: Deudor)

}