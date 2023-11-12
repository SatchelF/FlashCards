package com.example.studycards.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.studycards.models.Flashcard
import kotlinx.coroutines.launch

@Composable
fun Flashcard(flashcard: Flashcard) {
    val scope = rememberCoroutineScope()

    // Rotation degrees of the card
    val rotation = remember { Animatable(0f) }

    // Whether the card is flipped
    var isFlipped by remember { mutableStateOf(false) }

    // Gesture modifier for handling drag gestures
    val gestureModifier = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures(
            onDragStart = {
                // This is where we can set state when the user starts dragging
            },
            onDragEnd = {
                // When the user stops dragging
                if (!isFlipped) {
                    scope.launch {
                        rotation.animateTo(0f, tween(500))
                    }
                }
            },
            onHorizontalDrag = { change, dragAmount ->
                scope.launch {
                    // Calculate the potential new rotation value
                    val newRotation = rotation.value + dragAmount * 0.2f // Adjust this multiplier for sensitivity

                    // Check if we've reached the threshold to flip the card
                    if (newRotation <= -90f && !isFlipped) {
                        // Card is now flipped to back
                        isFlipped = true
                        rotation.snapTo(90f) // Snap rotation to start showing the back
                    } else if (newRotation >= 90f && isFlipped) {
                        // Card is now flipped to front
                        isFlipped = false
                        rotation.snapTo(-90f) // Snap rotation to start showing the front
                    } else {
                        // If the thresholds are not reached, just update the rotation value
                        rotation.snapTo(newRotation)
                    }
                }
                change.consumeAllChanges()
            }
        )
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(0.95f)
                .graphicsLayer {
                    // Correctly display the text based on the flip state
                    rotationY = if (isFlipped) rotation.value - 180f else rotation.value
                }
                .then(gestureModifier) // Apply the gesture modifier
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = if (!isFlipped) flashcard.front else flashcard.back,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}






