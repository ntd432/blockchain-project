package com.example.blockchain_project.controllers

import com.example.blockchain_project.models.Block
import com.example.blockchain_project.models.Blockchain
import com.example.blockchain_project.models.SupplyChainData
import com.example.blockchain_project.requests.CreateBlockchainRequest
import com.example.blockchain_project.services.BlockchainService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/supply-chain")
@Tag(name = "Supply chain blockchain controller", description = "This blockchain contains data related to supply chain")
class SupplyChainBlockchainController(private val blockchainService: BlockchainService<SupplyChainData>) {
    @GetMapping("/{id}")
    @Operation(summary = "Get supply chain blockchain by ID")
    fun getBlockchain(@PathVariable id: String): Blockchain<SupplyChainData>? {
        return blockchainService.getBlockchainById(id)
    }

    @PostMapping()
    @Operation(summary = "Create a new supply chain blockchain")
    fun createBlockChain(@RequestBody createBlockchainRequest: CreateBlockchainRequest): Blockchain<SupplyChainData> {
        createBlockchainRequest.type = SupplyChainData::class.simpleName
        return blockchainService.createBlockchain(createBlockchainRequest)
    }

    @PostMapping("/{id}")
    @Operation(summary = "Add a new block to current supply chain blockchain")
    fun addBlockToChain(@PathVariable id: String, @RequestBody data: SupplyChainData): Block<SupplyChainData>? {
        return blockchainService.addBlockToChain(id, data)
    }

    @PostMapping("/validate/{id}")
    @Operation(summary = "Validate if a supply chain blockchain is valid")
    fun validateChain(@PathVariable id: String): Boolean {
        return blockchainService.validateBlockChain(id)
    }
}