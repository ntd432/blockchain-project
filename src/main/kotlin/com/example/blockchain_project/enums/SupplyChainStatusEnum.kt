package com.example.blockchain_project.enums

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class SupplyChainStatusEnum(@JsonValue val status: String) {
    MANUFACTURED("Manufactured"),
    PACKED("Packed"),
    COLLECTED_BY_DISTRIBUTOR("Collected by Distributor"),
    IN_TRANSIT_TO_RETAILER("In Transit to Retailer"),
    DELIVERED("Delivered"),
    PURCHASED("Purchased");

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