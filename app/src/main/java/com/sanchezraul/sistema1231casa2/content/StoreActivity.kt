package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.MainActivity
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.TermsActivity
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import org.json.JSONArray

class StoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/categorias.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("Response JSON", response);
                fillArray(response);

            },
            { })
        queue.add(stringRequest);

        enableEdgeToEdge()
        setContent {
            Sistema1231casa2Theme {

            }
        }
    }

    private fun fillArray(response: String){
        val jsonArray = JSONArray(response);
        val arrayList = ArrayList<HashMap<String, String>>();

        for (i in 0 until jsonArray.length()){
            val idcategoria = jsonArray.getJSONObject(i).getString("idcategoria");
            val nombre = jsonArray.getJSONObject(i).getString("nombre");
            val descripcion = jsonArray.getJSONObject(i).getString("descripcion");
            val foto = jsonArray.getJSONObject(i).getString("foto");
            val total = jsonArray.getJSONObject(i).getString("total");

            val hashMap = HashMap<String, String>();
            hashMap["idcategoria"] = idcategoria
            hashMap["nombre"] = nombre
            hashMap["descripcion"] = descripcion
            hashMap["foto"] = foto
            hashMap["total"] = total

            arrayList.add(hashMap);
        }
        //Ordenar por categoría

        arrayList.sortBy { it["idcategoria"] }

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
                                    text= stringResource(id = R.string.title_activity_store),
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
                        )//TopAppBar
                    }
                ){ innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                            .background(Color.White)
                    ){
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp),
                            modifier = Modifier.padding(10.dp,20.dp,10.dp,20.dp)
                        ) {
                            items(arrayList) { item ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .border(2.dp, MaterialTheme.colorScheme.tertiary,RoundedCornerShape(10.dp))

                                ){
                                    AsyncImage(
                                        model = "https://servicios.campus.pe/${item["foto"]}",
                                        contentDescription = null,
                                        Modifier.height(150.dp).fillMaxWidth(),
                                        contentScale = ContentScale.Crop
                                    )

                                    Column(
                                        modifier = Modifier.fillMaxSize()
                                            .height(150.dp)
                                            .padding(14.dp, 15.dp, 15.dp, 15.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .border(0.dp, Color.Black.copy(alpha = 0.1f),RoundedCornerShape(10.dp))
                                            .background(Color.Black.copy(alpha = 0.4f)) // para que salga redondeado el color de fondo, se ubica después de bordes

                                            .clickable { selectCategory(item) },

                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Row (
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(4.dp, 6.dp, 4.dp, 6.dp)
                                                .fillMaxWidth()
                                                //.clip(RoundedCornerShape(10.dp))
                                                //.border(4.dp, MaterialTheme.colorScheme.tertiary,RoundedCornerShape(10.dp))
                                        ){
                                            Text(
                                                text = item["idcategoria"].toString() ,
                                                style = MaterialTheme.typography.headlineLarge,
                                                color = Color4
                                            )
                                            Spacer(
                                                modifier= Modifier.width(15.dp)
                                            )
                                            Column {
                                                Text(
                                                    text = item["nombre"].toString(),
                                                    style = MaterialTheme.typography.displayLarge,
                                                    color = Color.White
                                                )
                                                Text(
                                                    text = item["descripcion"].toString(),
                                                    style = MaterialTheme.typography.displaySmall,
                                                    color = Color.White
                                                )
                                                Text(
                                                    text = "Total productos: "+item["total"].toString(),
                                                    style = MaterialTheme.typography.displaySmall,
                                                    color = Color.White
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

    private fun selectCategory(item: HashMap<String, String>) {

        //Encapsular datos a enviar
        val bundle = Bundle().apply {
            putString("idcategoria", item["idcategoria"])
            putString("nombre", item["nombre"])

        }

        val intent = Intent(this, ProductsActivity::class.java)

        // Enviar los datos

        intent.putExtras(bundle);

        startActivity(intent)

    }
}

