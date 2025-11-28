package com.example.blockchain_project.enums

import com.fasterxml.jackson.annotation.JsonCreator

enum class SupplyChainStatusEnum(val status: String) {
    MANUFACTURED("Manufactured"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromValue(value: String): SupplyChainStatusEnum {
            return SupplyChainStatusEnum.values().firstOrNull {
                it.status.equals(value, ignoreCase = true) ||
                        it.name.equals(value, ignoreCase = true)
            } ?: throw IllegalArgumentException("Unknown status: $value")
        }
    }
}