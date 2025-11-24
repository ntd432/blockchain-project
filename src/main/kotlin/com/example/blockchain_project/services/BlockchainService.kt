package com.example.blockchain_project.services

import com.example.blockchain_project.models.Data
import com.example.blockchain_project.mappers.BlockMapper
import com.example.blockchain_project.mappers.BlockchainMapper
import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Blockchain
import com.example.blockchain_project.repositories.BlockRepository
import com.example.blockchain_project.repositories.BlockchainRepository
import com.example.blockchain_project.requests.CreateBlockchainRequest
import com.example.blockchain_project.utils.ProofOfWork
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
            blockchain.chain = ArrayList(blocks)
            return blockchain
        } else {
            throw NotFoundException()
        }
    }

    fun createBlockchain(createBlockchainRequest: CreateBlockchainRequest): Blockchain {
        val blockchain = Blockchain(difficulty = createBlockchainRequest.difficulty,
                consensusAlgorithm = createBlockchainRequest.consensusAlgorithm)
        val savedBlockchainEntity = blockchainRepository.save(BlockchainMapper.toEntity(blockchain))
        val genesisBlock = generateGenesisBlock()
        genesisBlock.chainId = savedBlockchainEntity.id
        val savedGenesisBlock = blockRepository.save(BlockMapper.toEntity(genesisBlock))
        blockchain.id = savedBlockchainEntity.id
        blockchain.chain = arrayListOf(BlockMapper.toDomain(savedGenesisBlock))
        return blockchain
    }

    fun addBlockToChain(chainId: String, data: Data): Block? {
        val blockchainEntity = blockchainRepository.findById(chainId)
        if (blockchainEntity.isEmpty) {
            throw NotFoundException()
        }
        val blockchain = BlockchainMapper.toDomain(blockchainEntity.get())
        val blockEntities = blockRepository.findByChainId(blockchain.id)
        val blocks = BlockMapper.toDomainList(blockEntities)
        blockchain.chain = ArrayList(blocks)
        val minedBlock = blockchain.addBlock(data)
        val addedBlock = blockRepository.save(BlockMapper.toEntity(minedBlock))
        return BlockMapper.toDomain(addedBlock)
    }

    fun validateBlockChain(chainId: String): Boolean {
        val blockchainEntity = blockchainRepository.findById(chainId)
        if (blockchainEntity.isEmpty) {
            throw NotFoundException()
        }
        val blockchain = BlockchainMapper.toDomain(blockchainEntity.get())
        val blockEntities = blockRepository.findByChainId(blockchain.id)
        val blocks = BlockMapper.toDomainList(blockEntities)
        blockchain.chain = ArrayList(blocks)
        return blockchain.isValid()
    }

    private fun generateGenesisBlock(): Block {
        return Block(timeStamp = System.currentTimeMillis())
    }

}