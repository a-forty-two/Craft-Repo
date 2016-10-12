#CSC 423 Final Project

write.csv(test,"test.csv")
#Set Working Directory
setwd("~/Dropbox/Grad School/CSC 423/Final Project")

#Load the dataset
day <- read.csv("~/Dropbox/Grad School/CSC 423/Final Project/day.csv")

#drop month, weekday, year variables
day_drop = day[,-c(4,5,7)]

#create dummy variables for season, base case = 4
day_drop$season_1 = ifelse(day_drop$season == 1,1,0)
day_drop$season_2 = ifelse(day_drop$season == 2,1,0)
day_drop$season_3 = ifelse(day_drop$season == 3,1,0)

#create dummy variables for weather sit base case = 3
day_drop$weather_1 = ifelse(day_drop$weathersit == 1,1,0)
day_drop$weather_2 = ifelse(day_drop$weathersit == 2,1,0)

#create second order variables
day_drop$atemp_sq = day_drop$atemp ^ 2
day_drop$temp_sq = day_drop$temp ^ 2
day_drop$hum_sq = day_drop$hum ^ 2
day_drop$windspeed_sq = day_drop$windspeed ^ 2
day_drop$casual_sq = day_drop$casual ^ 2
day_drop$registered_sq = day_drop$registered ^ 2

#outlier detection, z score conversion
day_drop$temp_scale = scale(day_drop$temp)
day_drop$atemp_scale = scale(day_drop$atemp)
day_drop$hum_scale = scale(day_drop$hum)
day_drop$wind_scale = scale(day_drop$windspeed)
day_drop$casual_scale = scale(day_drop$casual)
day_drop$registed_scale = scale(day_drop$registered)

