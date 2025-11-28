package com.example.blockchain_project.controllers

import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Blockchain
import com.example.blockchain_project.models.SupplyChainData
import com.example.blockchain_project.requests.CreateBlockchainRequest
import com.example.blockchain_project.services.BlockchainService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/supply-chain")
class SupplyChainBlockchainController(private val blockchainService: BlockchainService<SupplyChainData>) {
    @GetMapping("/{id}")
    fun getBlockchain(@PathVariable id: String): Blockchain<SupplyChainData>? {
        return blockchainService.getBlockchainById(id)
    }

    @PostMapping()
    fun createBlockChain(@RequestBody createBlockchainRequest: CreateBlockchainRequest): Blockchain<SupplyChainData> {
        createBlockchainRequest.type = SupplyChainData::class.simpleName
        return blockchainService.createBlockchain(createBlockchainRequest)
    }

    @PostMapping("/{id}")
    fun addBlockToChain(@PathVariable id: String, @RequestBody data: SupplyChainData): Block<SupplyChainData>? {
        return blockchainService.addBlockToChain(id, data)
    }

    @PostMapping("/validate/{id}")
    fun validateChain(@PathVariable id: String): Boolean {
        return blockchainService.validateBlockChain(id)
    }
}