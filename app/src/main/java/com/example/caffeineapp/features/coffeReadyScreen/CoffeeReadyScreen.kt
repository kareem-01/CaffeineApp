package com.example.caffeineapp.features.coffeReadyScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeineapp.LocalNavController
import com.example.caffeineapp.R
import com.example.caffeineapp.Screens
import com.example.caffeineapp.components.AppActionIcon
import com.example.caffeineapp.components.MainButton
import com.example.caffeineapp.components.ToggleButton
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.textColor87
import com.example.caffeineapp.utils.dropShadow

@Composable
fun CoffeeReadyScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val isTakeAway = remember { mutableStateOf(false) }
    val enterAnimationProgress = remember { Animatable(800f) }
    LaunchedEffect(true) {
        enterAnimationProgress.animateTo(
            0f,
            animationSpec = tween(600, easing = LinearEasing)
        )
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppActionIcon(
            painterResource(R.drawable.cancel_icon),
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp)
                .align(Alignment.Start)
                .graphicsLayer {
                    translationY = -enterAnimationProgress.value
                },
            onClick = {
                navController.navigate(Screens.Splash)
            }
        )


        Icon(
            painter = painterResource(R.drawable.right_icon),
            contentDescription = null,
            modifier = Modifier
                .graphicsLayer {
                    translationY = -enterAnimationProgress.value
                }
                .dropShadow(
                    shape = CircleShape,
                    color = Color(0x80B94B23),
                    blur = 16.dp
                )
                .background(
                    color = Color(0xFF7C351B),
                    shape = CircleShape
                )
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            tint = Color.White
        )

        Text(
            text = "Your coffee is ready,\n Enjoy",
            textAlign = TextAlign.Center,
            style = MainTextStyle,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = textColor87,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .graphicsLayer {
                    translationY = -enterAnimationProgress.value
                }
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(R.drawable.coffee_cup_sample),
                contentDescription = null,
                modifier = Modifier
                    .size(245.dp, 300.dp),
                contentScale = ContentScale.FillBounds
            )
            Image(
                painter = painterResource(R.drawable.machiato_cup_cover),
                contentDescription = null,
                modifier = Modifier
                    .size(260.dp, 69.dp)
                    .offset(y = (-20).dp)
                    .graphicsLayer {
                        translationY = -enterAnimationProgress.value
                    }
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ToggleButton(
                onToggle = {
                    isTakeAway.value = !isTakeAway.value
                },
                isOn = isTakeAway.value
            )
            Text(
                "take Away",
                style = MainTextStyle,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = textColor87.copy(.7f),
            )
        }

        MainButton(
            onClick = {
                navController.navigate(Screens.SnacksScreen)
            },
            text = "Take snack",
            modifier = Modifier.graphicsLayer {
                translationY = enterAnimationProgress.value
            }
        )

    }
}