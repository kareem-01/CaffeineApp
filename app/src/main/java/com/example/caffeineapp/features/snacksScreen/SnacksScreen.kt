package com.example.caffeineapp.features.snacksScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.caffeineapp.LocalNavController
import com.example.caffeineapp.R
import com.example.caffeineapp.Screens
import com.example.caffeineapp.components.AppActionIcon
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.textColor87
import com.example.caffeineapp.utils.noRippleClick
import kotlin.math.absoluteValue

@Composable
fun SnacksScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val imageList = listOf(
        R.drawable.oreo_image,
        R.drawable.crossiant_image,
        R.drawable.cinnabun_image,
        R.drawable.cookies_image,
        R.drawable.cup_cake_image,
        R.drawable.chocolate_image,
    )
    val pagerState = rememberPagerState(
        pageCount = { imageList.size }
    )
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AppActionIcon(
            painterResource(R.drawable.cancel_icon),
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            onClick = {
                navController.navigate(Screens.Splash)
            }
        )
        Text(
            text = "Take your snack",
            style = MainTextStyle,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = textColor87,
            modifier = Modifier.padding(start = 16.dp)
        )


        VerticalPager(
            state = pagerState,
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = 210.dp),
            reverseLayout = true,
            snapPosition = SnapPosition.Center,
            beyondViewportPageCount = 2,
        ) { page ->
            val pageOffset =
                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
            if (pageOffset > 0)
                Log.d("SnacksScreen", "Page: $page, Offset: $pageOffset")
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .aspectRatio(1f)
                    .zIndex(pageOffset)

                    .graphicsLayer {
                        val isPreviousItem = pageOffset > 0
                        if (!isPreviousItem) {
                            val scale = 1f - (pageOffset.absoluteValue * 0.25f)
                            scaleX = scale
                            scaleY = scale
                        }
                        val baseRotation = 12f
                        rotationZ = pageOffset * baseRotation
                        translationY =
                            -pageOffset * size.height / 2.5f * if (isPreviousItem) 1.2f else 1.5f
                        translationX =
                            (pageOffset * size.width / 5f) * if (isPreviousItem) -1f else 1f
                        if (pageOffset > 1) {
                            translationX =
                                -(pageOffset * size.width / 5f) * pageOffset
                            rotationZ = baseRotation / pageOffset
                        }
                    }
                    .shadow(
                        spotColor = Color.Black.copy(.12f),
                        shape = RoundedCornerShape(bottomEnd = 32.dp, topEnd = 32.dp),
                        elevation = 16.dp,
//                        ambientColor = Color.Black.copy(.12f),
                    )
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(bottomEnd = 32.dp, topEnd = 32.dp)
                    )
                    .noRippleClick {
                        navController.navigate(
                            Screens.SnackDetailsScreen(
                                snackImageResource = imageList[page]
                            )
                        )
                    },
            ) {
                Image(
                    painter = painterResource(imageList[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(56.dp)
                )
            }

        }
    }
}