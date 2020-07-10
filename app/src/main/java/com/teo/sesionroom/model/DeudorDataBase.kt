package com.teo.sesionroom.model

import androidx.room.Database
import androidx.room.RoomDatabase

/*Es abstract porque no se instancia nunca*/

@Database(entities = arrayOf(Deudor::class), version =1)
abstract  class DeudorDataBase : RoomDatabase() {

    abstract fun DeudorDAO() : DeudorDAO
}