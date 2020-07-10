package com.teo.sesionroom.model2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_users")
class newUser (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id : Int,
    @ColumnInfo(name="nombre") val nombre : String,
    @ColumnInfo(name="email") val email : String,
    @ColumnInfo(name="contra") val contra : String
)