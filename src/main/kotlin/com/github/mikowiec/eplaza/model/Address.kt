package com.github.mikowiec.eplaza.model

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

@Entity
@Table(name = "address")
class Address {
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var address_id: Int = 0
    // Name
    var name: String? = null
    // province
    var province: String? = null
    // city
    var city: String? = null
    // Area
    var area: String? = null
    // Address
    var address: String? = null
    // Mobile phone
    var phone: String? = null
    // Postal code
    var postalcode: String? = null

    var userid: Int = 0

}
