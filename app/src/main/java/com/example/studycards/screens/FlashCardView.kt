package com.example.studycards.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studycards.components.Flashcard
import com.example.studycards.models.Flashcard
import com.example.studycards.models.mockFlashcards

@Composable
fun FlashcardView() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Flashcard(flashcard = mockFlashcards[1])
    }
}