package com.example.blockchain_project.utils

import com.example.blockchain_project.enums.ConsensusAlgorithmEnum
import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Data

interface ConsensusAlgorithm<T: Data> {
    fun getAlgorithm(): ConsensusAlgorithmEnum
    fun reachConsensus(block: Block<T>, difficulty: Long): Block<T>
}