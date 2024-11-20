package com.sanchezraul.sistema1231casa2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sanchezraul.sistema1231casa2.content.ProfileActivity
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme
import com.sanchezraul.sistema1231casa2.utils.UserStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var startAnimation by remember { mutableStateOf(false) }

            val offsetY by animateDpAsState(

                targetValue = if (startAnimation) 0.dp else 1500.dp,
                animationSpec = tween(durationMillis = 1000)
            )

            val alpha by animateFloatAsState(
                targetValue = if (startAnimation) 1f else 0f,
                animationSpec = tween(durationMillis = 4000)
            )

            val scale by animateFloatAsState(
                targetValue = if (startAnimation) 1f else 3f,
                animationSpec = tween(durationMillis = 4000)
            )

            Sistema1231casa2Theme {

                LaunchedEffect(key1 = true) {
                    startAnimation  = true
                    delay(5000)

                    val userStore = UserStore(this@SplashActivity);

                    lifecycleScope.launch {

                        val data = userStore.leerDatosUsuario.first();

                        if (data==" "){
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        } else{
                            startActivity(Intent(this@SplashActivity, ProfileActivity::class.java))
                        }

                        finish()

                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter= painterResource (R.drawable.logo),
                        contentDescription = "El logo de la empresa",
                        modifier = Modifier
                            .offset(y=offsetY)
                            .graphicsLayer(
                                alpha = alpha,
                                scaleX = scale,
                                scaleY = scale
                            )

                    )

                }



            }
        }
    }
}

