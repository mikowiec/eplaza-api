package com.github.mikowiec.eplaza.admin.dao


import com.github.mikowiec.eplaza.model.Merchant

interface MerchantDao {

    fun findAll(): List<Merchant>

    fun findByPage(page: Int, pageSize: Int): List<Merchant>

    fun getMerchantByMerchantName(merchantName: String): List<Merchant>

    fun findById(id: Int): Merchant?

    fun save(merchant: Merchant): Int

    fun update(merchant: Merchant)

    fun delete(merchant: Merchant)

}
