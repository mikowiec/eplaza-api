package com.github.mikowiec.eplaza.utils

import java.math.BigInteger
import java.security.MessageDigest

object sign {
    fun md5(str: String): String {
        try {
            // Generate a summary of MD5 encryption calculations
            val md = MessageDigest.getInstance("MD5")
            // Calculate the md5 function
            md.update(str.toByteArray())   //getBytes())
            // digest() finally determines to return the md5 hash value,
            // returning a value of 8 as a string.
            // Because the md5 hash value is a 16-bit hex value,
            // it is actually an 8-bit character.
            // The BigInteger function converts an 8-bit string into a 16-bit hex value,
            // represented by a string; gets a hash value in the form of a string
            val md5 = BigInteger(1, md.digest()).toString(16)
            // BigInteger will omit 0 and need to be completed to 32 bits.
            return fillMD5(md5)
        } catch (e: Exception) {
            throw RuntimeException("MD5 encryption error:" + e.message, e)
        }

    }

    private fun fillMD5(md5: String): String {
        return if (md5.length === 32) md5 else fillMD5("0$md5")
    }
}
