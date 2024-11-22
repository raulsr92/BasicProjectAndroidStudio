package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sanchezraul.sistema1231casa2.BeginActivity
import com.sanchezraul.sistema1231casa2.MainActivity
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.utils.UserStore
import kotlinx.coroutines.launch

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showAlertDialog by remember { mutableStateOf(false) }


            Sistema1231casa2Theme {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    Text(
                        text = "Bienvenido",
                    )
                    Button(
                        onClick = {
                            showAlertDialog = true;
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ){
                        Text(text = "Cerrar Sesión")
                    }

                    if (showAlertDialog){
                        AlertDialog(
                            onDismissRequest = { /*TODO*/ },
                            title = {
                                Text(
                                    text = "Cerrar Sesión",
                                    style = MaterialTheme.typography.displayLarge,
                                    color = Color2
                                ) },
                            text = {
                                Text(
                                    text = "¿Desea cerrar sesión?",
                                    style = MaterialTheme.typography.displaySmall
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = Color.Red
                                )
                            },
                            confirmButton = {
                                ElevatedButton(onClick = {
                                    cerrarSesión()
                                },
                                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                        containerColor = Color2
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(
                                        text = "Si",
                                        color = Color.White
                                    )
                                }
                            },
                            dismissButton = {
                                ElevatedButton(onClick = {
                                    showAlertDialog = false
                                },
                                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                        containerColor = Color2
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(text = "No",
                                        color = Color.White
                                    )
                                }
                            },
                            containerColor = Color4,
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                }

            }
        }
    }

    private fun cerrarSesión() {

        val userStore = UserStore(this)

        lifecycleScope.launch {

            userStore.escribirDatosUsuario(" ")

            finish()

            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
        }
    }
}

