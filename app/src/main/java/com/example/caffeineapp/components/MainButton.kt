package com.example.caffeineapp.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.caffeineapp.R
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.pureBlack

@Composable
fun MainButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Continue",
    iconPainter: Painter = painterResource(R.drawable.arrow_right_icon),
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = pureBlack,
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text,
            fontWeight = FontWeight.W700,
            style = MainTextStyle
        )
        Icon(
            painter = iconPainter,
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun MainButtonPreview() {
    MainButton(text = "Get Started", onClick = {})
}
