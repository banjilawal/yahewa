package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.model.WeatherPrediction
import com.lawal.banji.yahewa.ui.theme.BattleShipGrayBlue
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.Peach
import com.lawal.banji.yahewa.ui.theme.PowderBlue




//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun PredictionGroupComposable(predictionGroup: PredictionGroup) {
//
//    val cityDetails = "$predictionGroup.city.name, ${predictionGroup.city.country}"
//    val coordinates = "lat:  ${predictionGroup.city.coordinates.latitude}, " +
//            "lon: ${predictionGroup.city.coordinates.longitude}"
//
//    LazyColumn() {
//        item { Box(modifier = Modifier.fillMaxWidth().background(SandLightest)) { Text(text = cityDetails) } }
////        item { Box(modifier = Modifier.fillMaxWidth().background(Lavender)) { Text(text = coordinates) } }
////        item { PredictionListComposable(predictions = predictionGroup.predictions) }
//    }
//}