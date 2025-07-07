
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import kotlinx.coroutines.isActive
import kotlin.math.PI
import kotlin.math.sin

/**
 * A loading indicator that draws and erases a wave. Both the drawing
 * and erasing animations accelerate (start slow and get faster).
 */
@Composable
fun ProgressiveWaveIndicator(
    modifier: Modifier = Modifier,
    wavelength: Float = 200f,
    strokeWidth: Float = 8f,
    waveColor: Color = Color.Black,
    durationMillis: Int = 3000
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(durationMillis) {

        while (isActive) {
            progress.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = durationMillis ,
                    easing = EaseInCubic
                )
            )

            progress.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = durationMillis ,
                    easing = EaseInCubic
                )
            )
        }
    }

    val wavePath = remember { Path() }

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height



        val dynamicAmplitude = ((height - strokeWidth) / 2f).coerceAtLeast(0f)
        val centerY = height / 2f
        val angularFrequency = (2 * PI / wavelength).toFloat()

        wavePath.reset()
        wavePath.moveTo(0f, centerY + dynamicAmplitude * sin(angularFrequency * 0f))
        for (x in 1..width.toInt()) {
            val y = centerY + dynamicAmplitude * sin(angularFrequency * x)
            wavePath.lineTo(x.toFloat(), y)
        }

        clipRect(right = width * progress.value) {
            drawPath(
                path = wavePath,
                color = waveColor,
                style = Stroke(width = strokeWidth)
            )
        }
    }
}