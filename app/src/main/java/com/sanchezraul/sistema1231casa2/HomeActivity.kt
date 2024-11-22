package com.sanchezraul.sistema1231casa2

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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sanchezraul.sistema1231casa2.content.DirectorsActivity
import com.sanchezraul.sistema1231casa2.content.EmployeesActivity
import com.sanchezraul.sistema1231casa2.content.LoginActivity
import com.sanchezraul.sistema1231casa2.content.StoreActivity
import com.sanchezraul.sistema1231casa2.content.SuppliersActivity
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myArray = arrayOf("Proveedores", "Empleados", "Tienda","Clientes","Directores",
            "Salir")

        val cardIcons = arrayOf(R.drawable.supplier, R.drawable.employee, R.drawable.store,
            R.drawable.customers,
            R.drawable.director,
            R.drawable.exit)

        enableEdgeToEdge()
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
                                    stringResource(R.string.button_text3),
                                    style = MaterialTheme.typography.displayLarge,
                                    color = Color.White
                                )
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                                /*containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.primary*/
                            )
                            {
                                NavigationBar {
                                    NavigationBarItem(
                                        selected = false,
                                        onClick = {},
                                        icon = {
                                            Image(
                                                painter = painterResource(id=R.drawable.home),
                                                contentDescription = "icons",
                                                modifier = Modifier.size(20.dp),
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        },
                                        label={Text("Inicio")},
                                    )
                                    NavigationBarItem(
                                        selected = false,
                                        onClick = {},
                                        icon = {
                                            Image(
                                                painter = painterResource(id=R.drawable.search),
                                                contentDescription = "icons",
                                                modifier = Modifier.size(20.dp),
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        },
                                        label={Text("Buscar")},
                                    )
                                    NavigationBarItem(
                                        selected = false,
                                        onClick = {},
                                        icon = {
                                            Image(
                                                painter = painterResource(id=R.drawable.notification),
                                                contentDescription = "icons",
                                                modifier = Modifier.size(20.dp),
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        },
                                        label={Text("Alertas")},
                                    )
                                    NavigationBarItem(
                                        selected = false,
                                        onClick = {},
                                        icon = {
                                            /*Icon(
                                                imageVector = Icons.Default.Home,
                                                contentDescription = null,
                                            )*/
                                            Image(
                                                painter = painterResource(id=R.drawable.menu),
                                                contentDescription = "icons",
                                                modifier = Modifier.size(20.dp),
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        },

                                        label={Text("Menú")},
                                    )
                                }
                            }

                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                            .background(Color.White)
                            .fillMaxSize()

                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize(),
                        ) {
                            items(myArray.size) { index ->
                                Card (
                                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    modifier = Modifier.clickable {
                                        showWindow(index)

                                    }
                                ){
                                    Column (
                                        modifier = Modifier.padding(dimensionResource(R.dimen.space_2))
                                    ){
                                        Image(
                                            painter = painterResource(id = cardIcons[index]),
                                            contentDescription = "icons",
                                            modifier = Modifier.size(40.dp),
                                            colorFilter = ColorFilter.tint(Color1)
                                        )
                                        Text(
                                            myArray[index],
                                            color = Color1

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

    private fun showWindow(index: Int) {
        Log.d("Verificado el click de la card", index.toString())

        when(index){
            0 -> startActivity(Intent(this@HomeActivity, SuppliersActivity::class.java))
            1 -> startActivity(Intent(this@HomeActivity, EmployeesActivity::class.java))
            2 -> startActivity(Intent(this@HomeActivity, StoreActivity::class.java))
            3 -> startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            4 -> startActivity(Intent(this@HomeActivity, DirectorsActivity::class.java))
            5 -> finish()

        }
    }
}

