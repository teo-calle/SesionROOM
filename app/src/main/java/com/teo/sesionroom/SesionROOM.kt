package com.teo.sesionroom

import android.app.Application
import androidx.room.Room
import com.teo.sesionroom.model.DeudorDataBase
import com.teo.sesionroom.model2.newUser
import com.teo.sesionroom.model2.newUserDataBase

class SesionROOM : Application() {

    companion object{
        lateinit var database: DeudorDataBase
        lateinit var dataBase2: newUserDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DeudorDataBase::class.java,
            "misdeudores_db"
        ).allowMainThreadQueries()
            .build()

        dataBase2 = Room.databaseBuilder(
            this,
            newUserDataBase::class.java,
            "usuarios_db"
        ).allowMainThreadQueries()
            .build()
    }
}