package org.ostelco.tools.prime.admin

import org.ostelco.prime.dsl.DSL.job
import org.ostelco.prime.getLogger
import org.ostelco.prime.model.Offer
import org.ostelco.prime.model.PaymentProperties.LABEL
import org.ostelco.prime.model.PaymentProperties.TAX_REGION_ID
import org.ostelco.prime.model.PaymentProperties.TYPE
import org.ostelco.prime.model.PaymentType.SUBSCRIPTION
import org.ostelco.prime.model.Plan
import org.ostelco.prime.model.Price
import org.ostelco.prime.model.Product
import org.ostelco.prime.model.ProductClass.MEMBERSHIP
import org.ostelco.prime.model.ProductClass.SIMPLE_DATA
import org.ostelco.prime.model.ProductProperties.NO_OF_BYTES
import org.ostelco.prime.model.ProductProperties.PRODUCT_CLASS
import org.ostelco.prime.model.ProductProperties.SEGMENT_IDS
import org.ostelco.prime.model.Region
import org.ostelco.prime.model.Segment
import org.ostelco.prime.storage.graph.adminStore

private val logger by getLogger()

// for Norway

job {
    create { Region(id = "no", name = "Norway") }
}.mapLeft {
    logger.error(it.message)
}

adminStore.atomicCreateOffer(
        offer = Offer(id = "default_offer-no"),
        segments = listOf(Segment(id = "country-no")),
        products = listOf(
                Product(sku = "1GB_0NOK",
                        price = Price(0, ""),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "1_073_741_824"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "Free",
                                "productLabel" to "1GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "0",
                                "taxLabel" to "MVA",
                                "tax" to "0",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "1GB",
                                TAX_REGION_ID.s to "no"
                        )
                ),
                Product(sku = "1GB_249NOK",
                        price = Price(24900, "NOK"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "1_073_741_824"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "249 kr",
                                "productLabel" to "1GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "19920",
                                "taxLabel" to "MVA",
                                "tax" to "4980",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "1GB",
                                TAX_REGION_ID.s to "no"
                        )
                ),
                Product(sku = "2GB_299NOK",
                        price = Price(29900, "NOK"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "2_147_483_648"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "299 kr",
                                "productLabel" to "2GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "23920",
                                "taxLabel" to "MVA",
                                "tax" to "5980",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "2GB",
                                TAX_REGION_ID.s to "no"
                        )
                ),
                Product(sku = "3GB_349NOK",
                        price = Price(34900, "NOK"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "3_221_225_472"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "349 kr",
                                "productLabel" to "3GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "27920",
                                "taxLabel" to "MVA",
                                "tax" to "6980",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "3GB",
                                TAX_REGION_ID.s to "no"
                        )
                ),
                Product(sku = "5GB_399NOK",
                        price = Price(39900, "NOK"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "5_368_709_120"),
                        presentation = mapOf(
                                "priceLabel" to "399 kr",
                                "productLabel" to "5GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "31920",
                                "taxLabel" to "MVA",
                                "tax" to "7980",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "5GB",
                                TAX_REGION_ID.s to "no"
                        )
                )
        )
).mapLeft {
    logger.error(it.message)
}

// for Singapore
job {
    create { Region(id = "sg", name = "Singapore") }
}

adminStore.createPlan(
        plan = Plan(
                id = "PLAN_1SGD_YEAR",
                interval = "year"),
        stripeProductName = "Annual subscription plan",
        planProduct = Product(
                sku = "PLAN_1SGD_YEAR",
                price = Price(amount = 100, currency = "SGD"),
                properties = mapOf(
                        PRODUCT_CLASS.s to MEMBERSHIP.name,
                        SEGMENT_IDS.s to "country-sg"),
                presentation = mapOf(
                        "priceLabel" to "$1",
                        "productLabel" to "Annual subscription plan",
                        "payeeLabel" to "Red Otter",
                        "subTotal" to "93",
                        "taxLabel" to "GST",
                        "tax" to "7",
                        "subTotalLabel" to "Sub Total"
                ),
                payment = mapOf(
                        TYPE.s to SUBSCRIPTION.name,
                        LABEL.s to "Annual subscription plan",
                        TAX_REGION_ID.s to "sg"
                )
        )
).mapLeft {
    logger.error(it.message)
}

