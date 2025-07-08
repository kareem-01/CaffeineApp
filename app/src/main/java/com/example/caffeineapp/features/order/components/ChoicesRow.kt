package com.example.caffeineapp.features.order.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeineapp.features.order.HeaderedIcon
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.pureBlack
import com.example.caffeineapp.theme.textColor87
import com.example.caffeineapp.theme.white87
import com.example.caffeineapp.utils.dropShadow
import com.example.caffeineapp.utils.noRippleClick

@Composable
fun ChoicesRow(
    selectedIndex: Int,
    onChoiceSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    sizes: List<String>? = null,
    headeredIcons: List<HeaderedIcon>? = null,
) {
    val itemSize = 24.dp
    val width = 153.dp
    Column(
        modifier = Modifier.width(width)
    ) {
        Row(
            modifier = modifier
                .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(100))
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            sizes?.let { sizesList ->
                sizesList.forEachIndexed { index, item ->
                    val isSelected = index == selectedIndex

                    val textColor by calculateItemColor(
                        isSelected = isSelected,
                        selectedColor = white87,
                        unselectedColor = textColor87.copy(.6f)
                    )
                    val backgroundColor by calculateItemColor(
                        isSelected = isSelected,
                        selectedColor = Color(0xFF7C351B),
                        unselectedColor = Color.Transparent
                    )
                    val dropShadowColor by calculateItemColor(
                        isSelected = isSelected,
                        selectedColor = Color(0x80B94B23),
                        unselectedColor = Color.Transparent
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .dropShadow(
                                shape = CircleShape,
                                color = dropShadowColor,
                                blur = 16.dp
                            )
                            .background(color = backgroundColor, shape = CircleShape)
                            .padding(8.dp)
                            .size(itemSize)
                            .noRippleClick { onChoiceSelected(index) }
                    ) {
                        Text(
                            text = item,
                            style = MainTextStyle,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Center,
                        )
                    }

                }
            }

            headeredIcons?.let { iconsList ->
                iconsList.forEachIndexed { index, icon ->
                    val isSelected = index == selectedIndex
                    val iconColor by calculateItemColor(
                        isSelected = isSelected,
                        selectedColor = white87,
                        unselectedColor = Color.Transparent
                    )
                    val backgroundColor by calculateItemColor(
                        isSelected = isSelected,
                        selectedColor = Color(0xFF7C351B),
                        unselectedColor = Color.Transparent
                    )
                    val dropShadowColor by calculateItemColor(
                        isSelected = isSelected,
                        selectedColor = Color(0x80B94B23),
                        unselectedColor = Color.Transparent
                    )

                    Icon(
                        painter = painterResource(icon.iconResource),
                        contentDescription = null,
                        modifier = Modifier
                            .dropShadow(
                                shape = CircleShape,
                                color = dropShadowColor,
                                blur = 16.dp
                            )
                            .background(
                                color = backgroundColor,
                                shape = CircleShape
                            )
                            .padding(8.dp)
                            .size(itemSize)
                            .noRippleClick {
                                onChoiceSelected(index)
                            },
                        tint = iconColor
                    )

                }
            }

        }
        if (headeredIcons != null)
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                headeredIcons.forEachIndexed { index, icon ->
                    Text(
                        text = icon.header,
                        style = MainTextStyle,
                        fontSize = 10.sp,
                        color = pureBlack.copy(.6f)
                    )

                }
            }
    }

}

@Composable
private fun calculateItemColor(
    isSelected: Boolean,
    selectedColor: Color,
    unselectedColor: Color
): State<Color> {
    return animateColorAsState(
        targetValue = if (isSelected) selectedColor else unselectedColor,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearEasing,
        )
    )
}