package com.example.studycards.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.studycards.components.Flashcard
import com.example.studycards.models.Flashcard
import com.example.studycards.models.mockFlashcards

@Composable
fun FlashcardView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Adjust padding to position the card in the top third of the screen
        Flashcard(flashcard = mockFlashcards[1])
    }
}