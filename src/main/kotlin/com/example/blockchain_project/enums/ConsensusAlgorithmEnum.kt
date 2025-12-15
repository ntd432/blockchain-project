package com.example.blockchain_project.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class ConsensusAlgorithmEnum(@JsonValue val algorithm: String) {
    PROOF_OF_WORK("Proof of work"),
    NONE("None");

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromValue(value: String): ConsensusAlgorithmEnum {
            return values().firstOrNull {
                it.algorithm.equals(value, ignoreCase = true) ||
                        it.name.equals(value, ignoreCase = true)
            } ?: throw IllegalArgumentException("Unknown algorithm: $value")
        }
    }
}