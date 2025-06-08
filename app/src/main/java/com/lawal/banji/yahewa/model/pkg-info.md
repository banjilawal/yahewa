# Model Package
---
## Purpose

The `model` package contains data structures that:
1. Single Source of Truth (SSOT) for data in the application.
2. Implement data classes to represent REST API responses.
3. Sealed classes for handling JSON query succes or error responses.
4. Represents Data Transfer Object (DTO) intermediary between repository, ViewModel, and UI layers.

---
## Classes in This Package
Classes in the package are fairly simple.

```plantuml
@startuml

class Coordinate {
    + Double longitude {readOnly}
    + Doulble latitude {readOnly}
}

class City {
  + String name {readOnly}
 + String state {readOnly},
 + String country {readOnly}
 + Coordinate coordinate {readOnly}
 + Int timezone
 + String zipCode
}

City "1" *-- "0..*" Coordinate   // Composition

class GeoCode {
  + String name
  + String state
  + String zipCode
  + String country
  + Double longitude
  + Double latitude
  + Double Int timezone
  + Coordinate coordinate()
  + String getTitle()
}
@enduml
```

### Higher Level Class
- Aggregations of 

### 1. **WeatherData**
- Represents the weather information fetched from an API or local cache.
- **Example Fields**:
    - `temperature: Double`
    - `humidity: Int`
    - `weatherCondition: String`

   ```kotlin
   data class WeatherData(
       val temperature: Double,
       val humidity: Int,
       val weatherCondition: String
   )
   ```

### 2. **Location**
- Represents the user's current or selected location.
- **Example Fields**:
    - `latitude: Double`
    - `longitude: Double`
    - `cityName: String`

   ```kotlin
   data class Location(
       val latitude: Double,
       val longitude: Double,
       val cityName: String
   )
   ```

### 3. **Forecast**
- Represents hourly or daily forecast data.
- **Example Fields**:
    - `date: String`
    - `highTemperature: Double`
    - `lowTemperature: Double`
    - `description: String`

   ```kotlin
   data class Forecast(
       val date: String,
       val highTemperature: Double,
       val lowTemperature: Double,
       val description: String
   )
   ```

---
## Example Usage

The `model` classes are typically used as follows:

- **Repository**: These models act as DTOs (Data Transfer Objects) for data retrieved from external or internal sources.
- **ViewModel and LiveData**: They are shared as observable data between ViewModels and UI components.
- **Serialization**: Models are used for serializing/deserializing data when communicating with APIs.

### Example – Using `WeatherData` in a ViewModel

```kotlin
val weatherData: LiveData<WeatherData> = liveData {
    val data = repository.getWeatherForLocation(location)
    emit(data)
}
```

---

## Package Dependencies

The `model` package is self-contained. It does not depend on other packages in the application.

---

## Best Practices
1. **Immutable Data Models**  
   Data classes should be immutable whenever possible. Use `val` for fields.

2. **Separate Concerns**  
   Keep these models tightly focused on data representation. Do not include business logic or UI-related code in this package.

3. **Serialization**  
   Annotate fields for serialization/deserialization if the data is coming from an API. Example for `Gson`:
   ```kotlin
   @SerializedName("temp")
   val temperature: Double
   ```kotlin
4. **Clarity**
    Some fields in JSON response are ambigous. Field names should be descriptive.
---

## Future Improvements
- Simplify GeoCode class.
- Duplicate fields in Forecast and CurrentWeather classes.
- Add validation logic (e.g., using Kotlin contracts or custom validation annotations).
- Define specific response models for each API endpoint if the app becomes more complex.

---

This structure aims to provide clarity for future maintainers and contributors while ensuring the responsibilities of this package are clean and well-documented.
