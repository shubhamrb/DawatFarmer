package com.dawat.farmer.mamits.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WeatherModel implements Serializable {

    @SerializedName("location")
    private LocationModel location;

    @SerializedName("current")
    private CurrentModel current;

    @SerializedName("forecast")
    private ForecastModel forecast;


    public static class LocationModel implements Serializable {
        @SerializedName("name")
        String name;

        @SerializedName("region")
        String region;

        @SerializedName("country")
        String country;

        @SerializedName("lat")
        String lat;

        @SerializedName("lon")
        String lon;

        @SerializedName("tz_id")
        String tz_id;

        @SerializedName("localtime_epoch")
        String localtime_epoch;

        @SerializedName("localtime")
        String localtime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getTz_id() {
            return tz_id;
        }

        public void setTz_id(String tz_id) {
            this.tz_id = tz_id;
        }

        public String getLocaltime_epoch() {
            return localtime_epoch;
        }

        public void setLocaltime_epoch(String localtime_epoch) {
            this.localtime_epoch = localtime_epoch;
        }

        public String getLocaltime() {
            return localtime;
        }

        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", region='" + region + '\'' +
                    ", country='" + country + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    ", tz_id='" + tz_id + '\'' +
                    ", localtime_epoch='" + localtime_epoch + '\'' +
                    ", localtime='" + localtime + '\'' +
                    '}';
        }
    }

    public static class CurrentModel implements Serializable {
        @SerializedName("temp_c")
        float temp_c;

        @SerializedName("humidity")
        int humidity;

        @SerializedName("cloud")
        int cloud;

        @SerializedName("condition")
        ConditionModel condition;

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getCloud() {
            return cloud;
        }

        public void setCloud(int cloud) {
            this.cloud = cloud;
        }

        public float getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(float temp_c) {
            this.temp_c = temp_c;
        }

        public ConditionModel getCondition() {
            return condition;
        }

        public void setCondition(ConditionModel condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return "{" +
                    "temp_c=" + temp_c +
                    ", humidity=" + humidity +
                    ", cloud=" + cloud +
                    ", condition=" + condition +
                    '}';
        }

        public static class ConditionModel implements Serializable {
            @SerializedName("text")
            String text;

            @SerializedName("icon")
            String icon;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            @Override
            public String toString() {
                return "{" +
                        "text='" + text + '\'' +
                        ", icon='" + icon + '\'' +
                        '}';
            }
        }
    }


    public static class ForecastModel implements Serializable {
        @SerializedName("forecastday")
        List<ForecastDayModel> forecastday;

        public List<ForecastDayModel> getForecastday() {
            return forecastday;
        }

        public void setForecastday(List<ForecastDayModel> forecastday) {
            this.forecastday = forecastday;
        }

        @Override
        public String toString() {
            return "{" +
                    "forecastday=" + forecastday +
                    '}';
        }

        public static class ForecastDayModel implements Serializable {
            @SerializedName("date")
            String date;

            @SerializedName("day")
            DayModel day;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public DayModel getDay() {
                return day;
            }

            public void setDay(DayModel day) {
                this.day = day;
            }

            @Override
            public String toString() {
                return "{" +
                        "date='" + date + '\'' +
                        ", day=" + day +
                        '}';
            }

            public static class DayModel implements Serializable {
                @SerializedName("maxtemp_c")
                float maxtemp_c;

                @SerializedName("mintemp_c")
                float mintemp_c;

                @SerializedName("avghumidity")
                float avghumidity;

                @SerializedName("daily_chance_of_rain")
                int daily_chance_of_rain;

                @SerializedName("condition")
                CurrentModel.ConditionModel condition;

                public float getMaxtemp_c() {
                    return maxtemp_c;
                }

                public void setMaxtemp_c(float maxtemp_c) {
                    this.maxtemp_c = maxtemp_c;
                }

                public float getMintemp_c() {
                    return mintemp_c;
                }

                public void setMintemp_c(float mintemp_c) {
                    this.mintemp_c = mintemp_c;
                }

                public float getAvghumidity() {
                    return avghumidity;
                }

                public void setAvghumidity(float avghumidity) {
                    this.avghumidity = avghumidity;
                }

                public int getDaily_chance_of_rain() {
                    return daily_chance_of_rain;
                }

                public void setDaily_chance_of_rain(int daily_chance_of_rain) {
                    this.daily_chance_of_rain = daily_chance_of_rain;
                }

                public CurrentModel.ConditionModel getCondition() {
                    return condition;
                }

                public void setCondition(CurrentModel.ConditionModel condition) {
                    this.condition = condition;
                }

                @Override
                public String toString() {
                    return "{" +
                            "maxtemp_c=" + maxtemp_c +
                            ", mintemp_c=" + mintemp_c +
                            ", avghumidity=" + avghumidity +
                            ", daily_chance_of_rain=" + daily_chance_of_rain +
                            ", condition=" + condition +
                            '}';
                }
            }
        }
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public CurrentModel getCurrent() {
        return current;
    }

    public void setCurrent(CurrentModel current) {
        this.current = current;
    }

    public ForecastModel getForecast() {
        return forecast;
    }

    public void setForecast(ForecastModel forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "{" +
                "location=" + location +
                ", current=" + current +
                ", forecast=" + forecast +
                '}';
    }
}
