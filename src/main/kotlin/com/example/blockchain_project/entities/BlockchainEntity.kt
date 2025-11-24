package com.example.blockchain_project.entities

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("blockchains")
data class BlockchainEntity (
    @Id val  id: String? = null,
        val difficulty: Long = 0,
        val consensusAlgorithm: ConsensusAlgorithmEnum = ConsensusAlgorithmEnum.PROOF_OF_WORK
)