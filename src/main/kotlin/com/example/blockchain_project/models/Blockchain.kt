package com.example.blockchain_project.models

import com.example.blockchain_project.utils.ConsensusAlgorithm

data class Blockchain (val id: String? = null,
                        val difficulty: Long = 4,
                        var chain: ArrayList<Block?>? = null) {
    private fun getLastBlock(): Block? {
        if (chain.isNullOrEmpty()) {
            return null
        }
        return chain!![chain!!.size - 1]
    }

    fun addBlock(data: Data, algorithm: ConsensusAlgorithm): Block {
        val lastBlock = getLastBlock() ?: throw IllegalStateException()
        val newBlock = Block(previousHash = lastBlock.hash,
                chainId = id, index = lastBlock.index + 1, data = data, timeStamp = System.currentTimeMillis())
        if (!this.id.isNullOrEmpty()) {
            newBlock.chainId = this.id
        } else {
            throw IllegalStateException()
        }
        val minedBlock = algorithm.reachConsensus(newBlock, this.difficulty)
        this.chain?.add(minedBlock)
        return minedBlock
    }

    fun isValid(): Boolean {
        if (chain.isNullOrEmpty()) {
            return false
        }
        val n = chain!!.size
        var i = 1
        while (i != n) {
            val current = chain!![i]
            val previous = chain!![i]
            if (previous == null || current == null) {
                return false
            }
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