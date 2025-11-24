package com.example.blockchain_project.utils

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import com.example.blockchain_project.models.Block
import org.springframework.stereotype.Component

@Component
object EmptyConsensusAlgorithm : ConsensusAlgorithm {
    override fun getAlgorithm(): ConsensusAlgorithmEnum {
        return ConsensusAlgorithmEnum.NONE
    }

    override fun reachConsensus(block: Block, difficulty: Long): Block {
        return block
    }
}