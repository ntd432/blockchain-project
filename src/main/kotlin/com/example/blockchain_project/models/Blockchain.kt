package com.example.blockchain_project.models

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import com.example.blockchain_project.utils.ConsensusAlgorithmFactory

data class Blockchain<T: Data> (var id: String? = null,
                                val difficulty: Long = 4,
                                var chain: ArrayList<Block<T>> = arrayListOf(),
                                val consensusAlgorithm: ConsensusAlgorithmEnum = ConsensusAlgorithmEnum.PROOF_OF_WORK,
    val type: String? = null) {

    private fun getLastBlock(): Block<T>? {
        if (chain.isEmpty()) {
            return null
        }
        return chain[chain.size - 1]
    }

    fun addBlock(data: T): Block<T> {
        val lastBlock = getLastBlock() ?: throw IllegalStateException()
        val newBlock = Block(previousHash = lastBlock.hash,
                chainId = id, index = lastBlock.index + 1, data = data, timeStamp = System.currentTimeMillis())
        if (!this.id.isNullOrEmpty()) {
            newBlock.chainId = this.id
        } else {
            throw IllegalStateException()
        }
        val algorithm = ConsensusAlgorithmFactory<T>().getConsensusAlgorithm(this.consensusAlgorithm)
        val minedBlock = algorithm.reachConsensus(newBlock, this.difficulty)
        this.chain.add(minedBlock)
        return minedBlock
    }

    fun isValid(): Boolean {
        if (chain.isEmpty()) {
            return false
        }
        val n = chain.size
        var i = 1
        while (i != n) {
            val current = chain[i]
            val previous = chain[i - 1]
            if (current.hash != current.calculateHash()) {
                return false
            }
            if (current.previousHash != previous.calculateHash()) {
                return false
            }
            i++
        }
        return true
    }
}