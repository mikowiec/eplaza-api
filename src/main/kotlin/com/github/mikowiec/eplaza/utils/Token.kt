package com.github.mikowiec.eplaza.utils

import com.alibaba.fastjson.JSON
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT

import java.util.Date
import java.util.HashMap

object Token {
    private val SECRET = "ePlazavServersa!@#$#"
    private val EXP = "exp"
    private val PAYLOAD = "payload"
    /**
     * Generation Token: jwt
     * @param object Incoming encrypted object - put in PAYLOAD
     * @param maxAge Expired event, in seconds
     * @param <T>
     * @return
    </T> */
    fun <T> createToken(`object`: T, maxAge: Long): String? {
        val now = Date()
        //val map = HashMap<String, Object>()
        val map: HashMap<String, Any> = HashMap()
        val jsonString = JSON.toJSONString(`object`)
        map.put("alg", "HS256")
        map.put("typ", "JWT")
        val exp = now.getTime() + maxAge * 1000
        var token: String? = null
        try {
            token = JWT.create()
                    .withHeader(map)//header
                    .withClaim(PAYLOAD, jsonString)//Stored content json
                    .withExpiresAt(Date(exp))
                    .sign(Algorithm.HMAC256(SECRET))//Key
        } catch (e: JWTCreationException) {
            System.out.println(e)
        }

        return token
    }

    /**
     * Decrypt token
     * @param token jwt type token
     * @param classT Type when encrypting
     * @param <T>
     * @return Returns the decrypted object -
     *    if the token expires, returns an empty object
    </T> */
    fun <T> validToken(token: String, classT: Class<T>?): T? {
        var decode: DecodedJWT? = null
        try {
            decode = JWT.decode(token)
            val claims = decode!!.getClaims()
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                val tokenTime = claims.get(EXP)!!.asDate().getTime()
                val nowTime = Date().getTime()
                // Determine if the token has timed out
                if (tokenTime > nowTime) {
                    val json: String = claims.get(PAYLOAD)!!.asString()
                    return if (classT != null) {
                        JSON.parseObject(json, classT)
                    } else {
                        JSON.parse(json) as T
                    }
                }
            }
        } catch (e: Exception) {
            System.out.println(e)
            return null
        }

        return null
    }
}
