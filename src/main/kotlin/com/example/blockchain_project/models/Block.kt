package com.example.blockchain_project.models

import com.example.blockchain_project.entities.DataEntity
import com.example.blockchain_project.utils.Crypt

data class Block (val id: String? = null,
                  val hash: String? = null,
                  val previousHash: String? = null,
                  val chainId: String? = null,
                  val nonce: String? = null,
                  val index: Long = 0,
                  val data: DataEntity? = null,
                  val timeStamp: Long = 0){

    // Function to calculate the hash
    private fun calculateHash(): String? {
        // Calling the "crypt" class
        // to calculate the hash
        // by using the previous hash,
        // timestamp and the data
        return Crypt.Sha256.generateHash(
                chainId + index.toString() + previousHash
                        + timeStamp.toString()
                        + data + nonce)
    }
}