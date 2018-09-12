package com.github.mikowiec.eplaza.admin.service

import com.github.mikowiec.eplaza.model.Merchant

interface MerchantService {

    fun findAll(): List<Merchant>

    fun findByPage(offset: Int, pageSize: Int): List<Merchant>

    fun isAllowLogin(merchantName: String, password: String): Merchant?

    fun findById(id: Int): Merchant?

    fun save(merchant: Merchant): Int

    fun update(merchant: Merchant)

    fun delete(merchant: Merchant)

}
