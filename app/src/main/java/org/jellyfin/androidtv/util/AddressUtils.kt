package org.jellyfin.androidtv.util

import java.util.Locale

/**
 * Utility for standardizing address components.
 */
object AddressUtils {

    /**
     * Standardizes address components.
     * 
     * @param street The street address.
     * @param city The city.
     * @param state The state (e.g., California or CA).
     * @param postalCode The postal/zip code.
     * @return A map containing standardized components.
     */
    fun standardize(
        street: String?,
        city: String?,
        state: String?,
        postalCode: String?
    ): Map<String, String> {
        val result = mutableMapOf<String, String>()

        // Standardize Street: Trim and capitalize words
        street?.trim()?.let {
            result["street"] = it.split(" ").joinToString(" ") { word ->
                word.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString() }
            }
        }

        // Standardize City: Trim and capitalize words
        city?.trim()?.let {
            result["city"] = it.split(" ").joinToString(" ") { word ->
                word.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString() }
            }
        }

        // Standardize State: Trim and convert to upper case (assuming 2-letter codes or short names)
        state?.trim()?.uppercase(Locale.ROOT)?.let {
            result["state"] = it
        }

        // Standardize Postal Code: Trim and keep alphanumeric characters
        postalCode?.trim()?.let {
            result["postalCode"] = it.filter { char -> char.isLetterOrDigit() }
        }

        return result
    }
}
