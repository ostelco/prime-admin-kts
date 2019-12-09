package org.ostelco.tools.prime.admin

import arrow.core.flatMap
import org.ostelco.prime.dsl.withId
import org.ostelco.prime.dsl.writeTransaction
import org.ostelco.prime.getLogger
import org.ostelco.prime.model.PaymentProperties.LABEL
import org.ostelco.prime.model.PaymentProperties.TAX_REGION_ID
import org.ostelco.prime.model.Price
import org.ostelco.prime.model.Product
import org.ostelco.prime.model.ProductClass.SIMPLE_DATA
import org.ostelco.prime.model.ProductProperties.NO_OF_BYTES
import org.ostelco.prime.model.ProductProperties.PRODUCT_CLASS
import org.ostelco.prime.storage.graph.adminStore
import org.ostelco.prime.storage.graph.model.Offer
import org.ostelco.prime.storage.graph.model.Segment
import org.ostelco.prime.model.Offer as ModelOffer

private val logger by getLogger()

/**
 * Done on 2019.12.10
 */

adminStore.atomicCreateOffer(
        offer = ModelOffer(
                id = "currency-sgd",
                segments = listOf("country-sg")
        ),
        products = listOf(
                Product(sku = "1GB_4SGD",
                        price = Price(400, "SGD"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "1_073_741_824"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "S$4",
                                "productLabel" to "1GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "374",
                                "taxLabel" to "GST",
                                "tax" to "26",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "1GB",
                                TAX_REGION_ID.s to "sg"
                        )
                ),
                Product(sku = "5GB_15SGD",
                        price = Price(1500, "SGD"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "5_368_709_120"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "S$15",
                                "productLabel" to "5GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "1402",
                                "taxLabel" to "GST",
                                "tax" to "98",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "5GB",
                                TAX_REGION_ID.s to "sg"
                        )
                )
        )
).flatMap {
    writeTransaction {
        unlink { (Offer withId "discount-sgd") isOfferedTo (Segment withId "country-sg") }
    }
}.mapLeft {
    logger.error(it.message)
}

