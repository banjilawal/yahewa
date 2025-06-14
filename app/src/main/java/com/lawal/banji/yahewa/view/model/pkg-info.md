#  Package `com.lawal.banji.yahewa.viewModel`
---
## Purpose

1. Contains ViewModels  that set state through UI controls.
2. Set from MainActivity and Factories.
3. Maintains relationship between: CurrentWeather<-->GeoCode<-->Forecast

## Dependencies
The `viewModel` package depends on

- **Other Packages**:
    -  `com.lawal.banji.yahewa.repo`
    - `com.lawal.banji.yahewa.model`
    -  `com.lawal.banji.yahema.factory`
---
## Main Classes in ViewModelPackage

### AppViewModel
If `GeoCode`, `CurrentWeather`, or `Forecast` have inconsistencies in their
`coordinate` we cannot trust the accuracy or reliability of the location, nor
the weather reports.  `AppViewModel` maintains the relationships between
`GeoCode`, `CurrentWeather`, and `Forecast`.

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
        + AppViewModel(repo: AppRepo)
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
### Class Dependencies
`AppViewModel` depends on:
- **Data Classes**
  - `GeoCode`
  - `CurrentWeather`
  - `Forecast`
  - `Coordinate`
  
- **Builder Classes**
  - `AppViewModelFactory`
  
- **Repository Classes**
  - `AppRepository`
```plantuml
@startuml
    title AppViewModel Class Dependencies
    class AppViewModel {
        + AppViewModel(repo: AppRepository)
        + getCurrentWeather(): CurrentWeather
        + getForecast(): Forecast
        + getGeoCode(): GeoCode
    }
    
    'Related Classes'
    class GeoCode
    class CurrentWeather
    class Forecast
    class AppRepository
    
    'Relationships
    AppViewModel -> AppRepository: uses
    AppViewModel -> GeoCode: queries
    AppViewMode -> CurrentWeather: gets
    AppViewModel -> Forecast: gets
    AppViewFactory -> AppViewModel: creates
@enduml
```
