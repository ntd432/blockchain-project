package com.example.blockchain_project.entities

import com.example.blockchain_project.models.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("blocks")
data class BlockEntity<T: Data> (
        @Id val id: String? = null,
        val hash: String? = null,
        val previousHash: String? = null,
        val chainId: String? = null,
        val nonce: Long = 0,
        val index: Long = 0,
        val data: T? = null,
        val timeStamp: Long = 0,
)