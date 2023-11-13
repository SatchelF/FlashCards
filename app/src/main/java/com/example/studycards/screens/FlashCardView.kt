package com.example.studycards.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studycards.components.Flashcard
import com.example.studycards.models.Flashcard
import com.example.studycards.models.mockFlashcards

@Composable
fun FlashcardView(flashcards: List<Flashcard>) {
    var currentIndex by remember { mutableStateOf(0) }

    fun handleCardSwipe(toNext: Boolean) {
        if (toNext) {
            currentIndex = (currentIndex + 1).coerceAtMost(flashcards.size - 1)
        } else {
            currentIndex = (currentIndex - 1).coerceAtLeast(0)
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (flashcards.isNotEmpty()) {
            Flashcard(
                flashcard = flashcards[currentIndex],
                onCardChangeRequested = { toNext -> handleCardSwipe(toNext) }
            )
        }
    }


}