package com.example.caffeineapp.features.order

import ProgressiveWaveIndicator
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caffeineapp.CoffeeCub
import com.example.caffeineapp.LocalNavController
import com.example.caffeineapp.R
import com.example.caffeineapp.Screens
import com.example.caffeineapp.components.AppActionIcon
import com.example.caffeineapp.components.MainButton
import com.example.caffeineapp.features.order.components.ChoicesRow
import com.example.caffeineapp.theme.MainTextStyle
import com.example.caffeineapp.theme.dark
import com.example.caffeineapp.theme.darkRed
import com.example.caffeineapp.theme.pureBlack
import com.example.caffeineapp.theme.singletFontFamily
import com.example.caffeineapp.theme.textColor87
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun OrderCoffeeScreen(coffeeCub: CoffeeCub?, modifier: Modifier = Modifier) {
    val isPhaseOne = remember { mutableStateOf(true) }
    val loadingAnimationDuration = 4000
    val previousCoffeeConcentration = remember { mutableStateOf(CoffeeConcentration.LOW) }

    val navController = LocalNavController.current
    val isEntering = remember { mutableStateOf(false) }
    val isExiting = remember { mutableStateOf(false) }
    val selectedCoffeeSpecs = remember {
        mutableStateOf(
            CoffeeCupSpecs(
                coffeeCupSize = CoffeeCupSize.MEDIUM,
                coffeeConcentration = CoffeeConcentration.LOW
            )
        )
    }
    val secondPhaseSlide by animateFloatAsState(
        targetValue = if (isPhaseOne.value) 0f else 1f,
        animationSpec = tween(
            durationMillis = 500,
            easing = if (isPhaseOne.value) EaseIn else EaseOut
        ),
    )

    val slideInOffset by animateFloatAsState(
        targetValue = when {
            isExiting.value -> 1f
            isEntering.value -> 0f
            else -> 1f
        },
        animationSpec = tween(
            durationMillis = 500,
            easing = if (isExiting.value) EaseOut else EaseIn
        ),
        label = "slideAnimation",
    )
    val beansTranslationY = remember { Animatable(0f) }
    val beansScale = remember { Animatable(1f) }
    val beansAlpha = remember { Animatable(0f) }
    val beansAnimationSpec = tween<Float>(500, easing = LinearEasing)
    val beansTargetOffset = 800f
    val beansTargetScale = 0.5f
    val beansTargetAlpha = 0f

    val isFirstComposition = remember { mutableStateOf(true) }

    val deviceHeight = LocalWindowInfo.current.containerSize.height.toFloat()
    val currentSizeScale = when (selectedCoffeeSpecs.value.coffeeCupSize) {
        CoffeeCupSize.SMALL -> 0.6f
        CoffeeCupSize.MEDIUM -> 0.8f
        CoffeeCupSize.LARGE -> 1f
    }


    val coffeeCupScaleFactor by animateFloatAsState(
        targetValue = currentSizeScale,
        animationSpec = tween(350, easing = LinearEasing),
    )

    LaunchedEffect(true) {
        isEntering.value = true
    }

    LaunchedEffect(isPhaseOne.value) {
        if (isPhaseOne.value.not()) {
            delay((loadingAnimationDuration * 1.5).toLong())
            withContext(Dispatchers.Main){
                navController.navigate(Screens.CoffeeReadyScreen)
            }
        }
    }

    LaunchedEffect(selectedCoffeeSpecs.value.coffeeConcentration) {
        if (isFirstComposition.value) {
            isFirstComposition.value = false
            return@LaunchedEffect
        }
        val isAddingCoffee =
            selectedCoffeeSpecs.value.coffeeConcentration.ordinal > previousCoffeeConcentration.value.ordinal

        if (isAddingCoffee) {
            beansTranslationY.snapTo(0f)
            beansScale.snapTo(1f)
            beansAlpha.snapTo(1f)

            launch { beansTranslationY.animateTo(beansTargetOffset, beansAnimationSpec) }
            launch {
                beansScale.animateTo(
                    targetValue = beansTargetScale,
                    animationSpec = beansAnimationSpec
                )
            }
            launch { beansAlpha.animateTo(beansTargetAlpha, beansAnimationSpec) }

        } else {
            beansTranslationY.snapTo(beansTargetOffset)
            beansScale.snapTo(beansTargetScale)
            beansAlpha.snapTo(beansTargetAlpha)

            launch { beansTranslationY.animateTo(0f, beansAnimationSpec) }
            launch {
                beansScale.animateTo(
                    targetValue = 1f,
                    animationSpec = beansAnimationSpec
                )
            }
            launch { beansAlpha.animateTo(1f, beansAnimationSpec) }
        }
        previousCoffeeConcentration.value = selectedCoffeeSpecs.value.coffeeConcentration
    }
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        translationY = deviceHeight * slideInOffset
                        if (!isPhaseOne.value)
                            translationY = -300f * secondPhaseSlide
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppActionIcon(
                    painterResource(R.drawable.arrow_right_icon),
                    onClick = {
                        isExiting.value = true
                        navController.popBackStack()
                    },
                    modifier = Modifier.graphicsLayer {
                        scaleX = -1f
                    }
                )
                AnimatedContent(
                    targetState = coffeeCub?.name ?: "Coffee",
                    transitionSpec = { fadeIn(tween(3500)) togetherWith fadeOut(tween(3500)) }
                ) { animatedText ->
                    Text(
                        text = animatedText,
                        style = MainTextStyle,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor87,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AnimatedContent(
                    targetState = selectedCoffeeSpecs.value.coffeeCupSize.size,
                    transitionSpec = {
                        fadeIn(tween(300)) togetherWith fadeOut(tween(300))
                    },
                    label = "CupSizeAnimation"
                ) { cupSize ->
                    Text(
                        text = "$cupSize ML",
                        style = MainTextStyle,
                        color = dark.copy(alpha = 0.6f),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .widthIn(min = 47.dp)
                    )
                }
                Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.height(300.dp)) {
                        Image(
                            painter = painterResource(R.drawable.coffee_cup_sample),
                            contentDescription = null,
                            modifier = Modifier
                                .animateContentSize(
                                    animationSpec = SpringSpec(
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                                .scale(coffeeCupScaleFactor)
                                .size(245.dp, 300.dp)
                        )
                    }


                    AnimatedVisibility(
                        isPhaseOne.value,
                        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 500)),
                    ) {
                        Column {
                            ChoicesRow(
                                modifier = Modifier.padding(top = 16.dp),
                                selectedIndex = selectedCoffeeSpecs.value.coffeeCupSize.ordinal,
                                onChoiceSelected = {
                                    selectedCoffeeSpecs.value = selectedCoffeeSpecs.value.copy(
                                        coffeeCupSize = CoffeeCupSize.entries[it]
                                    )
                                },
                                sizes = listOf("S", "M", "L")
                            )

                            ChoicesRow(
                                modifier = Modifier.padding(top = 16.dp),
                                selectedIndex = selectedCoffeeSpecs.value.coffeeConcentration.ordinal,
                                onChoiceSelected = {
                                    selectedCoffeeSpecs.value = selectedCoffeeSpecs.value.copy(
                                        coffeeConcentration = CoffeeConcentration.entries[it]
                                    )
                                },
                                headeredIcons = listOf(
                                    HeaderedIcon(
                                        iconResource = R.drawable.coffee_beans,
                                        header = CoffeeConcentration.LOW.title
                                    ),
                                    HeaderedIcon(
                                        iconResource = R.drawable.coffee_beans,
                                        header = CoffeeConcentration.MEDIUM.title
                                    ),
                                    HeaderedIcon(
                                        iconResource = R.drawable.coffee_beans,
                                        header = CoffeeConcentration.HIGH.title
                                    )
                                )
                            )
                        }
                    }
                }
            }

        }
        AnimatedVisibility(
            isPhaseOne.value.not(), enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ProgressiveWaveIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(bottom = 24.dp),
                    durationMillis = loadingAnimationDuration
                )
                Text(
                    text = "Almost Done",
                    style = MainTextStyle,
                    color = textColor87,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(
                    text = "Your coffee will be finish in",
                    style = MainTextStyle,
                    color = pureBlack.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                val spannedString = buildAnnotatedString {
                    val style = SpanStyle(
                        fontFamily = singletFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 32.sp,
                        color = darkRed,
                    )
                    val separatorColor = pureBlack.copy(0.12f)
                    append("CO")
                    withStyle(style.copy(color = separatorColor)) {
                        append("  :  ")
                    }
                    append("FF")
                    withStyle(style.copy(color = separatorColor)) {
                        append(" :  ")
                    }
                    append("EE")

                }
                Text(
                    text = spannedString,
                    fontFamily = singletFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = darkRed,
                )
            }


        }

        AnimatedVisibility(
            isPhaseOne.value,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            MainButton(
                text = "continue",
                onClick = {
                    isPhaseOne.value = !isPhaseOne.value
                },
                modifier = Modifier
                    .padding(16.dp)
                    .graphicsLayer {
                        translationY = 3000 * slideInOffset
                    },
            )
        }
        Image(
            painter = painterResource(id = R.drawable.coffee_beans_image),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-240).dp)
                .graphicsLayer {
                    translationY = beansTranslationY.value
                    alpha = beansAlpha.value
                }
                .scale(beansScale.value)
                .size(220.dp)
        )
    }

}