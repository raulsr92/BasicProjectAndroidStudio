package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.components.headerImageWithLayer
import com.sanchezraul.sistema1231casa2.dao.DatabaseProvider
import com.sanchezraul.sistema1231casa2.dao.User
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4
import kotlinx.coroutines.launch

class UserInsertActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var age by remember { mutableStateOf("") }

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
                                    text = "Regístrate en el club",
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
                        horizontalAlignment =  Alignment.CenterHorizontally,
                        verticalArrangement =  Arrangement.Center,
                    ){
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .padding(0.dp, 0.dp, 0.dp, 0.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        )
                        {
                            Text(
                                text = "Regístrate".uppercase(),
                                style = MaterialTheme.typography.displayMedium,
                                color = Color1,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth(  ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            )
                        {
                            OutlinedTextField(
                                value= name,
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    name=it
                                },
                                label = {
                                    Text(
                                        text="Nombres",
                                        style = MaterialTheme.typography.displaySmall,
                                        )
                                        },
                                textStyle = TextStyle(color = Color2,)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value= email,
                                //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    email = it
                                },
                                label = {
                                    Text(
                                        text="Email",
                                        style = MaterialTheme.typography.displaySmall,
                                        )
                                        },
                                textStyle = TextStyle(
                                            color = Color2,)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value= age,
                                        //shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    age = it
                                },
                                label = {
                                    Text(
                                        text="Edad",
                                        style = MaterialTheme.typography.displaySmall,
                                        )
                                        },
                                        textStyle = TextStyle(color = Color2,)
                                    )

                            Spacer(modifier = Modifier.height(20.dp))

                            Column (
                                modifier = Modifier.fillMaxWidth()
                                            .padding(50.dp,0.dp,50.dp,0.dp),
                                ) {
                                Button(
                                    onClick = {
                                        val database = DatabaseProvider.getDatabase(this@UserInsertActivity)
                                        val userDao = database.userDao()
                                        lifecycleScope.launch {
                                            val user = User(
                                                name = name,
                                                email = email,
                                                age = age.toInt()
                                            )

                                            userDao.insertUser(user)
                                            finish()
                                        }

                                    },
                                    modifier = Modifier.fillMaxWidth()
                                                .height(50.dp)
                                ) {
                                    Text(
                                        text = "Registrarse".uppercase(),
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


}

