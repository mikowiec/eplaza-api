package com.github.mikowiec.eplaza.model

import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import java.util.HashSet

@Entity
@Table(name = "order_list")
class Order {
    // Unique ID
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var orderid: Int = 0

    // Name
    var name: String? = null

    // address
    var address: String? = null

    // Address ID
    @Column(name = "address_id")
    var addressId: Int = 0

    // price
    var price: Double = 0.toDouble()

    // User id
    var userid: Int = 0

    // Order Status
    @ColumnDefault(value = "0")
    var status: Int = 0

    // order details
    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var orderDetail: Set<OrderDetail> = HashSet()
}
