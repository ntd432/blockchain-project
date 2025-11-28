package com.example.blockchain_project.models

import com.example.blockchain_project.utils.Crypt
import com.google.gson.Gson

data class Block<T: Data> (val id: String? = null,
                  val previousHash: String? = null,
                  var chainId: String? = null,
                  var nonce: Long = 0,
                  val index: Long = 0,
                  val data: T? = null,
                  val timeStamp: Long = 0){
    var hash: String? = calculateHash()

    // Function to calculate the hash
    fun calculateHash(): String? {
        // Calling the "crypt" class
        // to calculate the hash
        // by using the previous hash,
        // timestamp and the data
        return Crypt.Sha256.generateHash(
                chainId + index.toString() + previousHash
                        + timeStamp.toString()
                        + Gson().toJson(data) + nonce)
    }
}