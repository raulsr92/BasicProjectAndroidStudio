package com.sanchezraul.sistema1231casa2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1231casa2Theme {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.space_4)),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    Text(
                        stringResource(R.string.version),
                        style = MaterialTheme.typography.displaySmall)

                    Text(
                        stringResource(R.string.author),
                        style = MaterialTheme.typography.displayLarge)

                }

                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        stringResource(R.string.greeting),
                        //style = TextStyle(fontSize = 48.sp)
                        style = MaterialTheme.typography.displaySmall
                    )

                    Button(onClick = {
                        startActivity(Intent(this@MainActivity, BeginActivity::class.java))
                    },
                        shape = RoundedCornerShape(10.dp),
                        /*colors = ButtonDefaults.buttonColors(
                            containerColor = Color(245,11,64,120)
                        )*/
                    ) { Text(
                        stringResource(R.string.button_text1),
                    )
                    }
                }

                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom =  dimensionResource(R.dimen.space_3)),
                    contentAlignment = Alignment.BottomCenter

                ){
                    Text(
                        stringResource(R.string.copyright),
                    )
                }

            }
        }
    }
}

