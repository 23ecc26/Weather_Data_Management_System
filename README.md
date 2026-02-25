# Weather Forecast Backend System

A complete backend system to process, store, and expose weather forecast data for Delhi spanning nearly 20 years.

## Architecture

The system follows clean architecture principles with the following layers:

- **Controller Layer**: REST API endpoints
- **Service Layer**: Business logic and data processing
- **Repository Layer**: Database access using Spring Data JPA
- **Entity Layer**: Database entities
- **DTO Layer**: Data Transfer Objects
- **Mapper Layer**: Entity-DTO conversion
- **Utility Layer**: CSV processing
- **Config Layer**: Application configuration

## Technology Stack

- Java 17
- Spring Boot 4.0.3
- Spring Data JPA
- MySQL Database
- OpenCSV 5.9
- Lombok
- Maven

## Setup Instructions

### 1. Database Setup

Create MySQL database:

```sql
CREATE DATABASE weather_db;
```

Update database credentials in `application.properties` if needed:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Import Weather Data

**Endpoint**: `POST /weather/import`

**Parameters**: 
- `filePath`: Absolute path to CSV file

**Example**:
```bash
curl -X POST "http://localhost:8080/weather/import?filePath=C:/path/to/weather_data.csv"
```

**Response**:
```
Weather data imported successfully
```

---

### 2. Get Weather by Date

**Endpoint**: `GET /weather/date/{date}`

**Example**:
```bash
curl http://localhost:8080/weather/date/2010-05-12
```

**Response**:
```json
{
  "date": "2010-05-12",
  "weatherCondition": "Sunny",
  "temperature": 32.5,
  "humidity": 55.3,
  "pressure": 1010.5,
  "heatIndex": 35.2
}
```

---

### 3. Get Weather by Month (All Years)

**Endpoint**: `GET /weather/month/{month}`

**Example**:
```bash
curl http://localhost:8080/weather/month/05
```

**Response**:
```json
[
  {
    "date": "2010-05-12",
    "weatherCondition": "Sunny",
    "temperature": 32.5,
    "humidity": 55.3,
    "pressure": 1010.5,
    "heatIndex": 35.2
  },
  {
    "date": "2010-05-13",
    "weatherCondition": "Clear",
    "temperature": 31.8,
    "humidity": 58.7,
    "pressure": 1011.2,
    "heatIndex": 34.1
  }
]
```

---

### 4. Get Weather by Year and Month

**Endpoint**: `GET /weather/month/{year}/{month}`

**Example**:
```bash
curl http://localhost:8080/weather/month/2015/08
```

**Response**:
```json
[
  {
    "date": "2015-08-15",
    "weatherCondition": "Hot",
    "temperature": 35.2,
    "humidity": 72.5,
    "pressure": 1008.3,
    "heatIndex": 38.5
  }
]
```

---

### 5. Get Temperature Statistics by Year

**Endpoint**: `GET /weather/stats/{year}`

**Example**:
```bash
curl http://localhost:8080/weather/stats/2018
```

**Response**:
```json
[
  {
    "month": "January",
    "maxTemperature": 16.8,
    "medianTemperature": 16.0,
    "minTemperature": 15.2
  },
  {
    "month": "February",
    "maxTemperature": 22.3,
    "medianTemperature": 21.4,
    "minTemperature": 20.5
  }
]
```

## Features

### Data Processing
- ✅ CSV file parsing with OpenCSV
- ✅ Multiple date format support
- ✅ Data validation and cleansing
- ✅ Duplicate record removal
- ✅ Missing value handling
- ✅ Type conversion (String to LocalDate, Double)

### Database
- ✅ MySQL integration
- ✅ Proper indexing on date and temperature
- ✅ Efficient bulk insert
- ✅ Transaction management

### Statistics
- ✅ Maximum temperature calculation
- ✅ Minimum temperature calculation
- ✅ Median temperature calculation (correctly sorted)
- ✅ Monthly aggregation

### Architecture
- ✅ Clean layered architecture
- ✅ Separation of concerns
- ✅ DTO pattern for data transfer
- ✅ Mapper pattern for entity-DTO conversion
- ✅ Repository pattern for data access
- ✅ Service layer for business logic

## Project Structure

```
src/main/java/com/assesment/weather_forecast/
├── controller/
│   └── WeatherController.java
├── service/
│   ├── WeatherService.java
│   └── impl/
│       └── WeatherServiceImpl.java
├── repository/
│   └── WeatherRepository.java
├── entity/
│   └── WeatherData.java
├── dto/
│   ├── WeatherDTO.java
│   └── WeatherStatsDTO.java
├── mapper/
│   └── WeatherMapper.java
├── util/
│   └── CSVReaderUtil.java
├── config/
│   └── DatabaseConfig.java
└── WeatherForecastApplication.java
```

## Testing the Application

1. Start the application
2. Import sample data:

3. Test the endpoints:
```bash
# Get weather for specific date
curl http://localhost:8080/weather/date/2018-01-10

# Get all weather data for May
curl http://localhost:8080/weather/month/05

# Get weather for August 2015
curl http://localhost:8080/weather/month/2015/08

# Get temperature statistics for 2018
curl http://localhost:8080/weather/stats/2018
```


