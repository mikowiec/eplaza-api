package com.github.mikowiec.eplaza.frontend.dao

import com.github.mikowiec.eplaza.model.Address

interface AddressDao {
    fun save(address: Address): Int
    fun list(userid: Int): List<Address>
    fun delete(address: Address)
    fun update(address: Address)
}
