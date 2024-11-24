package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sanchezraul.sistema1231casa2.BeginActivity
import com.sanchezraul.sistema1231casa2.MainActivity
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.utils.UserStore
import com.sanchezraul.sistema1231casa2.utils.usuarioActivo
import kotlinx.coroutines.launch

class ProfileActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showAlertDialog by remember { mutableStateOf(false) }
            Sistema1231casa2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    text = stringResource(id = R.string.title_activity_profile),
                                    style = MaterialTheme.typography.displayLarge,
                                    color = Color.White
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        finish() // para que se cierre el activity
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Column (
                        modifier = Modifier.padding(innerPadding)
                    ){
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ){
                            Image(
                                painterResource(R.drawable.storeinner),
                                contentDescription = "null",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            // Capa que va encima de la imagen grande para que el texto se note
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f))
                            )
                            Column(
                                modifier = Modifier.fillMaxSize()
                                   // .background(Color.White)
                                ,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Black.copy(alpha = 0.4f))
                                        .padding(0.dp,20.dp,0.dp,20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ){
                                    Text(
                                        text = "Bienvenido".uppercase(),
                                        style = MaterialTheme.typography.displayLarge,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))

                                    Text(
                                        text = usuarioActivo.getString("nombres"),
                                        style = MaterialTheme.typography.headlineLarge,
                                        color = Color4

                                    )
                                }

                                Spacer(modifier = Modifier.height(230.dp))

                                Button(
                                    onClick = {
                                        showAlertDialog = true;
                                    },
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                        .height(55.dp).width(250.dp)
                                        .shadow(
                                            elevation = 7.dp,
                                            shape = RoundedCornerShape(14.dp),
                                        ),
                                    colors =ButtonDefaults.buttonColors(
                                        containerColor = Color1,

                                    )

                                    ) {
                                    Text(
                                        text = "Cerrar Sesión".uppercase(),
                                        color = Color4,
                                        style = MaterialTheme.typography.displayMedium
                                    )
                                }

                                if (showAlertDialog) {
                                    AlertDialog(
                                        onDismissRequest = { /*TODO*/ },
                                        title = {
                                            Text(
                                                text = "Cerrar Sesión",
                                                style = MaterialTheme.typography.displayLarge,
                                                color = Color2
                                            )
                                        },
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
                                            ElevatedButton(
                                                onClick = {
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
                                            ElevatedButton(
                                                onClick = {
                                                    showAlertDialog = false
                                                },
                                                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                                    containerColor = Color2
                                                ),
                                                shape = RoundedCornerShape(12.dp)
                                            ) {
                                                Text(
                                                    text = "No",
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
            }
        }//
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

