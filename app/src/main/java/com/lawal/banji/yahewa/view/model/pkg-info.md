# ViewModel Package
---
## Purpose

1. Contains ViewModels  that set state through UI controls.
2. Set from MainActivity and Factories.
3. Maintains relationship between: CurrentWeather<-->GeoCode<-->Forecast

## Class Relationships in view.model package.
```plantuml
@startuml
package view.model {
    class AppViewModel {
        - currentCityName: String?
        - currentZipCode: String?
        - currentCoordinate: Coordinate?
        - cachedCurrentWeather: CurrentWeather?
        - cachedForecast: Forecast? = null
        - <i>expirationTimer</i>: ExpirationTimer
        + _geoCodeState: MutableStateFlow<GeoCodeState?>
        + _currentWeatherState: MutableStateFlow<CurrentWeatherState?>
        + _forecastState: MutableStateFlow<ForecastState?>
        + <i>geoCodeState</i>: StateFlow<GeoCodeState?>
        + <i>currentWeatherState</i>: StateFlow<CurrentWeatherState?>
        + <i>forecastState</i>: StateFlow<ForecastState?>
         + loadDataByCoordinate(coordinate: Coordinate)
         + loadDataByZipCode(zipCode: String)
         + loadDataByCityName(cityName: String)
         - printInfo()
          - isCacheValid(): Boolean 
         - resetCache(): void
         - stateGeoCodeLookup(): void
         - coordinateFromCityName(cityName: String): Coordinate
        - updateGeoCodeFromWeather(currentWeather: CurrentWeather) 
        - forecastQueryResponseHandler(queryResponseState: QueryResponseState<Forecast>)
        - currentWeatherQueryResponseHandler(queryResponseState: QueryResponseState<CurrentWeather>)
        - forecastQueryResponseHandler(queryResponseState: QueryResponseState<Forecast>) 
    }
}
@enduml
```