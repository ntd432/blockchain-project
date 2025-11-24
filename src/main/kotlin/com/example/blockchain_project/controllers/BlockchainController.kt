package com.example.blockchain_project.controllers

import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Blockchain
import com.example.blockchain_project.models.Data
import com.example.blockchain_project.services.BlockchainService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/blockchain")
class BlockchainController(private val blockchainService: BlockchainService) {
    @GetMapping("/{id}")
    fun getBlockchain(@PathVariable id: String): Blockchain? {
        return blockchainService.getBlockchainById(id)
    }

    @PostMapping()
    fun createBlockChain(): Blockchain {
        return blockchainService.createBlockchain()
    }

    @PostMapping("/{id}")
    fun addBlockToChain(@PathVariable id: String, @RequestBody data: Data): Block? {
        return blockchainService.addBlockToChain(id, data)
    }

    @PostMapping("/validate/{id}")
    fun validateChain(@PathVariable id: String): Boolean {
        return blockchainService.validateBlockChain(id)
    }
}