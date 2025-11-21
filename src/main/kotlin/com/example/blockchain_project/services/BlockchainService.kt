package com.example.blockchain_project.services

import com.example.blockchain_project.mappers.BlockMapper
import com.example.blockchain_project.mappers.BlockchainMapper
import com.example.blockchain_project.models.Blockchain
import com.example.blockchain_project.repositories.BlockRepository
import com.example.blockchain_project.repositories.BlockchainRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class BlockchainService (var blockchainRepository: BlockchainRepository,
        var blockRepository: BlockRepository) {
    fun getBlockchainById(id: String): Blockchain {
        val entity = blockchainRepository.findById(id)
        if (entity.isPresent) {
            val blockchain = BlockchainMapper.toDomain(entity.get())
            val blockEntities = blockRepository.findByChainId(blockchain.id)
            val blocks = BlockMapper.toDomainList(blockEntities)
            blockchain.chain = blocks
            return blockchain
        } else {
            throw NotFoundException()
        }
    }


}