package com.teo.sesionroom.model2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Deudor2DAO {

    @Insert
    fun crearDeudor2(deudor2: Deudor2)

    @Query("SELECT * FROM table_deudor2 WHERE email LIKE :correo")
    fun buscarDeudor2(correo: String): Deudor2

}

