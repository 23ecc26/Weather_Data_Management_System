# Weather Forecast Backend System

A Spring Boot REST API application for managing and analyzing weather forecast data for Delhi spanning nearly 20 years.

## Overview

This project provides a complete backend solution to import weather data from CSV files, store it in a MySQL database, and expose RESTful APIs for querying and analyzing temperature statistics.

## Features

- Import weather data from CSV files (supports multiple formats)
- Store weather information including temperature, humidity, pressure, and conditions
- Query weather data by date, month, or year
- Calculate temperature statistics (max, median, min) for any year
- Clean architecture with proper separation of concerns

## Technology Stack

- **Java**: 17
- **Framework**: Spring Boot 4.0.3
- **Database**: MySQL 8.0
- **Build Tool**: Maven
- **Libraries**: 
  - Spring Data JPA
  - OpenCSV
  - Lombok

## Project Structure

```
src/main/java/com/assesment/weather_forecast/
├── controller/          # REST API endpoints
├── service/            # Business logic
│   └── impl/          # Service implementations
├── repository/         # Database access layer
├── entity/            # JPA entities
├── dto/               # Data transfer objects
├── mapper/            # Entity-DTO converters
├── util/              # CSV processing utilities
├── config/            # Application configuration
└── exception/         # Exception handlers
```

## Database Schema

```sql
CREATE TABLE weather_data (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    temperature DOUBLE NOT NULL,
    humidity DOUBLE NOT NULL,
    pressure DOUBLE NOT NULL,
    weather_condition VARCHAR(255),
    heat_index DOUBLE,
    INDEX idx_date (date),
    INDEX idx_temperature (temperature)
);
```

## Setup Instructions

### Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository
```bash
git clone <repository-url>
cd weather_forecast
```

2. Configure database in `src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
spring.datasource.username=root
spring.datasource.password=your_password
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

### 1. Upload Weather Data

Upload a CSV file containing weather data.

**Endpoint**: `POST /weather/upload`

**Request**: Form-data with file upload
- Key: `file`
- Value: CSV file

**Response**: 
```
Weather data uploaded and imported successfully
```

---

### 2. Import Weather Data from Path

Import weather data from a file path on the server.

**Endpoint**: `POST /weather/import?filePath={path}`

**Example**:
```
POST /weather/import?filePath=C:/data/weather.csv
```

---

### 3. Get Weather by Date

Retrieve weather details for a specific date.

**Endpoint**: `GET /weather/date/{date}`

**Example**:
```
GET /weather/date/2010-05-12
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

### 4. Get Weather by Month (All Years)

Retrieve all weather records for a specific month across all years.

**Endpoint**: `GET /weather/month/{month}`

**Example**:
```
GET /weather/month/05
```

**Response**: Array of weather records

---

### 5. Get Weather by Year and Month

Retrieve weather records for a specific year and month.

**Endpoint**: `GET /weather/month/{year}/{month}`

**Example**:
```
GET /weather/month/2015/08
```

**Response**: Array of weather records

---

### 6. Get Temperature Statistics

Get monthly temperature statistics (max, median, min) for a specific year.

**Endpoint**: `GET /weather/stats/{year}`

**Example**:
```
GET /weather/stats/2018
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

---

### 7. Clear Database (Admin)

Clear all weather data from the database.

**Endpoint**: `DELETE /admin/clear`

**Response**:
```
Database cleared successfully
```

## CSV File Format

The application supports two CSV formats:

### Simple Format
```csv
date,temperature,humidity,pressure,weather_condition,heat_index
2010-05-12,32.5,55.3,1010.5,Sunny,35.2
```

### Complex Format (with datetime)
```csv
datetime_utc,_conds,_dewptm,_fog,_hail,_heatindexm,_hum,_precipm,_pressurem,_rain,_snow,_tempm,_thunder,_tornado,_vism,_wdird,_wdire,_wgustm,_windchillm,_wspdm
19961101-11:00,Smoke,9,0,0,,27,,1010,0,0,30,0,0,5,280,West,,,7.4
```

## Testing

### Using Postman

1. Import the provided Postman collection: `Weather_Forecast_API.postman_collection.json`
2. Upload a CSV file using the `/weather/upload` endpoint
3. Test other endpoints to retrieve weather data

### Using Browser

For GET requests, simply paste the URL in your browser:
```
http://localhost:8080/weather/date/2010-05-12
http://localhost:8080/weather/month/05
http://localhost:8080/weather/stats/2018
```

### Using cURL

```bash
# Upload file
curl -X POST -F "file=@weather_data.csv" http://localhost:8080/weather/upload

# Get weather by date
curl http://localhost:8080/weather/date/2010-05-12

# Get statistics
curl http://localhost:8080/weather/stats/2018
```

## Key Implementation Details

### Data Processing
- Automatic detection of CSV format
- Duplicate record removal based on date
- Handling of missing values and invalid data
- Support for multiple date formats

### Database Optimization
- Indexed columns for faster queries (date, temperature)
- Bulk insert operations for efficient data import
- Transaction management for data consistency

### Statistics Calculation
- Accurate median calculation using sorted temperature values
- Monthly aggregation of temperature data
- Efficient query design for large datasets

## Architecture Highlights

- **Clean Architecture**: Separation of concerns with distinct layers
- **DTO Pattern**: Safe data transfer between layers
- **Repository Pattern**: Abstraction of data access logic
- **Exception Handling**: Centralized error handling with meaningful messages
- **Modular Design**: Easy to maintain and extend

## Sample Data

A sample CSV file is included at `src/main/resources/sample_weather_data.csv` for testing purposes.

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running
- Verify credentials in `application.properties`
- Check if database `weather_db` exists

### File Upload Issues
- Check file size (max 100MB)
- Verify CSV format matches expected structure
- Ensure proper file permissions

### Duplicate Data
- Clear database using `/admin/clear` endpoint
- Or manually: `TRUNCATE TABLE weather_data;`

## Future Enhancements

- Pagination for large result sets
- Caching layer for improved performance
- API documentation with Swagger
- Authentication and authorization
- Data export functionality
- Advanced filtering options

## Author

Developed as part of a weather data analysis assessment project.

## License

This project is created for educational and assessment purposes.
