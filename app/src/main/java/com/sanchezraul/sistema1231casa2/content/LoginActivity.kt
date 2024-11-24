package com.sanchezraul.sistema1231casa2.content

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.utils.BASE_URL
import com.sanchezraul.sistema1231casa2.utils.UserStore
import com.sanchezraul.sistema1231casa2.utils.usuarioActivo
import kotlinx.coroutines.launch
import org.json.JSONArray

class LoginActivity : ComponentActivity() {

    var checkSaveSession = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var usuario by remember { mutableStateOf("") }
            var clave by remember { mutableStateOf("") }

            // para el checkbox

            var checkBoxState by remember { mutableStateOf(false) }

            Sistema1231casa2Theme {

                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ){

                    Text(
                        text = "Iniciar Sesión",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color1
                    )

                    OutlinedTextField(
                        value = usuario,
                        onValueChange = {
                            usuario = it
                        },
                        label = { Text("Usuario") },
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = clave,
                        onValueChange = {
                            clave = it
                        },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Guardar la sesión"
                        )

                        Checkbox(
                            checked = checkBoxState,
                            onCheckedChange = {
                                checkBoxState = it

                                checkSaveSession = it;

                            }
                        )

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            loginService(usuario, clave);
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
                            .height(45.dp)

                    ) {
                        Text(
                            text = "Iniciar Sesión",
                            color  = Color1,
                            )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            startActivity(Intent(this@LoginActivity, UserActivity::class.java))
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth()
                            .height(45.dp)
                    ) {
                        Text(
                            text = "Únete al Club",
                            color  = Color1,
                            )
                    }
                }
            }
        }
    }

    private fun loginService(usuario: String, clave: String) {

        Log.d("loginService", "Usuario: $usuario, Clave: $clave")

        // Conectarnos a Servicio WEB

        val queue = Volley.newRequestQueue(this)
        val url = BASE_URL+"iniciarsesion.php"

        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("Response JSON", response);

                verifyLogin(response);

            },
            { }){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["usuario"] = usuario
                params["clave"] = clave
                return params
            }
            }

        queue.add(stringRequest);

    }

    private fun verifyLogin(response: String) {

        when(response){
            "-1" -> Toast.makeText(this,"El usuario no existe", Toast.LENGTH_SHORT).show()
            "-2" -> Toast.makeText(this,"Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                else -> {
                    Toast.makeText(this,"Bienvenido", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ProfileActivity::class.java))
                    verifySaveSesion(response);
                    finish()
                }
        }

    } // verifyLogin

    private fun verifySaveSesion(response: String) {

        usuarioActivo = JSONArray(response).getJSONObject(0)

        if (checkSaveSession){

            val userStore = UserStore(this)

            lifecycleScope.launch {

                userStore.escribirDatosUsuario(response)
            }

        }
    }
}

