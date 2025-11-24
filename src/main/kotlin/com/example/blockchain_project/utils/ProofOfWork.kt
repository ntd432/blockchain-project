package com.example.blockchain_project.utils

import com.example.blockchain_project.models.Block

object ProofOfWork : ConsensusAlgorithm {
    override fun reachConsensus(block: Block, difficulty: Long): Block {
        val target = "0".repeat(difficulty.toInt())
        while (block.hash?.startsWith(target) == false) {
            block.nonce = block.nonce + 1
            block.hash = block.calculateHash()
        }
        return block
    }
}