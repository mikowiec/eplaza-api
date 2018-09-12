package com.github.mikowiec.eplaza.frontend.service

import com.github.mikowiec.eplaza.model.Address

interface AddressService {
    fun saveAddress(address: Address): Int
    fun getAddress(userid: Int): List<Address>
    fun delAddress(address: Address)
    fun editAddress(address: Address)
}
