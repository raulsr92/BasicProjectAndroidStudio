package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4
import org.json.JSONArray

class ProductsActivity : ComponentActivity() {

    var nombre: String = ""


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Leer data de la página origen

        var bundle = intent.extras

        val idcategoria = bundle?.getString("idcategoria")

        nombre = bundle?.getString("nombre").toString()


        //Conectarnos a servicio web usado un método

        readService(idcategoria);

        enableEdgeToEdge()
    }

    private fun readService(idcategoria: String?) {

        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/productos.php?idcategoria=$idcategoria";

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("Response JSON CATEGORY", response);
                fillArray(response);

            },
            { })
        queue.add(stringRequest);

    }

    private fun fillArray(response: String) {
        val jsonArray = JSONArray(response);
        val arrayList = ArrayList<HashMap<String, String>>();

        for (i in 0 until jsonArray.length()){
            val idproducto = jsonArray.getJSONObject(i).getString("idproducto");
            val nombre = jsonArray.getJSONObject(i).getString("nombre");
            val precio = jsonArray.getJSONObject(i).getString("precio");
            val preciorebajado = jsonArray.getJSONObject(i).getString("preciorebajado");
            val imagenchica = jsonArray.getJSONObject(i).getString("imagenchica");


            val hashMap = HashMap<String, String>();
            hashMap["idproducto"] = idproducto
            hashMap["nombre"] = nombre
            hashMap["precio"] = precio
            hashMap["preciorebajado"] = preciorebajado
            hashMap["imagenchica"] = imagenchica

            arrayList.add(hashMap);
        }

        drawList(arrayList);
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun drawList(arrayList: ArrayList<HashMap<String, String>>) {
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
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color.White)

                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(vertical = 10.dp),

                            modifier = Modifier
                                .fillMaxSize()
                                //.background(Color4)
                                .padding(10.dp, 10.dp, 10.dp, 30.dp)

                        ) {
                            items(arrayList){ product ->
                                Card (
                                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    modifier = Modifier
                                        .height(300.dp)
                                        .clickable {

                                            startActivity(Intent(
                                                this@ProductsActivity,
                                                ProductDetailsActivity::class.java
                                            ).apply {
                                                putExtra("idproducto", product["idproducto"])
                                            })

                                        }

                                ){
                                    Column (
                                        //modifier = Modifier.padding(8.dp,10.dp,5.dp,10.dp)

                                    )  {
                                        val precioRebajado = product["preciorebajado"].toString().toFloat();
                                        val precio = product["precio"].toString().toFloat();
                                        val porcentajeDescuento = (1-(precioRebajado/precio))*100;

                                        var rutaImagen = "https://servicios.campus.pe/imagenes/nofoto.jpg"

                                        if (product["imagenchica"] != "null"){
                                            rutaImagen = "https://servicios.campus.pe/${product["imagenchica"].toString()}"
                                        }
                                        Box(
                                            //contentAlignment = Alignment.TopStart
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(12.dp, 10.dp, 8.dp, 10.dp),
                                                    verticalArrangement = Arrangement.Center
                                            ) {

                                                AsyncImage(
                                                    model = rutaImagen,
                                                    contentDescription = null,
                                                    modifier = Modifier.fillMaxWidth()//.height(100.dp)
                                                         ,
                                                    //contentScale = ContentScale.Crop
                                                )
                                                Spacer(
                                                    modifier= Modifier.height(7.dp)
                                                )
                                                Text(
                                                    text = product["nombre"].toString(),
                                                    style = MaterialTheme.typography.displayMedium
                                                )
                                                Spacer(
                                                    modifier= Modifier.height(7.dp)
                                                )

                                                if (precioRebajado == 0f) {
                                                    Text(
                                                        text = "Precio: S/ " + "%.2f".format(precio),
                                                        style = MaterialTheme.typography.displaySmall
                                                    )
                                                } else {
                                                    Text(
                                                        text = "Oferta: S/ " + "%.2f".format(precioRebajado),
                                                        style = MaterialTheme.typography.displaySmall
                                                    )
                                                    Spacer(
                                                        modifier= Modifier.height(5.dp)
                                                    )
                                                    Text(
                                                        text = "Precio: S/ " + "%.2f".format(precio),
                                                        textDecoration = TextDecoration.LineThrough,
                                                        color = Color.Red
                                                    )
                                                }
                                            }
                                            if (precioRebajado != 0f) {
                                                Text(
                                                    text = "Dscto: " + "%.0f".format(porcentajeDescuento) + " %",
                                                    style = TextStyle(color = Color4, fontSize = 15.sp, fontWeight = FontWeight.Bold),
                                                    modifier = Modifier
                                                        .background(Color1)
                                                        .padding(9.dp, 10.dp, 9.dp, 10.dp)
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
        }

    }
}

