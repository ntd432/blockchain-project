package com.example.blockchain_project.mappers

import com.example.blockchain_project.entities.BlockEntity
import com.example.blockchain_project.models.Block

object BlockMapper {
    fun toEntity(domain: Block): BlockEntity {
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

    fun toDomain(entity: BlockEntity): Block {
        return Block(
                id = entity.id,
                previousHash = entity.previousHash,
                chainId = entity.chainId,
                nonce = entity.nonce,
                index = entity.index,
                data = entity.data,
                timeStamp = entity.timeStamp
        )
    }

    // List<Entity> → List<Domain>
    fun toDomainList(entities: List<BlockEntity>): List<Block> =
            entities.map { toDomain(it) }

    // List<Domain> → List<Entity>
    fun toEntityList(blocks: List<Block>): List<BlockEntity> =
            blocks.map { toEntity(it) }
}