package com.example.caffeineapp.features.sweetDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.singletFontFamily
import com.example.caffeineapp.theme.textColor87

@Composable
fun SnackDetailsScreen(imageResource: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val navController = LocalNavController.current
        AppActionIcon(
            painterResource(R.drawable.cancel_icon),
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp)
                .align(Alignment.Start),
            onClick = {
                navController.navigate(Screens.Splash)
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.coffee_beans_icon),
                contentDescription = "Coffee Beans Icon",

                )
            Text(
                text = "More Espresso, Less Depresso",
                fontFamily = singletFontFamily,
                color = Color(0xFF7C351B),
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.coffee_beans_icon),
                contentDescription = "Coffee Beans Icon",

                )
        }

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Snack Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Bon appétit",
                style = MainTextStyle,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = textColor87.copy(.8f)
            )
            Image(
                painter = painterResource(R.drawable.magic_wand_icon),
                contentDescription = null
            )

        }
        Spacer(modifier = Modifier.weight(1f))
        MainButton(
            onClick = {
                navController.navigate(Screens.Splash)
            },
            text = "Thank youu",
            modifier = Modifier
        )


    }
}