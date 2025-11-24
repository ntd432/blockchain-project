package com.example.blockchain_project.models

import com.example.blockchain_project.enums.SupplyChainStatusEnum

class SupplyChainData (
        val productId: String? = null,
        val status: SupplyChainStatusEnum = SupplyChainStatusEnum.MANUFACTURED,
        val location: String? = null,
        createdBy: String? = null,
        dataOrder: Long? = 0
) : Data(createdBy, dataOrder)