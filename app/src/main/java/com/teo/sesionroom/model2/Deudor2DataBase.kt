package com.teo.sesionroom.model2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Deudor2::class), version = 1)
abstract class Deudor2DataBase : RoomDatabase (){

    abstract fun Deudor2DAO() : Deudor2DAO
}