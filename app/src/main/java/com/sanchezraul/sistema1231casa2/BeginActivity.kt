package com.sanchezraul.sistema1231casa2

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sanchezraul.sistema1231casa2.components.headerImageWithLayer
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme

class BeginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1231casa2Theme {

                Column(

                ) {
                    headerImageWithLayer(R.drawable.begin_imagen,R.string.begin_image_alt,R.string.button_text1)


                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = dimensionResource(R.dimen.space_3)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            stringResource(R.string.button_text1),
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            stringResource(R.string.paragraph_1),
                        )
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Button(onClick = {
                                startActivity(Intent(this@BeginActivity, TermsActivity::class.java))
                            }) {
                                Text(stringResource(R.string.button_text2),)
                            }
                            Button(onClick = {
                                startActivity(Intent(this@BeginActivity, HomeActivity::class.java))

                            }) {
                                Text(stringResource(R.string.button_text3),)
                            }
                        }

                    }
                }

            }
        }
    }
}


