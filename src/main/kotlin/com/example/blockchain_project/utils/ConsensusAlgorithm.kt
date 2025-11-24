package com.example.blockchain_project.utils

import com.example.blockchain_project.models.Block

interface ConsensusAlgorithm {
    fun reachConsensus(block: Block, difficulty: Long): Block
}