package com.example.blockchain_project.utils

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Data
import org.springframework.stereotype.Component

@Component
class EmptyConsensusAlgorithm<T: Data> : ConsensusAlgorithm<T> {
    override fun getAlgorithm(): ConsensusAlgorithmEnum {
        return ConsensusAlgorithmEnum.NONE
    }

    override fun reachConsensus(block: Block<T>, difficulty: Long): Block<T> {
        return block
    }
}