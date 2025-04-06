package com.lawal.banji.yahewa

data class Temperature(
    val degrees: Int
) {
    override fun toString(): String {
        return "$degrees °F"
    }
}