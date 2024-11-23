package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.utils.BASE_URL

class DirectorsUpdateActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Leer data de la página origen

        var bundle = intent.extras

        val iddirector = bundle!!.getString("iddirector")
        val nombres = bundle.getString("nombres")
        val peliculas = bundle.getString("peliculas")


        enableEdgeToEdge()
        setContent {

            // Variables mutables que controlan la info de cajas de texto

            var idDirector by remember { mutableStateOf(iddirector.toString()) }
            var directorName by remember { mutableStateOf(nombres.toString()) }
            var pelicula by remember { mutableStateOf(peliculas.toString()) }

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
                                    text = stringResource(id = R.string.title_activity_directors_update),
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
                            .background(Color.White))
                    {
                        Spacer(modifier = Modifier.height(15.dp))

                        Column (
                            modifier = Modifier.fillMaxWidth()
                                .padding(10.dp, 20.dp, 10.dp, 10.dp)
                            ,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ){
                            Text(
                                text = "Actualizar/Eliminar información de director".uppercase(),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.displayMedium,
                                color = Color1
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            OutlinedTextField(
                                value= idDirector,
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                },
                                label = {
                                    Text(
                                        text="Identificador",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                },
                                textStyle = TextStyle(
                                    color = Color2,
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))

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

                            Spacer(modifier = Modifier.height(30.dp))

                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(50.dp,0.dp,50.dp,0.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = {
                                        updateDirector(idDirector,directorName,pelicula)
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                        .height(55.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center

                                    ) {
                                        Text(
                                            text = "Actualizar".uppercase(),
                                            color  = Color1,
                                            style = MaterialTheme.typography.displayMedium,
                                        )
                                        Spacer(modifier = Modifier.width(15.dp))
                                        Image(
                                            painter = painterResource(id = R.drawable.edit),
                                            contentDescription = "icons",
                                            modifier = Modifier.size(25.dp),
                                            colorFilter = ColorFilter.tint(Color1)
                                        )
                                    }

                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = {
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                        .height(55.dp)
                                ) {
                                    Row (
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ){
                                        Text(
                                            text = "Eliminar".uppercase(),
                                            color  = Color1,
                                            style = MaterialTheme.typography.displayMedium,
                                        )
                                        Spacer(modifier = Modifier.width(15.dp))

                                        Image(
                                            painter = painterResource(id = R.drawable.trashblack),
                                            contentDescription = "icons",
                                            modifier = Modifier.size(25.dp),
                                            colorFilter = ColorFilter.tint(Color1)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateDirector(idDirector: String, directorName: String, pelicula: String) {

        Log.d("Response", idDirector + " " +directorName + " " + pelicula)

        val queue = Volley.newRequestQueue(this)
        val url = BASE_URL +"directoresupdate.php"

        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("Response JSON ", response);

                Toast.makeText(this, "Se actualizó director", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this@DirectorsUpdateActivity, DirectorsActivity::class.java))
            },
            {  }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["iddirector"]= idDirector
                params["nombres"] = directorName
                params["peliculas"] = pelicula
                return params
            }
        }
        queue.add(stringRequest)
    }
}

