package com.example.blockchain_project.utils

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Data
import org.springframework.stereotype.Component

@Component
class ProofOfWork<T: Data> : ConsensusAlgorithm<T> {
    override fun getAlgorithm(): ConsensusAlgorithmEnum {
        return ConsensusAlgorithmEnum.PROOF_OF_WORK
    }

    override fun reachConsensus(block: Block<T>, difficulty: Long): Block<T> {
        val target = "0".repeat(difficulty.toInt())
        while (block.hash?.startsWith(target) == false) {
            block.nonce = block.nonce + 1
            block.hash = block.calculateHash()
        }
        return block
    }
}