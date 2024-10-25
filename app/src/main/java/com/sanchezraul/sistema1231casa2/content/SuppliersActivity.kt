package com.sanchezraul.sistema1231casa2.content

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import org.json.JSONArray

class SuppliersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/proveedores.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("Response JSON", response);
                fillArray(response);
            },
            { })
        queue.add(stringRequest);

        enableEdgeToEdge()

    }

    private fun fillArray(response: String){

        val jsonArray = JSONArray(response);
        val arrayList = ArrayList<HashMap<String, String>>();

        for (i in 0 until jsonArray.length()){
            val idproveedor = jsonArray.getJSONObject(i).getString("idproveedor");
            val nombreempresa = jsonArray.getJSONObject(i).getString("nombreempresa");
            val nombrecontacto = jsonArray.getJSONObject(i).getString("nombrecontacto");
            val cargocontacto = jsonArray.getJSONObject(i).getString("cargocontacto");
            val ciudad = jsonArray.getJSONObject(i).getString("ciudad");
            val pais = jsonArray.getJSONObject(i).getString("pais");

            val hashMap = HashMap<String, String>();
            hashMap["idproveedor"] = idproveedor
            hashMap["nombreempresa"] = nombreempresa
            hashMap["nombrecontacto"] = nombrecontacto
            hashMap["cargocontacto"] = cargocontacto
            hashMap["ciudad"] = ciudad
            hashMap["pais"] = pais

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
                                    text= stringResource(id = R.string.title_activity_suppliers),
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
                    AsyncImage(
                        model = "https://martech.org/wp-content/uploads/2021/09/Cinemark.jpg",
                        contentDescription = null,
                    )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.padding(10.dp,40.dp,10.dp,40.dp)
                    ) {
                    items(arrayList) { item ->
                        Column(
                            modifier = Modifier.border(1.dp, MaterialTheme.colorScheme.primary)
                                .padding(12.dp, 15.dp, 12.dp, 15.dp).fillMaxWidth()
                        ) {
                            Text(
                                text = item["nombreempresa"].toString(),
                                style = MaterialTheme.typography.displayLarge
                            )
                            Text(
                                text = item["nombrecontacto"].toString() ,
                                style = MaterialTheme.typography.displaySmall
                            )
                            Text(
                                text = item["cargocontacto"].toString(),
                                style = MaterialTheme.typography.displaySmall
                            )
                            Text(
                                text = item["ciudad"].toString() + " - " + item["pais"].toString(),
                                style = MaterialTheme.typography.displaySmall
                            )
                        }

                    }
                }
                }}

            }
        }
    }
}
