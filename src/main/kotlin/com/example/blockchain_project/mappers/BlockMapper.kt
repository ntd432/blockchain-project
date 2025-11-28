package com.example.blockchain_project.mappers

import com.example.blockchain_project.entities.BlockEntity
import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Data

class BlockMapper<T: Data> {
    fun toEntity(domain: Block<T>): BlockEntity<T> {
        return BlockEntity(
                id = domain.id,
                hash = domain.hash,
                previousHash = domain.previousHash,
                chainId = domain.chainId,
                nonce = domain.nonce,
                index = domain.index,
                data = domain.data,
                timeStamp = domain.timeStamp
        )
    }

    fun toDomain(entity: BlockEntity<T>): Block<T> {
        val block = Block<T>(
                id = entity.id,
                previousHash = entity.previousHash,
                chainId = entity.chainId,
                nonce = entity.nonce,
                index = entity.index,
                data = entity.data,
                timeStamp = entity.timeStamp
        )
        block.hash = entity.hash
        return block
    }

    // List<Entity> → List<Domain>
    fun toDomainList(entities: List<BlockEntity<T>>): List<Block<T>> =
            entities.map { toDomain(it) }

    // List<Domain> → List<Entity>
    fun toEntityList(blocks: List<Block<T>>): List<BlockEntity<T>> =
            blocks.map { toEntity(it) }
}