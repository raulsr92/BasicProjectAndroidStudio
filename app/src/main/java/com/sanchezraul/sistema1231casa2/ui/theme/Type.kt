package com.sanchezraul.sistema1231casa2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sanchezraul.sistema1231casa2.R

val Urbanist = FontFamily(
    Font(R.font.urbanist_black, FontWeight.Black),
    Font(R.font.urbanist_bold, FontWeight.Bold),
    Font(R.font.urbanist_regular, FontWeight.Normal),
    Font(R.font.urbanist_extralight, FontWeight.ExtraLight),
    )


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    displaySmall = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp,
        color= Color(0xFF4F75FF)
    ),

    displayLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp,
        color= Color(0xFF6439FF)
    ),

    displayMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp,
        color= Color(0xFF6439FF)
    ),

    headlineLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp
    )

)