package com.example.PigeonsVoyageurs.calculator;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class Calculator {
    private static final double EARTH_RADIUS = 6378.1370;

    public static double calculateDistance(Coordinate start, Coordinate end) {
        double dLat = Math.toRadians(start.getLatitude() - end.getLatitude());
        double dLong = Math.toRadians(start.getLongitude() - end.getLongitude());

        double startLat = Math.toRadians(start.getLatitude());
        double endLat = Math.toRadians(end.getLatitude());

        double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);

        return 2 * EARTH_RADIUS * Math.asin(Math.sqrt(a));

    }
    private static double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    public static LocalTime calculateFlightDuration(Date date1, Date date2) {
        Duration duration = Duration.between(date1.toInstant(), date2.toInstant());
        Duration absDuration = duration.abs();
        return LocalTime.ofSecondOfDay(absDuration.getSeconds());
    }

    public static double distanceAvg(List<Double> distances){
            OptionalDouble optionalAvg = distances.stream().mapToDouble(Double::doubleValue).average();
            return optionalAvg.orElseThrow(NullPointerException::new);
    }
    public static double distanceCoefficient(double traveledDistance, double distanceAvg){
        if (traveledDistance == 0){
            throw new IllegalArgumentException("The traveled distance can not be null");
        }else {
            return distanceAvg / traveledDistance;
        }
    }
    public static double flightSpeed(double traveledDistance, double flightDuration, double distanceCoefficient){
        if (flightDuration == 0){
            throw new IllegalArgumentException("The flight duration can not be null");
        }else {
            return (traveledDistance / flightDuration) * distanceCoefficient;
        }
    }
}
