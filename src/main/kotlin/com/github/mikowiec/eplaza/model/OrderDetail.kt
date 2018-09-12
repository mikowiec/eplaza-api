package com.github.mikowiec.eplaza.model

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*

@Entity
@Table(name = "order_detail")
class OrderDetail {
    // Unique ID
    //    @ManyToOne
    //    private Order order;

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // Package ID
    @Column(name = "attr_id")
    var attrId: Int = 0

    // Package name
    @Column(name = "attr_title")
    var attrTitle: String? = null

    // product name
    @Column(name = "goods_name")
    var goodsName: String? = null

    // product picture
    var img: String? = null

    // Package code
    @Column(name = "goods_code")
    var goodsCode: String? = null

    // Merchant ID
    @Column(name = "merchant_id")
    var merchantId: String? = null

    // Purchase quantity
    var count: Int = 0

    //    public Order getOrder() {
    //        return order;
    //    }
    //
    //    public void setOrder(Order order) {
    //        this.order = order;
    //    }
}
