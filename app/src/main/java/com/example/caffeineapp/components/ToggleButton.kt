package com.example.caffeineapp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeineapp.R
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.textColor87
import com.example.caffeineapp.utils.noRippleClick

@Composable
fun ToggleButton(
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
    offStateColor: Color = Color(0xFFFFEEE7),
    animatedToggleIconPainter: Painter = painterResource(R.drawable.machiato_coffee_top),
    isOn: Boolean = false,

    ) {
    val backgroundColor by animateColorAsState(
        if (isOn) offStateColor else Color(0xFF7C351B),
        animationSpec = tween(durationMillis = 500)
    )
    val alignment by animateFloatAsState(
        if (isOn) 1f else -1f,
        animationSpec = tween(durationMillis = 500)
    )


    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .width(80.dp)
            .noRippleClick(onClick = onToggle),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Text(
                "OFF",
                style = MainTextStyle,
                fontWeight = FontWeight.Bold,
                color = textColor87.copy(.6f),
                fontSize = 12.sp
            )
            Text(
                "ON",
                style = MainTextStyle,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(.6f),
                fontSize = 12.sp
            )
        }
        Image(
            painter = animatedToggleIconPainter,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .align(BiasAlignment(alignment, 0f))
        )
    }

}