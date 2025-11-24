package com.example.blockchain_project.mappers

import com.example.blockchain_project.entities.BlockchainEntity
import com.example.blockchain_project.models.Blockchain

object BlockchainMapper {
    fun toEntity(domain: Blockchain): BlockchainEntity {
        return BlockchainEntity(
                id = domain.id,
                difficulty = domain.difficulty,
                consensusAlgorithm = domain.consensusAlgorithm
        )
    }

    fun toDomain(entity: BlockchainEntity): Blockchain {
        return Blockchain(
                id = entity.id,
                difficulty = entity.difficulty,
                consensusAlgorithm = entity.consensusAlgorithm
        )
    }
}