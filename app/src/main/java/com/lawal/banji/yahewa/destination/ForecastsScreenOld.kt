package com.lawal.banji.yahewa.destination


//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun ForecastComposable(forecast: ForecastRecord, modifier: Modifier = Modifier) {
//
//    val precipitationProbability = "${forecast.precipitationProbability} % chance of rain"
//    val solarTransitionTimes = "sunrise: ${forecast.sunrise} sunset: ${forecast.sunset}"
//    val humidity = "${forecast.humidity} % humidity"
//    val temperature = "high ${forecast.temperature.max} / low ${forecast.temperature.min}"
//    val weatherIconId = forecast.weather[0].iconId
//    val weatherDescription = forecast.weather[0].description
//
//    // Use a Column instead of LazyColumn as this is static data
////    Column(
////        modifier = modifier
////            .padding(DefaultPadding)
////            .fillMaxWidth()
////            .background(BattleShipGrayBlue)
////            .clip(RoundedCornerShape(DefaultCornerRadius))
////    ) {
////        Text(
////            text = temperature,
////            style = MaterialTheme.typography.labelSmall,
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(vertical = 8.dp)
////                .background(PowderBlue)
////                .clip(RoundedCornerShape(DefaultCornerRadius)),
////            textAlign = TextAlign.Center
////        )
////        WeatherIcon(
////            iconId = weatherIconId,
////            iconSize = 60.dp// Adjust size appropriately
////        )
////
////        Text(
////            text = solarTransitionTimes,
////            style = MaterialTheme.typography.labelSmall,
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(vertical = 8.dp)
////                .background(Peach)
////                .clip(RoundedCornerShape(DefaultCornerRadius)),
////            textAlign = TextAlign.Center
////        )
////
////        Text(
////            text = precipitationProbability,
////            style = MaterialTheme.typography.labelSmall,
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(vertical = 8.dp)
////                .background(PowderBlue)
////                .clip(RoundedCornerShape(DefaultCornerRadius)),
////            textAlign = TextAlign.Center
////        )
////
////        Text(
////            text = humidity,
////            style = MaterialTheme.typography.labelSmall,
////            modifier = Modifier
////                .fillMaxWidth()
////                .padding(vertical = 8.dp)
////                .background(Peach)
////                .clip(RoundedCornerShape(DefaultCornerRadius)),
////            textAlign = TextAlign.Center
////        )
////    }
////}
////
////@RequiresApi(Build.VERSION_CODES.O)
////@Composable
////fun ForecastListComposable(forecastRecords: List<ForecastRecord>, modifier: Modifier = Modifier) {
////    LazyColumn(
////        modifier = modifier
////            .fillMaxWidth()
////            .background(DefaultDisplayBackgroundColor) // Optional background for the list
////    ) {
////        items(forecastRecords.size) { index ->
////            ForecastComposable(
////                forecast = forecastRecords[index],
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .padding(vertical = 8.dp) // Add space between items
////            )
////        }
////    }
////}
////
////@RequiresApi(Build.VERSION_CODES.O)
////@Composable
////fun ForecastGroupScreen(
////    forecastGroupState: ForecastGroupState,
////    onNavigate: (NavigationEvent) -> Unit = {},
////    onZipcodeEntered: (String) -> Unit
//) {
////    Text(text = "ForecastGroupScreen")
//
//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .statusBarsPadding(),
//        topBar = { ZipcodeInput(onZipcodeEntered = onZipcodeEntered) },
//        bottomBar = {},
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(DefaultDisplayBackgroundColor)
//        ) {
//            when (forecastGroupState) {
//                is ForecastGroupState.Loading -> {
//                    // Show loading message
//                    Text(
//                        text = "Loading forecast predictions...",
//                        modifier = Modifier.padding(16.dp)
//                    )
//                }
//
//                is ForecastGroupState.Error -> {
//                    // Show error message
//                    val errorMessage = "WeatherPrediction error: ${(forecastGroupState as ForecastGroupState.Error).message}"
//                    Text(
//                        text = errorMessage,
//                        modifier = Modifier.padding(16.dp),
//                        color = MaterialTheme.colorScheme.error
//                    )
//                }
//
//                is ForecastGroupState.Success -> {
//                    // Ensure the list is constrained properly
//                    ForecastListComposable(
//                        forecastRecords = forecastGroupState.forecast.forecastRecords,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f) // Ensures the LazyColumn gets constrained height
//                    )
//                }
//            }
//        }
//    }
//}