#check for outliers using the scaled variables, outliers in hum_scale, wind_scale, casual_scale
summary(day_drop[,c(30:35])

#create dataset of the outliers          
outliers = subset(day_drop, temp_scale < -3 | temp_scale > 3 |
                            atemp_scale < -3 | atemp_scale > 3 |
                            hum_scale < -3 | hum_scale > 3 |
                            wind_scale < -3 | wind_scale > 3 |
                            casual_scale < -3 | casual_scale > 3 |
                            registed_scale < -3 | registed_scale > 3)

#remove the outliers 720 observations now
data = subset(day_drop, temp_scale > -3 & temp_scale < 3 &
                   atemp_scale > -3 & atemp_scale < 3 &
                   hum_scale > -3 & hum_scale < 3 &
                   wind_scale > -3 & wind_scale < 3 &
                   casual_scale > -3 & casual_scale < 3 &
                   registed_scale > -3 & registed_scale < 3)

#correlate the continuous variables
cor((data[,c(7:10)]))

#plot the continuos variables
plot(data[,c(7:10)])
               
#interaction terms for temp with season, weather and holiday
data$season1_temp = data$season_1 * data$temp
data$season2_temp = data$season_2 * data$temp
data$season3_temp = data$season_3 * data$temp

data$weather1_temp = data$weather_1 * data$temp
data$weather2_temp = data$weather_2 * data$temp

data$holiday_temp = data$holiday * data$temp

#interaction terms for hum with season, weather and holiday
data$season1_hum = data$season_1 * data$hum
data$season2_hum = data$season_2 * data$hum
data$season3_hum = data$season_3 * data$hum

data$weather1_hum = data$weather_1 * data$hum
data$weather2_hum = data$weather_2 * data$hum

data$holiday_hum = data$holiday * data$hum

#interaction terms for windspeed with season, weather and holiday
data$season1_windspeed = data$season_1 * data$windspeed
data$season2_windspeed = data$season_2 * data$windspeed
data$season3_windspeed = data$season_3 * data$windspeed

data$weather1_windspeed = data$weather_1 * data$windspeed
data$weather2_windspeed = data$weather_2 * data$windspeed

data$holiday_windspeed = data$holiday * data$windspeed


#test for interaction with temp and season
model_temp_season = lm(data$cnt ~ data$temp +
                                  data$season1_temp +
                                  data$season2_temp +
                                  data$season3_temp, 
                                  data = data)

summary(model_temp_season)

#test for interaction with weather
model_temp_weather = lm(data$cnt ~ data$temp +
                                   data$weather1_temp +
                                   data$weather2_temp,
                                   data = data)

summary(model_temp_weather)

#interaction between holiday and hum
model_holiday_hum = lm(data$cnt ~ data$temp +
                                  data$holiday_temp,
                                  data = data)

summary(model_holiday_hum)


#test for interaction with hum and season
model_hum_season = lm(data$cnt ~ data$hum +
                         data$season1_hum +
                         data$season2_hum +
                         data$season3_hum, 
                         data = data)

summary(model_hum_season)

#test for interaction for hum and weather
model_hum_weather = lm(data$cnt ~ data$hum +
                                   data$weather1_hum +
                                   data$weather2_hum,
                                   data = data)

summary(model_hum_weather)

#interaction between holiday and hum
model_holiday_hum = lm(data$cnt ~ data$hum +
                                  data$holiday_hum,
                                  data = data)

summary(model_holiday_hum)

#test for interaction with windspeed and season
model_windspeed_season = lm(data$cnt ~ data$windspeed +
                                       data$season1_windspeed +
                                       data$season2_windspeed +
                                       data$season3_windspeed, 
                                       data = data)

summary(model_windspeed_season)

#test for interaction for windspeed and weather
model_windspeed_weather = lm(data$cnt ~ data$windspeed +
                                        data$weather1_windspeed +
                                        data$weather2_windspeed,
                                        data = data)

summary(model_windspeed_weather)

#interaction between windspeed and hum
model_windspeed_holiday = lm(data$cnt ~ data$windspeed +
                                       data$holiday_windspeed,
                                       data = data)

summary(model_windspeed_holiday)

model_test_square = lm(data$cnt ~ data$temp +
                                  data$hum +
                                  data$temp_sq +
                                  data$windspeed_sq +
                                  data$windspeed +
                                  data$hum_sq,
                                  data = data)

summary(model_test_square)


#create a training and test
## 75% of the sample size
smp_size <- floor(0.75 * nrow(data))

## set the seed to make your partition reproductible
set.seed(123)
train_ind <- sample(seq_len(nrow(data)), size = smp_size)

train <- data[train_ind, ]
test <- data[-train_ind, ]



#create model full
model_full = lm(train$cnt ~ 
                  train$temp +
                  train$temp_sq +
                  train$hum +
                  train$hum_sq +
                  train$windspeed +
                  train$windspeed_sq +
                  train$holiday +
                  train$season_1 +
                  train$season_2 +
                  train$season_3 +
                  train$weather_1 +
                  train$weather_2 +
                  train$season1_temp+
                  train$season2_temp+
                  train$season3_temp+
                  train$season1_hum+
                  train$season2_hum+
                  train$season3_hum+
                  train$season1_windspeed+
                  train$season2_windspeed+
                  train$season3_windspeed+
                  train$weather1_temp+
                  train$weather2_temp+
                  train$weather1_hum+
                  train$weather2_hum+
                  train$weather1_windspeed+
                  train$weather2_windspeed+
                  train$holiday_temp +
                  train$holiday_hum +
                  train$holiday_windspeed 
                  ,data = train)

step(model_full, direction = "both")

test_model = lm(formula = train$cnt ~ train$temp + 
                                      train$hum + 
                                      train$temp_sq + 
                                      train$holiday +
                                      train$weather_1 +
                                      train$weather_2,
                                      data = train)

summary(test_model)

step_best = lm(formula = train$cnt ~ train$temp + train$temp_sq + train$hum + 
                 train$windspeed_sq + train$holiday + train$season_1 + train$season_2 + 
                 train$season_3 + train$weather_1 + train$season3_temp + train$season3_hum + 
                 train$weather1_temp + train$weather2_temp + train$weather1_hum + 
                 train$weather2_windspeed, data = train)
summary(step_best)

