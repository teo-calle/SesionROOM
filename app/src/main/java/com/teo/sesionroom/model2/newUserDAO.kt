package com.teo.sesionroom.model2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface newUserDAO {

    @Insert
    fun crearUsuario(newuser: newUser)

    @Query("SELECT * FROM table_users WHERE email LIKE :correo")
    fun buscarCorreo(correo: String): newUser

}

