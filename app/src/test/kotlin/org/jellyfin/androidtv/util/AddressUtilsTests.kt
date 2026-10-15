package org.jellyfin.androidtv.util

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AddressUtilsTests : FunSpec({
    test("standardize should capitalize street and city") {
        val result = AddressUtils.standardize(
            street = "123 main st",
            city = "san francisco",
            state = "ca",
            postalCode = "94105"
        )

        result["street"] shouldBe "123 Main St"
        result["city"] shouldBe "San Francisco"
        result["state"] shouldBe "CA"
        result["postalCode"] shouldBe "94105"
    }

    test("standardize should handle null values") {
        val result = AddressUtils.standardize(null, null, null, null)
        result.isEmpty() shouldBe true
    }

    test("standardize should filter non-digits from postalCode") {
        val result = AddressUtils.standardize(
            street = null,
            city = null,
            state = null,
            postalCode = "941-05"
        )

        result["postalCode"] shouldBe "94105"
    }

    test("standardize should uppercase state") {
        val result = AddressUtils.standardize(
            street = null,
            city = null,
            state = "california",
            postalCode = null
        )

        result["state"] shouldBe "CALIFORNIA"
    }
})
