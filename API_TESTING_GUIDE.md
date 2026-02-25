# API Testing Guide

## Quick Start

### 1. Start MySQL Server
Ensure MySQL is running on localhost:3306

### 2. Start the Application
```bash
cd weather_forecast
mvn spring-boot:run
```

### 3. Import Sample Data
```bash
curl -X POST "http://localhost:8080/weather/import?filePath=C:/Users/SOFIA%20SELCY/Downloads/Weather%20data%20assessment%20V.1.0/Assessment%202/testset.csv"
```

## API Testing Examples

### Test 1: Import Weather Data
```bash
curl -X POST "http://localhost:8080/weather/import?filePath=YOUR_CSV_FILE_PATH"
```

**Expected Response:**
```
Weather data imported successfully
```

---

### Test 2: Get Weather by Specific Date
```bash
curl http://localhost:8080/weather/date/2010-05-12
```

**Expected Response:**
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

### Test 3: Get Weather by Month (All Years)
```bash
curl http://localhost:8080/weather/month/05
```

**Expected Response:**
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
  },
  {
    "date": "2018-05-10",
    "weatherCondition": "Very Hot",
    "temperature": 35.1,
    "humidity": 50.5,
    "pressure": 1008.5,
    "heatIndex": 38.2
  }
]
```

---

### Test 4: Get Weather by Year and Month
```bash
curl http://localhost:8080/weather/month/2015/08
```

**Expected Response:**
```json
[
  {
    "date": "2015-08-15",
    "weatherCondition": "Hot",
    "temperature": 35.2,
    "humidity": 72.5,
    "pressure": 1008.3,
    "heatIndex": 38.5
  },
  {
    "date": "2015-08-16",
    "weatherCondition": "Very Hot",
    "temperature": 36.1,
    "humidity": 70.8,
    "pressure": 1009.1,
    "heatIndex": 39.8
  }
]
```

---

### Test 5: Get Temperature Statistics by Year
```bash
curl http://localhost:8080/weather/stats/2018
```

**Expected Response:**
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
  },
  {
    "month": "March",
    "maxTemperature": 25.8,
    "medianTemperature": 25.8,
    "minTemperature": 25.8
  },
  {
    "month": "April",
    "maxTemperature": 30.2,
    "medianTemperature": 30.2,
    "minTemperature": 30.2
  },
  {
    "month": "May",
    "maxTemperature": 35.1,
    "medianTemperature": 35.1,
    "minTemperature": 35.1
  },
  {
    "month": "June",
    "maxTemperature": 38.5,
    "medianTemperature": 38.5,
    "minTemperature": 38.5
  },
  {
    "month": "July",
    "maxTemperature": 36.8,
    "medianTemperature": 36.8,
    "minTemperature": 36.8
  },
  {
    "month": "August",
    "maxTemperature": 35.5,
    "medianTemperature": 35.5,
    "minTemperature": 35.5
  },
  {
    "month": "September",
    "maxTemperature": 32.8,
    "medianTemperature": 32.8,
    "minTemperature": 32.8
  },
  {
    "month": "October",
    "maxTemperature": 28.5,
    "medianTemperature": 28.5,
    "minTemperature": 28.5
  },
  {
    "month": "November",
    "maxTemperature": 23.2,
    "medianTemperature": 23.2,
    "minTemperature": 23.2
  },
  {
    "month": "December",
    "maxTemperature": 18.5,
    "medianTemperature": 18.5,
    "minTemperature": 18.5
  }
]
```

---

## Testing with Postman

1. Import the Postman collection: `Weather_Forecast_API.postman_collection.json`
2. Update the `filePath` parameter in the "Import Weather Data" request
3. Run the requests in order

---

## Testing with Browser

Open your browser and navigate to:

- http://localhost:8080/weather/date/2010-05-12
- http://localhost:8080/weather/month/05
- http://localhost:8080/weather/month/2015/08
- http://localhost:8080/weather/stats/2018

---

## Error Scenarios

### Invalid Date Format
```bash
curl http://localhost:8080/weather/date/invalid-date
```
**Response:** 400 Bad Request - "Invalid date format. Use yyyy-MM-dd"

### Invalid Month
```bash
curl http://localhost:8080/weather/month/13
```
**Response:** 400 Bad Request

### Date Not Found
```bash
curl http://localhost:8080/weather/date/2025-12-31
```
**Response:** 404 Not Found

---

## Verify Database

Connect to MySQL and run:

```sql
USE weather_db;

-- Check total records
SELECT COUNT(*) FROM weather_data;

-- View sample data
SELECT * FROM weather_data LIMIT 10;

-- Check date range
SELECT MIN(date), MAX(date) FROM weather_data;
```

---

## Performance Testing

### Bulk Import Test
Create a large CSV file with 20 years of data (7300+ records) and test import performance.

### Query Performance Test
```bash
# Test response time for different queries
time curl http://localhost:8080/weather/month/05
time curl http://localhost:8080/weather/stats/2018
```

---

## Troubleshooting

### Issue: Connection refused
**Solution:** Ensure MySQL is running and credentials are correct in application.properties

### Issue: CSV import fails
**Solution:** Check file path is absolute and file format matches expected structure

### Issue: Empty response
**Solution:** Ensure data is imported before querying

---

## Next Steps

1. Add more weather data to CSV file
2. Test with 20 years of data
3. Verify median calculation with larger datasets
4. Test pagination for large result sets
