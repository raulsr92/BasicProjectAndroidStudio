package com.sanchezraul.sistema1231casa2.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanchezraul.sistema1231casa2.R
import com.sanchezraul.sistema1231casa2.ui.theme.Color3
import com.sanchezraul.sistema1231casa2.ui.theme.Color4

// Composable functions
@Composable
fun headerImageWithLayer(drawableResource: Int, descriptionResource: Int, titleResource: Int) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.height(400.dp)

    ){
        Image(
            painterResource(id = drawableResource), //id es variable Integer
            modifier = Modifier.height(400.dp),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = descriptionResource) //id es variable Integer
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        Text(
            stringResource(id = titleResource), //id es variable Integer
            color = Color4,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(all = dimensionResource(R.dimen.space_3))
        )

    }
}