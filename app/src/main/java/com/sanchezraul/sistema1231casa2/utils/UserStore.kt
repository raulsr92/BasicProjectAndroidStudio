package com.sanchezraul.sistema1231casa2.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore (private val context : Context){
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dataUser")
        val DATOS_USUARIO = stringPreferencesKey("datos_usuario")
        }

        // Método para leer:

         val leerDatosUsuario: Flow<String> = context.dataStore.data
             .map { preferences ->
                preferences[DATOS_USUARIO] ?: " "
             }
        // Método para escribir datos

        suspend fun escribirDatosUsuario(infoUsuario: String)  {
            context.dataStore.edit { settings ->
                settings[DATOS_USUARIO] = infoUsuario


            }
        }
}

