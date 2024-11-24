package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.dao.DatabaseProvider
import com.sanchezraul.sistema1231casa2.dao.User
import com.sanchezraul.sistema1231casa2.dao.UserDao
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.ui.theme.Color2
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4

class UserActivity : ComponentActivity() {

    private lateinit var userDao: UserDao

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(this)

        userDao = database.userDao()


        enableEdgeToEdge()
        setContent {

            var userList = remember { mutableStateOf(listOf<User>()) }

            LaunchedEffect(Unit) {
                userDao.getAllUsers().collect{ users ->
                    userList.value  = users
                }
            }
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
                                    text = "Miembros del Club",
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
                                startActivity(Intent(this@UserActivity, UserInsertActivity::class.java))
                            },
                            containerColor = Color1,
                            contentColor = Color4
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add, "Botón para agregar nuevo user",
                                tint = Color4,
                                )
                        }

                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize()
                            .background(Color.White)
                    ){
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(14.dp),
                            modifier = Modifier.padding(10.dp, 40.dp, 10.dp, 40.dp)
                        ) {
                            items(userList.value) { user ->
                                Column(
                                    modifier = Modifier.border(
                                        1.dp, MaterialTheme.colorScheme.primary)
                                        .padding(12.dp, 15.dp, 12.dp, 15.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            val intent = Intent(this@UserActivity, UserUpdateActivity::class.java)
                                            intent.putExtra("id", user.id)
                                            intent.putExtra("name", user.name)
                                            intent.putExtra("email", user.email)
                                            intent.putExtra("age", user.age)

                                            startActivity(intent)
                                        }
                                ){
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Text(
                                            text = user.id.toString(),
                                            style = MaterialTheme.typography.headlineLarge,
                                            color = Color1
                                        )
                                        Spacer(modifier = Modifier.width(20.dp))

                                        Column {
                                            Text(
                                                text = user.name,
                                                style = MaterialTheme.typography.displayLarge,
                                                color = Color2
                                            )
                                            Text(
                                                text = user.email,
                                                style = MaterialTheme.typography.displayMedium,
                                                )
                                            Text(
                                                text = user.age.toString(),
                                                style = MaterialTheme.typography.displayMedium,
                                                color = Color3
                                                )
                                            Text(
                                                text = user.createdAt.toString(),
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
}

