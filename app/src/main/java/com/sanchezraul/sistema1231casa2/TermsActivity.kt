package com.sanchezraul.sistema1231casa2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sanchezraul.sistema1231casa2.ui.theme.Color1
import com.sanchezraul.sistema1231casa2.ui.theme.Sistema1231casa2Theme

class TermsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sistema1231casa2Theme {
                Column (
                    modifier = Modifier
                        .padding(all = dimensionResource(R.dimen.space_3))
                        .padding(top = dimensionResource(R.dimen.space_4))
                ){
                    Text(
                        stringResource(R.string.terms_conditions),
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color1
                    )

                    Column (
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ){
                        Text(
                            stringResource(R.string.terms_conditions_text)
                        )

                        Button(onClick = {
                            finish()
                        }) {
                            Text(
                                stringResource(R.string.button_text4),
                            )
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_3)))

                    }
                }
            }
        }
    }
}

