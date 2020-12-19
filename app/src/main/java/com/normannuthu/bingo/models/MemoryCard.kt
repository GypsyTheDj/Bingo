package com.normannuthu.bingo.models

data class MemoryCard(
        val identifier: Int,
        var isFaceUp: String = false,
        var isMatched: Boolean = false
)