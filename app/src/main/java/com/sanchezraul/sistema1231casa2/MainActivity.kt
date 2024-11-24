package com.sanchezraul.sistema1231casa2

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1231casa2Theme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painterResource(R.drawable.store1),
                        contentDescription = "null",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,

                        )
                    // Capa que va encima de la imagen grande para que el texto se note
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                    )

                    // Contenedor de la parte del contenido de esta pantalla
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            //.background(Color.White)
                            .padding(0.dp,30.dp,0.dp,0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                //.padding(0.dp, 25.dp,0.dp,15.dp)
                                    ,
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Spacer(modifier = Modifier.height(40.dp))

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(alpha = 0.3f))
                                    .padding(0.dp,20.dp,0.dp,20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                                Text(
                                    stringResource(R.string.presentation),
                                    style = MaterialTheme.typography.displaySmall)
                                Text(
                                    stringResource(R.string.name_main),
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = Color1
                                )
                            }

                        }
                        Spacer(modifier = Modifier.height(120.dp))

                        Column (
                            modifier = Modifier
                                //.background(Color.White)
                                .padding(0.dp, 1.dp, 0.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ){

                            Column( //se dibuja una columna mas interna para poder hacer padding y que el background transparente
                                // no esté tan pegado a la parte superior e inferior
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(alpha = 0.3f))
                                    .padding(0.dp,20.dp,0.dp,20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Image(
                                        painter =  painterResource(R.drawable.peru),
                                        contentDescription = "Bandera de Perú",
                                        modifier = Modifier.height(40.dp)
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))

                                    Image(
                                        painter =  painterResource(R.drawable.ecuador),
                                        contentDescription = "Bandera de Ecuador",
                                        modifier = Modifier.height(40.dp)
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))

                                    Image(
                                        painter =  painterResource(R.drawable.chile),
                                        contentDescription = "Bandera de Chile",
                                        modifier = Modifier.height(40.dp)
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))

                                    Image(
                                        painter =  painterResource(R.drawable.colombia),
                                        contentDescription = "Bandera de Colombia",
                                        modifier = Modifier.height(40.dp)
                                    )

                                }
                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    stringResource(R.string.greeting),
                                    //style = TextStyle(fontSize = 48.sp)
                                    style = MaterialTheme.typography.displaySmall,
                                    textAlign = TextAlign.Center,
                                    color= Color3
                                )

                                Spacer(modifier = Modifier.height(30.dp))
                                Button(
                                    onClick = {
                                        startActivity(Intent(this@MainActivity, BeginActivity::class.java))
                                    },
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.height(50.dp).width(200.dp)

                                    /*colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(245,11,64,120)
                                    )*/
                                ) { Text(
                                    stringResource(R.string.button_text1).uppercase(),
                                    color  = Color1,
                                    style = MaterialTheme.typography.displayMedium,
                                )
                                }
                            }

                        }

                        Column  (
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(0.dp, 30.dp, 0.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ){
                            Spacer(modifier = Modifier.height(80.dp))

                            Column( //se dibuja una columna mas interna para poder hacer padding y que el background transparente
                                // no esté tan pegado a la parte superior e inferior
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black.copy(alpha = 0.6f))
                                    .padding(0.dp,20.dp,0.dp,20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ){

                                Text(
                                    stringResource(R.string.copyright),
                                    style = MaterialTheme.typography.displaySmall
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    stringResource(R.string.creator),
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }


                        }
                    }
                }
            }
        }
    }
}

