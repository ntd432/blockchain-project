package com.example.blockchain_project.services

import com.example.blockchain_project.models.Data
import com.example.blockchain_project.mappers.BlockMapper
import com.example.blockchain_project.mappers.BlockchainMapper
import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Blockchain
import com.example.blockchain_project.repositories.BlockRepository
import com.example.blockchain_project.repositories.BlockchainRepository
import com.example.blockchain_project.requests.CreateBlockchainRequest
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class BlockchainService<T: Data> (var blockchainRepository: BlockchainRepository,
        var blockRepository: BlockRepository<T>) {
    val blockchainMapper = BlockchainMapper<T>()
    val blockMapper = BlockMapper<T>()

    fun getBlockchainById(id: String): Blockchain<T> {
        val entity = blockchainRepository.findById(id)
        if (entity.isPresent) {
            val blockchain = blockchainMapper.toDomain(entity.get())
            val blockEntities = blockRepository.findByChainId(blockchain.id)
            val blocks = blockMapper.toDomainList(blockEntities)
            blockchain.chain = blocks as ArrayList<Block<T>>
            return blockchain
        } else {
            throw NotFoundException()
        }
    }

    fun createBlockchain(createBlockchainRequest: CreateBlockchainRequest): Blockchain<T> {
        val blockchain = Blockchain<T>(difficulty = createBlockchainRequest.difficulty,
                consensusAlgorithm = createBlockchainRequest.consensusAlgorithm,
            type = createBlockchainRequest.type)
        val savedBlockchainEntity = blockchainRepository.save(blockchainMapper.toEntity(blockchain))
        val genesisBlock = generateGenesisBlock(savedBlockchainEntity.id)
        val savedGenesisBlock = blockRepository.save(blockMapper.toEntity(genesisBlock))
        blockchain.id = savedBlockchainEntity.id
        blockchain.chain = arrayListOf(blockMapper.toDomain(savedGenesisBlock))
        return blockchain
    }

    fun addBlockToChain(chainId: String, data: T): Block<T>? {
        val blockchainEntity = blockchainRepository.findById(chainId)
        if (blockchainEntity.isEmpty) {
            throw NotFoundException()
        }
        val blockchain = blockchainMapper.toDomain(blockchainEntity.get())
        val blockEntities = blockRepository.findByChainId(blockchain.id)
        val blocks = blockMapper.toDomainList(blockEntities)
        blockchain.chain = blocks as ArrayList<Block<T>>
        val minedBlock = blockchain.addBlock(data)
        val addedBlock = blockRepository.save(blockMapper.toEntity(minedBlock))
        return blockMapper.toDomain(addedBlock)
    }

    fun validateBlockChain(chainId: String): Boolean {
        val blockchainEntity = blockchainRepository.findById(chainId)
        if (blockchainEntity.isEmpty) {
            throw NotFoundException()
        }
        val blockchain = blockchainMapper.toDomain(blockchainEntity.get())
        val blockEntities = blockRepository.findByChainId(blockchain.id)
        val blocks = blockMapper.toDomainList(blockEntities)
        blockchain.chain = blocks as ArrayList<Block<T>>
        return blockchain.isValid()
    }

    private fun generateGenesisBlock(chainId: String?): Block<T> {
        return Block(timeStamp = System.currentTimeMillis(), chainId = chainId)
    }

}