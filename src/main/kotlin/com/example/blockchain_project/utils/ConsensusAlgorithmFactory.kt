package com.example.blockchain_project.utils

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import org.springframework.stereotype.Service

@Service
class ConsensusAlgorithmFactory {
    fun getConsensusAlgorithm(type: ConsensusAlgorithmEnum): ConsensusAlgorithm {
        return when (type) {
            ConsensusAlgorithmEnum.NONE -> EmptyConsensusAlgorithm
            ConsensusAlgorithmEnum.PROOF_OF_WORK -> ProofOfWork
        }
    }
}