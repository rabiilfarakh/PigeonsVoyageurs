package com.example.PigeonsVoyageurs.calculator;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Nested
    @DisplayName("Distance calculation between two coordinates")
    class CalculateDistance{
        // https://www.omnicalculator.com/fr/autre/calculateur-distance-latitude-longitude
        // This link above for a platform for calculating distance on earth
        @Test
        @DisplayName("Positive coordinates case")
        void PositiveCoordinatesCase(){
            Coordinate start = new Coordinate(33.954, 59.115);
            Coordinate end = new Coordinate(78.846, 23.652);

            double result = Math.round(Calculator.calculateDistance(start, end));

            assertEquals(5261.0, result, "the actual: "+result);

        }
        @Test
        @DisplayName("Negative coordinates case")
        void NegativeCoordinatesCase(){
            Coordinate start = new Coordinate(-33.954, -59.115);
            Coordinate end = new Coordinate(-78.846, -23.652);

            double result = Math.round(Calculator.calculateDistance(start, end));

            assertEquals(5261.0, result, "the actual: "+result);
        }
    }

    @Nested
    @DisplayName("Duration calculation two instants")
    class CalculateDuration{
        @Test
        @DisplayName("instants in the right order")
        void RightOrderCase(){
            Date startDate = new Date();
            Date endDate = new Date(startDate.getTime() + 3600000);

            LocalTime result = Calculator.calculateFlightDuration(startDate, endDate);

            assertEquals(LocalTime.of(1,0,0), result, "Duration should be 01:00:00");
        }
        @Test
        @DisplayName("instants not in the right order")
        void WrongOrderCase(){
            Date startDate = new Date();
            Date endDate = new Date(startDate.getTime() - 3600000);

            LocalTime result = Calculator.calculateFlightDuration(startDate, endDate);

            assertEquals(LocalTime.of(1,0,0), result, "Duration should be 01:00:00");
        }
    }
    @Nested
    @DisplayName("Distances average calculation")
    class CalculateDistanceAvg{
        @Test
        @DisplayName("Case of not empty distances list")
        void DistanceListNotEmpty(){
            List<Double> distances = List.of(10.0, 20.0, 30.0);
            double result = Calculator.distanceAvg(distances);
            assertEquals(20.0, result, 0.0001, "The average should be 20.0");
        }
        @Test
        @DisplayName("Case of null distances list")
        void DistanceListEmpty(){
            List<Double> distances = List.of();

            assertThrows(NullPointerException.class, () -> Calculator.distanceAvg(distances),
                    "NullPointerException should be thrown for an empty list.");
        }
    }
}