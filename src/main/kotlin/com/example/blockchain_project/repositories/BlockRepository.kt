package com.example.blockchain_project.repositories

import com.example.blockchain_project.entities.BlockEntity
import com.example.blockchain_project.models.Data
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BlockRepository<T: Data> : MongoRepository<BlockEntity<T>, String>{
    fun save(blockEntity: BlockEntity<T>): BlockEntity<T>
    fun findByChainId(chainId: String?): List<BlockEntity<T>>
}