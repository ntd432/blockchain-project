package com.example.blockchain_project.requests

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum

class CreateBlockchainRequest (
    val difficulty: Long = 4,
    val consensusAlgorithm: ConsensusAlgorithmEnum = ConsensusAlgorithmEnum.PROOF_OF_WORK
)