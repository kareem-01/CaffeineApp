package com.example.caffeineapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.caffeineapp.theme.lightGray
import com.example.caffeineapp.theme.textColor87

@Composable
fun AppActionIcon(painter: Painter, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Icon(
        painter = painter,
        contentDescription = "Add Icon",
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .background(
                shape = CircleShape, color = lightGray
            )
            .padding(12.dp),
        tint = textColor87
    )
}