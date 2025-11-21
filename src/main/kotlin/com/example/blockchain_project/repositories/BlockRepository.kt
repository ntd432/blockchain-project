package com.example.blockchain_project.repositories

import com.example.blockchain_project.entities.BlockEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BlockRepository : MongoRepository<BlockEntity, String>{
    fun save(blockEntity: BlockEntity): BlockEntity
    fun findByChainId(chainId: String?): List<BlockEntity>
}