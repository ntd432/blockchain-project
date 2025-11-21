package com.example.blockchain_project.models

data class Blockchain (val id: String? = null,
                        val difficulty: Long = 4,
                        var chain: List<Block>? = null) {
}