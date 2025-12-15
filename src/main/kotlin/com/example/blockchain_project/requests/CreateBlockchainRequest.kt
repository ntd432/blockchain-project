package com.example.blockchain_project.requests

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import com.example.blockchain_project.models.Data
import io.swagger.v3.oas.annotations.Hidden

class CreateBlockchainRequest (
    val difficulty: Long = 4,
    val consensusAlgorithm: ConsensusAlgorithmEnum = ConsensusAlgorithmEnum.PROOF_OF_WORK,
    @Hidden
    var type: String? = Data::class.simpleName
)