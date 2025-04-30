package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.lawal.banji.yahewa.model.WeatherPrediction

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PredictionListView(predictions: List<WeatherPrediction>) {
    LazyColumn {
        items(predictions.size) { index ->
            val prediction = predictions[index]
            PredictionView(prediction)
        }
    }
}
