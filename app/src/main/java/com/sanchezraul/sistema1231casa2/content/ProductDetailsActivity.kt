package com.sanchezraul.sistema1231casa2.content

import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil3.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import org.json.JSONArray

class ProductDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Conectarnos a servicio web usado un método

        var bundle = intent.extras

        val idproducto = bundle?.getString("idproducto")

        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/productos.php?idproducto=$idproducto";

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("Response JSON CATEGORY", response);
                fillArray(response);

            },
            { })
        queue.add(stringRequest);

        enableEdgeToEdge()

    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun fillArray(response: String){
        val jsonArray = JSONArray(response);
        val nombre = jsonArray.getJSONObject(0).getString("nombre");
        val descripcion = jsonArray.getJSONObject(0).getString("descripcion");
        val precio = jsonArray.getJSONObject(0).getString("precio");
        val preciorebajado = jsonArray.getJSONObject(0).getString("preciorebajado");
        val imagengrande = jsonArray.getJSONObject(0).getString("imagengrande");
        val detalle = jsonArray.getJSONObject(0).getString("detalle");
        val proveedor = jsonArray.getJSONObject(0).getString("proveedor");

        setContent {
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
                                    text= nombre,
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
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                            .padding(0.dp,0.dp,0.dp,0.dp)
                            .background(Color.White)
                        ,

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,


                    ){
                        Text(
                            text = detalle,
                            style = MaterialTheme.typography.displayMedium,
                            color = Color2
                        )
                        Text(
                            text = proveedor,
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp),
                            color = Color2
                        )
                        AsyncImage(
                            model ="https://servicios.campus.pe/${imagengrande}",
                            contentDescription = null,
                            modifier = Modifier.clip(RoundedCornerShape(12.dp))

                            //contentScale = ContentScale.Crop
                        )
                        Text(
                            text = HtmlCompat.fromHtml(descripcion,
                                HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_DIV).toString(),
                            color = Color2,
                            modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)

                        )

                    }
                }
            }
        }    }
}

