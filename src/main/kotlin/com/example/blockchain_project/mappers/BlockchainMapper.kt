package com.example.blockchain_project.mappers

import com.example.blockchain_project.entities.BlockchainEntity
import com.example.blockchain_project.models.Blockchain
import com.example.blockchain_project.models.Data

class BlockchainMapper<T: Data> {
    fun toEntity(domain: Blockchain<T>): BlockchainEntity {
        return BlockchainEntity(
                id = domain.id,
                difficulty = domain.difficulty,
                consensusAlgorithm = domain.consensusAlgorithm,
                type = domain.type
        )
    }

    fun toDomain(entity: BlockchainEntity): Blockchain<T> {
        return Blockchain(
                id = entity.id,
                difficulty = entity.difficulty,
                consensusAlgorithm = entity.consensusAlgorithm,
                type = entity.type
        )
    }
}