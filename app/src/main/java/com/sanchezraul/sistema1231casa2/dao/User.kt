package com.sanchezraul.sistema1231casa2.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "users")
@TypeConverters(Converters::class)
data class User (

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name:String,
    val email:String,
    val age: Int,

    @ColumnInfo(name = "created_at") val createdAt: Date = Date()

    //val createdAt: Long = System.currentTimeMillis()

)
