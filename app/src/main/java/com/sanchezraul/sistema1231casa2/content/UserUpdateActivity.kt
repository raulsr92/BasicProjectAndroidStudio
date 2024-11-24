package com.sanchezraul.sistema1231casa2.content

import android.os.Bundle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.dao.DatabaseProvider
import com.sanchezraul.sistema1231casa2.dao.User
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import kotlinx.coroutines.launch

class UserUpdateActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Leer data de la página origen

        var bundle = intent.extras

        val id = bundle!!.getInt("id")
        val name = bundle.getString("name")
        val email = bundle.getString("email")
        val age = bundle.getInt("age")


        enableEdgeToEdge()
        setContent {
            // Variables mutables que controlan la info de cajas de texto

            var idf by remember { mutableStateOf(id.toString()) }
            var namef by remember { mutableStateOf(name.toString()) }
            var emailf by remember { mutableStateOf(email.toString()) }
            var agef by remember { mutableStateOf(age.toString()) }

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
                                    text = "Actualizar datos",
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
                        modifier = Modifier.padding(innerPadding)
                            .background(Color.White)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .padding(0.dp, 0.dp, 0.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        )
                        {
                            Text(
                                text = "Actualizar Datos".uppercase(),
                                style = MaterialTheme.typography.displayMedium,
                                color = Color1,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        )
                        {
                            OutlinedTextField(
                                value = idf.toString(),
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    //no se puede editar, queda vacío este código
                                },
                                label = {
                                    Text(
                                        text = "Id",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                },
                                textStyle = TextStyle(color = Color2,)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = namef,
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    namef=it
                                },
                                label = {
                                    Text(
                                        text = "Nombres",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                },
                                textStyle = TextStyle(color = Color2,)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = emailf,
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    emailf=it
                                },
                                label = {
                                    Text(
                                        text = "Email",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                },
                                textStyle = TextStyle(
                                    color = Color2,
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = agef,
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    agef = it
                                },
                                label = {
                                    Text(
                                        text = "Edad",
                                        style = MaterialTheme.typography.displaySmall,
                                    )
                                },
                                textStyle = TextStyle(color = Color2,)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(50.dp, 0.dp, 50.dp, 0.dp),
                            ) {
                                Button(
                                    onClick = {
                                        val database = DatabaseProvider.getDatabase(this@UserUpdateActivity)
                                        val userDao = database.userDao()
                                        lifecycleScope.launch {
                                            val user = User(
                                                id =idf.toInt(),
                                                name = namef,
                                                email = emailf,
                                                age = agef.toInt()
                                            )

                                            userDao.updateUser(user)
                                            finish()
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center

                                    ){
                                        Text(
                                            text = "Actualizar".uppercase(),
                                            color = Color1,
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
                                        val database = DatabaseProvider.getDatabase(this@UserUpdateActivity)
                                        val userDao = database.userDao()
                                        lifecycleScope.launch {
                                            val user = User(
                                                id =idf.toInt(),
                                                name = namef,
                                                email = emailf,
                                                age = agef.toInt()
                                            )

                                            userDao.deleteUser(user)
                                            finish()
                                        }
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
}

