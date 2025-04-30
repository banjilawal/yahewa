package com.lawal.banji.yahewa.query

import com.lawal.banji.yahewa.model.PredictionGroup

sealed class PredictionGroupState {
    object Loading : PredictionGroupState() // Represents a loading state while forecasts are being fetched
    data class Success(val predictionGroup: PredictionGroup) : PredictionGroupState()
    data class Error(val message: String) : PredictionGroupState() // Represents an error state with a message
}
