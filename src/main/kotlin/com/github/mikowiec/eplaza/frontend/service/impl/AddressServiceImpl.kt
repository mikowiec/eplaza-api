package com.github.mikowiec.eplaza.frontend.service.impl

import com.github.mikowiec.eplaza.frontend.dao.AddressDao
import com.github.mikowiec.eplaza.frontend.service.AddressService
import com.github.mikowiec.eplaza.model.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AddressServiceImpl : AddressService {

    @Autowired
    private val addressDao: AddressDao? = null

    override fun saveAddress(address: Address): Int {
        return addressDao!!.save(address)
    }

    override fun getAddress(userid: Int): List<Address> {
        return addressDao!!.list(userid)
    }

    override fun delAddress(address: Address) {
        addressDao!!.delete(address)
    }

    override fun editAddress(address: Address) {
        addressDao!!.update(address)
    }


}
