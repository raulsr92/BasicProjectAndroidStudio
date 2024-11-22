package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4

class DirectorsInsertActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var directorName by remember { mutableStateOf("") }
            var pelicula by remember { mutableStateOf("") }

            Sistema1231casa2Theme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.shadow(
                                elevation = 4.dp, // Adjust the elevation value as needed
                                spotColor = Color3, // Adjust the shadow color if needed
                                ambientColor = Color2 // Adjust the ambient shadow color if needed
                            ),
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    text = stringResource(id = R.string.title_activity_directors_insert),
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
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment =  Alignment.CenterHorizontally,
                        verticalArrangement =  Arrangement.Bottom,

                    ) {
                        Spacer(modifier = Modifier.height(15.dp))

                        Column (
                            modifier = Modifier.fillMaxWidth()
                                .padding(0.dp, 20.dp, 0.dp, 10.dp)
                            ,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ){
                            Text(
                                text = "Registra un nuevo director".uppercase(),
                                style = MaterialTheme.typography.displayMedium,
                                color = Color1
                            )
                        }
                        Spacer(modifier = Modifier.height(0.dp))
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                            OutlinedTextField(
                                value= directorName,
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    directorName = it
                                },
                                label = {
                                    Text(
                                        text="Director",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                },
                                textStyle = TextStyle(
                                    color = Color2,
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value= pelicula,
                                //shape = RoundedCornerShape(20.dp),

                                onValueChange = {
                                    pelicula = it
                                },
                                label = {
                                    Text(
                                        text="Película",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                },
                                textStyle = TextStyle(
                                    color = Color2,
                                )
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(50.dp,0.dp,50.dp,0.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = {
                                        insertDirector(directorName,pelicula)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                        .height(55.dp)

                                ) {
                                    Text(
                                        text = "Guardar".uppercase(),
                                        color  = Color1,
                                        style = MaterialTheme.typography.displayMedium,
                                        )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private fun insertDirector(directorName: String, pelicula: String) {
        Log.d("Response", directorName + " dirigió: "+ pelicula)

        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/directoresinsert.php"

        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("Response JSON ", response);

                startActivity(Intent(this@DirectorsInsertActivity, DirectorsActivity::class.java))
            },
            {  }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["nombres"] = directorName
                params["peliculas"] = pelicula
                return params
            }
        }
        queue.add(stringRequest)
    }


}

