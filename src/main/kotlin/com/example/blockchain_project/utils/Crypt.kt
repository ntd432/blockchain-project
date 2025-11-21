package com.example.blockchain_project.utils

import java.security.MessageDigest

class Crypt {
    object Sha256 {
        fun generateHash(input: String): String? {
            return try {
                val sha = MessageDigest
                        .getInstance(
                                "SHA-256")
                var i = 0
                val hash = sha.digest(
                        input.toByteArray(charset("UTF-8")))

                // hexHash will contain
                // the Hexadecimal hash
                val hexHash = StringBuffer()
                while (i < hash.size) {
                    val hex = Integer.toHexString(
                            0xff and hash[i].toInt())
                    if (hex.length == 1) hexHash.append('0')
                    hexHash.append(hex)
                    i++
                }
                hexHash.toString()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}