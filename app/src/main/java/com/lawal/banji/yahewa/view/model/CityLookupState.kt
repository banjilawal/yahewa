package com.lawal.banji.yahewa.view.model

import com.lawal.banji.yahewa.model.City

sealed class CityLookupState {
    object Loading : CityLookupState()
    data class Success(val city: City) : CityLookupState()
    data class Error(val message: String) : CityLookupState()
}
