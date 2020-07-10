package com.teo.sesionroom.model2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(newUser::class), version = 1)
abstract class newUserDataBase : RoomDatabase (){

    abstract fun newUserDAO() : newUserDAO
}