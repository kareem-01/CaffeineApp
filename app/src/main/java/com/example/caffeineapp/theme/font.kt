package com.example.caffeineapp.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.caffeineapp.R

val urbanistFontFamily = FontFamily(Font(R.font.urbanist))
val singletFontFamily = FontFamily(Font(R.font.sniglet))
val MainTextStyle = TextStyle(
    fontFamily = urbanistFontFamily,
    letterSpacing = .25.sp,
)