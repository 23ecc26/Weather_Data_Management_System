# Quick Start Guide

## 🚀 Get Started in 5 Minutes

### Step 1: Verify Prerequisites
```bash
java -version    # Should be 17+
mvn -version     # Should be 3.6+
mysql --version  # Should be 8.0+
```

### Step 2: Setup Database
```bash
mysql -u root -p
```
```sql
CREATE DATABASE weather_db;
EXIT;
```

### Step 3: Configure Application
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### Step 4: Build and Run
```bash
cd weather_forecast
mvn clean install
mvn spring-boot:run
```

Wait for: `Started WeatherForecastApplication`

### Step 5: Import Sample Data
Open new terminal:
```bash
curl -X POST "http://localhost:8080/weather/import?filePath=C:/Users/SOFIA%20SELCY/Downloads/Weather%20data%20assessment%20V.1.0/Assessment%202/testset.csv"
```

### Step 6: Test APIs

**Get weather for specific date:**
```bash
curl http://localhost:8080/weather/date/2010-05-12
```

**Get all May weather data:**
```bash
curl http://localhost:8080/weather/month/05
```

**Get August 2015 weather:**
```bash
curl http://localhost:8080/weather/month/2015/08
```

**Get 2018 temperature statistics:**
```bash
curl http://localhost:8080/weather/stats/2018
```

---

## 🎯 What You Get

### 5 REST APIs
1. **POST** `/weather/import` - Import CSV data
2. **GET** `/weather/date/{date}` - Get weather by date
3. **GET** `/weather/month/{month}` - Get weather by month (all years)
4. **GET** `/weather/month/{year}/{month}` - Get weather by year and month
5. **GET** `/weather/stats/{year}` - Get temperature statistics

### Clean Architecture
- ✅ Controller Layer
- ✅ Service Layer
- ✅ Repository Layer
- ✅ Entity Layer
- ✅ DTO Layer
- ✅ Mapper Layer
- ✅ Utility Layer
- ✅ Config Layer
- ✅ Exception Layer

### Features
- ✅ CSV import with validation
- ✅ Duplicate removal
- ✅ Multiple date formats
- ✅ Temperature statistics (MAX, MEDIAN, MIN)
- ✅ Database indexing
- ✅ Error handling
- ✅ Transaction management

---

## 📁 Project Files

| File | Purpose |
|------|---------|
| `README.md` | Complete documentation |
| `PROJECT_SUMMARY.md` | Implementation summary |
| `API_TESTING_GUIDE.md` | Testing instructions |
| `QUICK_START.md` | This file |
| `Weather_Forecast_API.postman_collection.json` | Postman collection |
| `src/main/resources/sample_weather_data.csv` | Sample data |
| `src/main/resources/schema.sql` | Database schema |

---

## 🔧 Troubleshooting

**Problem**: Port 8080 already in use
**Solution**: Change port in `application.properties`:
```properties
server.port=8081
```

**Problem**: MySQL connection refused
**Solution**: Ensure MySQL is running and credentials are correct

**Problem**: CSV import fails
**Solution**: Use absolute file path with forward slashes

---

## 📊 Sample CSV Format

```csv
date,temperature,humidity,pressure,weather_condition,heat_index
2010-05-12,32.5,55.3,1010.5,Sunny,35.2
2010-05-13,31.8,58.7,1011.2,Clear,34.1
```

---

## 🎓 Next Steps

1. **Add Your Data**: Replace sample CSV with your 20-year dataset
2. **Test Performance**: Import large dataset and test query speed
3. **Customize**: Extend APIs based on your requirements
4. **Deploy**: Package as JAR and deploy to server

---

## 📞 Support

- Check `README.md` for detailed documentation
- Review `API_TESTING_GUIDE.md` for testing examples
- See `PROJECT_SUMMARY.md` for architecture details

---

## ✅ Success Checklist

- [ ] MySQL database created
- [ ] Application started successfully
- [ ] Sample data imported
- [ ] All 5 APIs tested
- [ ] Statistics endpoint returns correct median

**You're all set! 🎉**
