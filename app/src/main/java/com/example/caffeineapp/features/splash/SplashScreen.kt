package com.example.caffeineapp.features.splash

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeineapp.LocalNavController
import com.example.caffeineapp.R
import com.example.caffeineapp.Screens
import com.example.caffeineapp.components.AppHeader
import com.example.caffeineapp.components.MainButton
import com.example.caffeineapp.theme.textColor87

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "ghost_float")

    val navController = LocalNavController.current

    val ghostOffsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -50f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ghost_y_offset"
    )
    val shadowAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = .4f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ghost_y_offset"
    )


    val starAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(400),
            repeatMode = RepeatMode.Reverse
        ),
        label = "star_alpha"
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader()

        Box(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Hocus \n Pocus \n I Need Coffee to Focus",
                modifier = Modifier
                    .fillMaxWidth(.50f),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                lineHeight = 50.sp,
                color = textColor87,
                fontFamily = FontFamily(Font(R.font.sniglet))
            )

            Icon(
                painter = painterResource(R.drawable.star_icon),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(top = 8.dp)
                    .alpha(starAlpha)
            )
            Icon(
                painter = painterResource(R.drawable.star_icon),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.BottomEnd
                    )
                    .offset(x = 16.dp)
                    .alpha(starAlpha)
            )
            Icon(
                painter = painterResource(R.drawable.star_icon),
                contentDescription = null,
                modifier = Modifier
                    .align(
                        Alignment.CenterStart
                    )
                    .offset(y = (-24).dp, x = 8.dp)
                    .alpha(starAlpha)
            )


        }

        Image(
            painter = painterResource(R.drawable.ghost_image),
            contentDescription = "Ghost Image",
            modifier = Modifier
                .padding(top = 16.dp)
                .graphicsLayer {
                    translationY = ghostOffsetY
                },
        )
        Image(
            painter = painterResource(R.drawable.ghost_shadow),
            contentDescription = "Ghost Shadow",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(200.dp)
                .graphicsLayer {
                    translationY = -ghostOffsetY
                    alpha = shadowAlpha
                }
                .blur(7.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
        )
        Spacer(modifier = Modifier.weight(1f))
        MainButton(
            text = "bring my coffee", onClick = {
                navController.navigate(Screens.Home)
            },
            modifier = Modifier.padding(bottom = 16.dp),
            iconPainter = painterResource(R.drawable.coffee_icon)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(modifier = Modifier.systemBarsPadding())
}
