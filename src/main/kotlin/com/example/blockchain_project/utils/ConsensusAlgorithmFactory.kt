package com.example.blockchain_project.utils

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import com.example.blockchain_project.models.Data
import org.springframework.stereotype.Service

@Service
class ConsensusAlgorithmFactory<T: Data> {
    fun getConsensusAlgorithm(type: ConsensusAlgorithmEnum): ConsensusAlgorithm<T> {
        return when (type) {
            ConsensusAlgorithmEnum.NONE -> EmptyConsensusAlgorithm()
            ConsensusAlgorithmEnum.PROOF_OF_WORK -> ProofOfWork()
        }
    }
}