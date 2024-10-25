package com.sanchezraul.sistema1231casa2.content

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.content.ui.theme.Sistema1231casa2Theme

class ProductDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Conectarnos a servicio web usado un método

        var bundle = intent.extras

        val idproducto = bundle?.getString("idproducto")


        enableEdgeToEdge()
        setContent {
            Sistema1231casa2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column (
                            modifier = Modifier.padding(innerPadding)
                        ){
                            Text(
                                "Hola"
                            )
                        }
                }
            }
        }
    }
}

