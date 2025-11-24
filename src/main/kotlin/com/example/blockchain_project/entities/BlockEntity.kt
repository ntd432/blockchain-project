package com.example.blockchain_project.entities

import com.example.blockchain_project.models.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document("blocks")
data class BlockEntity (
        @Id val id: String? = null,
        val hash: String? = null,
        val previousHash: String? = null,
        @Field("chain_id")
        val chainId: String? = null,
        val nonce: Long = 0,
        val index: Long = 0,
        val data: Data? = null,
        val timeStamp: Long = 0,
)