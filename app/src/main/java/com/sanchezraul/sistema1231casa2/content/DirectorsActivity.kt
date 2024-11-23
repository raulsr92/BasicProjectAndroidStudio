package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.content.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4
import com.sanchezraul.sistema1231casa2.utils.BASE_URL
import org.json.JSONArray

class DirectorsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val queue = Volley.newRequestQueue(this)
        val url = BASE_URL+"directores.php"

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
            val iddirector = jsonArray.getJSONObject(i).getString("iddirector");
            val nombres = jsonArray.getJSONObject(i).getString("nombres");
            val peliculas = jsonArray.getJSONObject(i).getString("peliculas");

            val hashMap = HashMap<String, String>();
            hashMap["iddirector"] = iddirector
            hashMap["nombres"] = nombres
            hashMap["peliculas"] = peliculas

            arrayList.add(hashMap);
        }

        drawList(arrayList);
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun drawList(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme {
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
                                    text = stringResource(id = R.string.title_activity_directors),
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
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@DirectorsActivity,
                                        DirectorsInsertActivity::class.java
                                    )
                                )
                            },
                            containerColor = Color1,

                            contentColor = Color4
                        ) {
                            Icon(
                                Icons.Filled.Add, "Botón para agregar nuevo director",
                                tint = Color4,

                            )
                        }

                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                            .background(Color.White)
                    ) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.padding(10.dp, 40.dp, 10.dp, 40.dp)
                        ) {
                            items(arrayList) { item ->
                                Column(
                                    modifier = Modifier.border(
                                        1.dp, MaterialTheme.colorScheme.primary
                                    )
                                        .padding(12.dp, 15.dp, 12.dp, 15.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            selectDirector(item)
                                        }
                                ) {
                                    Row(
                                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = item["iddirector"].toString(),
                                            style = MaterialTheme.typography.displayLarge,
                                            modifier = Modifier.padding(end = 10.dp)
                                        )
                                        Column {
                                            Text(
                                                text = item["nombres"].toString(),
                                                color = Color1,
                                                style = MaterialTheme.typography.displayMedium,
                                            )
                                            Text(
                                                text = item["peliculas"].toString(),
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
        }
    }

    private fun selectDirector(item: HashMap<String, String>) {

        val intent = Intent(this, DirectorsUpdateActivity::class.java)

        var bundle = Bundle().apply {
            putString("iddirector", item["iddirector"])
            putString("nombres", item["nombres"])
            putString("peliculas", item["peliculas"])
        }
        intent.putExtras(bundle);

        startActivity(intent)
    }
}

