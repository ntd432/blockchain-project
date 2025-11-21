package com.example.blockchain_project.repositories

import com.example.blockchain_project.entities.BlockchainEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface BlockchainRepository : MongoRepository<BlockchainEntity, String>{
    fun save(blockchainEntity: BlockchainEntity): BlockchainEntity
    fun findById(id: Id?): BlockchainEntity
}