adminStore.atomicCreateOffer(
        offer = Offer(
                id = "plan-offer-sg",
                products = listOf("PLAN_1SGD_YEAR")
        ),
        segments = listOf(Segment(id = "plan-country-sg"))
).mapLeft {
    logger.error(it.message)
}

adminStore.atomicCreateOffer(
        offer = Offer(id = "default_offer-sg"),
        segments = listOf(Segment(id = "country-sg")),
        products = listOf(
                Product(sku = "1GB_5SGD",
                        price = Price(500, "SGD"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "1_073_741_824"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "$5",
                                "productLabel" to "1GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "467",
                                "taxLabel" to "GST",
                                "tax" to "33",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "1GB",
                                TAX_REGION_ID.s to "sg"
                        )
                )
        )
).mapLeft {
    logger.error(it.message)
}

// for US
job {
    create { Region(id = "us", name = "United States") }
}.mapLeft {
    logger.error(it.message)
}

adminStore.createPlan(
        plan = Plan(
                id = "PLAN_1USD_YEAR",
                interval = "year"),
        stripeProductName = "Annual subscription plan",
        planProduct = Product(
                sku = "PLAN_1USD_YEAR",
                price = Price(amount = 100, currency = "USD"),
                properties = mapOf(
                        PRODUCT_CLASS.s to MEMBERSHIP.name,
                        SEGMENT_IDS.s to "country-us"),
                presentation = mapOf(
                        "priceLabel" to "$1",
                        "productLabel" to "Annual subscription plan",
                        "payeeLabel" to "Red Otter",
                        "subTotal" to "80",
                        "taxLabel" to "GST",
                        "tax" to "20",
                        "subTotalLabel" to "Sub Total"),
                payment = mapOf(
                        TYPE.s to SUBSCRIPTION.name,
                        LABEL.s to "Annual subscription plan",
                        TAX_REGION_ID.s to "us"
                )
        )
).mapLeft {
    logger.error(it.message)
}

adminStore.atomicCreateOffer(
        offer = Offer(
                id = "plan-offer-us",
                products = listOf("PLAN_1USD_YEAR")
        ),
        segments = listOf(Segment(id = "plan-country-us"))
).mapLeft {
    logger.error(it.message)
}

adminStore.atomicCreateOffer(
        offer = Offer(id = "default_offer-us"),
        segments = listOf(Segment(id = "country-us")),
        products = listOf(
                Product(sku = "1GB_5USD",
                        price = Price(500, "USD"),
                        properties = mapOf(
                                PRODUCT_CLASS.s to SIMPLE_DATA.name,
                                NO_OF_BYTES.s to "1_073_741_824"
                        ),
                        presentation = mapOf(
                                "priceLabel" to "$5",
                                "productLabel" to "1GB",
                                "payeeLabel" to "Red Otter",
                                "subTotal" to "401",
                                "taxLabel" to "GST",
                                "tax" to "99",
                                "subTotalLabel" to "Sub Total"
                        ),
                        payment = mapOf(
                                LABEL.s to "1GB",
                                TAX_REGION_ID.s to "us"
                        )
                )
        )
).mapLeft {
    logger.error(it.message)
}

// generic

job {
    create {
        Product(sku = "2GB_FREE_ON_JOINING",
                price = Price(0, ""),
                properties = mapOf(
                        PRODUCT_CLASS.s to SIMPLE_DATA.name,
                        NO_OF_BYTES.s to "2_147_483_648"
                ),
                presentation = mapOf(
                        "priceLabel" to "Free",
                        "productLabel" to "2GB Welcome Pack"
                )
        )
    }

    create {
        Product(sku = "1GB_FREE_ON_JOINING",
                price = Price(0, ""),
                properties = mapOf(
                        PRODUCT_CLASS.s to SIMPLE_DATA.name,
                        NO_OF_BYTES.s to "1_073_741_824"
                ),
                presentation = mapOf(
                        "priceLabel" to "Free",
                        "productLabel" to "1GB Welcome Pack"
                )
        )
    }

    create {
        Product(sku = "1GB_FREE_ON_REFERRED",
                price = Price(0, ""),
                properties = mapOf(
                        PRODUCT_CLASS.s to SIMPLE_DATA.name,
                        NO_OF_BYTES.s to "1_073_741_824"
                ),
                presentation = mapOf(
                        "priceLabel" to "Free",
                        "productLabel" to "1GB Referral Pack"
                )
        )
    }
}.mapLeft {
    logger.error(it.message)
}