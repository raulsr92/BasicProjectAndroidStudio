package com.sanchezraul.sistema1231casa2.dao

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    private var INSTANCE: UserDatabase? = null

            fun getDatabase(context: Context): UserDatabase{

                return INSTANCE ?: synchronized(this){

                    val newInstance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).build()
                    INSTANCE = newInstance
                    newInstance
                }
            }
}