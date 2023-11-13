package com.example.studycards.components
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.studycards.models.Flashcard
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun Flashcard(
    flashcard: Flashcard,
    onCardChangeRequested: (Boolean) -> Unit
) {
    val rotation = remember { Animatable(0f) }
    val isFlipped = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                        rotation.stop()
                        awaitPointerEventScope {
                            horizontalDrag(pointerId) { change ->
                                val dragAmount = change.positionChange().x
                                val newRotation = rotation.value + dragAmount / 10f

                                launch {
                                    rotation.snapTo(clamp(newRotation))

                                    // Flip card logic
                                    when {
                                        !isFlipped.value && newRotation <= -90f -> {
                                            isFlipped.value = true
                                            rotation.snapTo(90f) // Swap to back text
                                        }
                                        isFlipped.value && newRotation >= 90f -> {
                                            isFlipped.value = false
                                            rotation.snapTo(-90f) // Swap to front text
                                        }
                                    }
                                }

                                // Swipe to change card logic
                                if (isFlipped.value && dragAmount < 0) {
                                    // Swiped left on the back of the card
                                    onCardChangeRequested(true)
                                } else if (!isFlipped.value && dragAmount > 0) {
                                    // Swiped right on the front of the card
                                    onCardChangeRequested(false)
                                }
                            }
                        }

                        // Reset rotation if not a swipe
                        launch {
                            if (rotation.value != 0f && rotation.value != -180f && rotation.value != 180f) {
                                rotation.animateTo(0f, animationSpec = spring(.75f, 50f))
                            }
                        }
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.90f)
                .padding(top = 200.dp)
                .height(300.dp)
                .padding(vertical = 16.dp)
                .graphicsLayer {
                    rotationY = rotation.value
                    cameraDistance = 12f * density
                }
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = flashcard.type,
                        modifier = Modifier.align(Alignment.Start),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isFlipped.value) flashcard.back else flashcard.front,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

fun clamp(value: Float, min: Float = -180f, max: Float = 180f): Float {
    return when {
        value >= max -> max
        value <= min -> min
        else -> value
    }
}