package com.github.mikowiec.eplaza.model


import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import java.util.Date

/**
 * Product Reviews
 */
@Entity
@Table(name = "goods_comment")
class GoodsComment {

    /**
     * TODO
     * 1.Add comment
     * POST：
     * {
     * commemtContent: 'Comment information',
     * commentLevel: 4,
     * tags: "Easy to use, good, cheap",
     * username: "Gavin",
     * userid: 1,
     * goodsId: 1,
     * attrTitle: "4.7 inches - deep indigo",
     * attrId: 102,
     * orderId: 2
     * }
     *
     * Note: goodsId is a foreign key, connecting goods
     *
     * 2. Get a comment
     * method goodsService.findById() Return field when getting a single item comment
     * The specific display contents are as follows:
     *
     * comment: {
     * commentNum: 16, // Total number of comments
     * remarksNumDetail: [ 2, 2, 2, 3, 1 ] // The number of comments for 5 comment levels
     *   (fields：commentLevel)， [ Praise, satisfied, average, poor, very poor ]
     * detail: [ // Comment details
     * {
     * username: "Gavin", // username
     * commentLevel: 5, // rating
     * commemtContent: "The color is very good, the quality is not bad!", // Comments
     * attrTitle: "4.7 inches - deep indigo",
     * tags: "Easy to use, good, cheap",
     * createAt: "2018-05-15 09:20"
     * },
     * {
     * username: "Gavin", // username
     * commentLevel: 5, // rating
     * commemtContent: "The color is very good, the quality is not bad!", // Comments
     * attrTitle: "4.7 inches - deep indigo",
     * tags: "Easy to use, good, cheap",
     * createAt: "2018-05-15 09:20"
     * }
     * ]
     * }
     */

    // Unique ID
    //    @ManyToOne
    //    private Goods goods;

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "native")
    var id: Int = 0

    // comment content
    var commentContent: String? = null

    // Rating level: number of stars
    var commentLevel: Int = 0

    // Comment label Example: "Good to use, good, good and cheap"
    // The front desk writes a few labels for selection.
    var tags: String? = null
        set(tags) {
            var tags = tags
            tags = tags
        }

    // username
    var username: String? = null

    // User id
    var userid: Int = 0

    // Item Id # Foreign Key
    var goodsId: Int = 0

    // Package description
    var attrTitle: String? = null

    // Comment time
    var createAt: Date? = null

    // order number
    var orderId: String? = null
}
