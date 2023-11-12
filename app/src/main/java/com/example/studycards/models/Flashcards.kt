package com.example.studycards.models

data class Flashcard(
    val id: Int,
    val front: String,
    val type: String,
    val back: String

)

// List of 20 mock flashcards
val mockFlashcards = listOf(
Flashcard(1, "Front of Card 1 ", "Type", "Back of Card 1"),
Flashcard(2, "Front of Card 2 Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1Front of Card 1", "Type", "Back of Card 2"),
Flashcard(3, "Front of Card 3", "Type", "Back of Card 3"),
Flashcard(4, "Front of Card 4", "Type", "Back of Card 4"),
Flashcard(5, "Front of Card 5", "Type", "Back of Card 5"),
Flashcard(6, "Front of Card 6", "Type", "Back of Card 6"),
Flashcard(7, "Front of Card 7", "Type", "Back of Card 7"),
Flashcard(8, "Front of Card 8", "Type", "Back of Card 8"),
Flashcard(9, "Front of Card 9", "Type", "Back of Card 9"),
Flashcard(10, "Front of Card 10", "Type", "Back of Card 10"),
Flashcard(11, "Front of Card 11", "Type", "Back of Card 11"),
Flashcard(12, "Front of Card 12", "Type", "Back of Card 12"),
Flashcard(13, "Front of Card 13", "Type", "Back of Card 13"),
Flashcard(14, "Front of Card 14", "Type", "Back of Card 14"),
Flashcard(15, "Front of Card 15", "Type", "Back of Card 15"),
Flashcard(16, "Front of Card 16", "Type", "Back of Card 16"),
Flashcard(17, "Front of Card 17", "Type", "Back of Card 17"),
Flashcard(18, "Front of Card 18", "Type", "Back of Card 18"),
Flashcard(19, "Front of Card 19", "Type", "Back of Card 19"),
Flashcard(20, "Front of Card 20", "Type", "Back of Card 20")
)