package com.example.caffeineapp.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.example.caffeineapp.CoffeeCub
import com.example.caffeineapp.R
import com.example.caffeineapp.components.AppHeader
import com.example.caffeineapp.components.MainButton
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.textColor87
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val coffeeCubsList = listOf(
        CoffeeCub(
            image = R.drawable.black_coffee,
            name = "Black",
        ),
        CoffeeCub(
            image = R.drawable.latte_coffee,
            name = "Latte",
        ),
        CoffeeCub(
            image = R.drawable.macchiato_coffee,
            name = "Macchiato",
        ),
        CoffeeCub(
            image = R.drawable.espresso_coffee,
            name = "Espresso",
        )
    )
    val pagerState = rememberPagerState(pageCount = { 4 })
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader()
        Text(
            text = "Good Morning",
            fontSize = 36.sp,
            color = Color(0xFFB3B3B3),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            style = MainTextStyle,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Hamsa ☀",
            style = MainTextStyle,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3B3B3B),
            fontSize = 36.sp,
        )

        Text(
            text = "What would you like to drink today?",
            modifier = Modifier.padding(start = 16.dp, top = 4.dp),
            fontSize = 16.sp,
            color = textColor87.copy(.8f)

        )
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 90.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) { page ->
            val pageOffset =
                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue.coerceIn(
                    0f,
                    1f
                )
            val animatedHeight = lerp(
                start = 150.dp,
                stop = 244.dp,
                fraction = 1f - pageOffset
            )
            val animatedWidth = lerp(
                start = 120.dp,
                stop = 199.dp,
                fraction = 1f - pageOffset
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .height(244.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = coffeeCubsList[page].image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = animatedWidth, height = animatedHeight)
                            .align (Alignment.BottomCenter)
                        ,
                        contentScale = ContentScale.Fit,

                        )
                }
                Text(
                    text = coffeeCubsList[page].name,
                    fontSize = 32.sp,
                    style = MainTextStyle,
                    fontWeight = FontWeight.Bold,
                )
            }

        }

        Spacer(Modifier.weight(1f))
        MainButton(
            onClick = {
                //TODO
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
    }

}

