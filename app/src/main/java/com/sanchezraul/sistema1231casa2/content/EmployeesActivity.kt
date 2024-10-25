package com.sanchezraul.sistema1231casa2.content

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import org.json.JSONArray

class EmployeesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/empleados.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->

                Log.d("Response JSON", response);

                fillArray(response);

            },
            {  })

        queue.add(stringRequest)

        enableEdgeToEdge()

    }

    private fun fillArray(response: String){
        val jsonArray = JSONArray(response);
        val arrayList = ArrayList<HashMap<String, String>>();

        for (i in 0 until jsonArray.length()){
            val nombres = jsonArray.getJSONObject(i).getString("nombres");
            val apellidos = jsonArray.getJSONObject(i).getString("apellidos");
            val cargo = jsonArray.getJSONObject(i).getString("cargo");
            val foto = jsonArray.getJSONObject(i).getString("foto");


            val hashMap = HashMap<String, String>();
            hashMap["nombres"] = nombres
            hashMap["apellidos"] = apellidos
            hashMap["cargo"] = cargo
            hashMap["foto"] = foto

            arrayList.add(hashMap);
        }

        drawList(arrayList);
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun drawList(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp
            val density = LocalDensity.current.density
            val screenHeightpx = screenHeight * density;

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
                                    text= stringResource(id = R.string.title_activity_employees),
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
                ){ innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding).fillMaxSize()
                        .background(Color.White)
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier
                            .background(Color3)
                            .padding(2.dp,5.dp,2.dp,5.dp),

                    ) {
                        items(arrayList) { employee ->
                            Box(
                                modifier=Modifier.fillParentMaxSize()
                            ){
                                DrawEmployees(employee,screenHeightpx);

                            }
                        }
                    }
                }}

            }
        }
    }

    // Crear nueva función

    @Composable
    fun DrawEmployees(employee: HashMap<String, String>, screenHeightpx: Float) {

            AsyncImage(
                model = "https://servicios.campus.pe/fotos/"+employee["foto"].toString(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.White),
                            startY = screenHeightpx * 0.5f,
                            endY = screenHeightpx * 1f,
                        )
                    )
                    .padding(10.dp, 0.dp,0.dp,10.dp), // el padding va después del degradado
                verticalArrangement = Arrangement.Bottom

            )
            {
                Text(
                    text = employee["nombres"].toString() + " " +employee["apellidos"].toString(),
                    style = MaterialTheme.typography.displayLarge
                    )
                Text(
                    text = employee["cargo"].toString(),
                    style = MaterialTheme.typography.displaySmall
                    )
            }
    }

}

