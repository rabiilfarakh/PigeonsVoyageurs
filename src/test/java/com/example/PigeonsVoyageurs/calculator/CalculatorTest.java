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
    @Nested
    @DisplayName("Distance Coefficient Calculation")
    class DistanceCoefficientTests {

        @Test
        @DisplayName("Case of valid traveled distance and average distance")
        void validInputs() {
            double traveledDistance = 100.0;
            double distanceAvg = 50.0;

            double result = Calculator.distanceCoefficient(traveledDistance, distanceAvg);
            assertEquals(0.5, result, 0.0001, "The coefficient should be 0.5");
        }

        @Test
        @DisplayName("Case of zero traveled distance")
        void zeroTraveledDistance() {
            double traveledDistance = 0.0;
            double distanceAvg = 50.0;

            assertThrows(IllegalArgumentException.class,
                    () -> Calculator.distanceCoefficient(traveledDistance, distanceAvg),
                    "IllegalArgumentException should be thrown for zero traveled distance.");
        }

        @Test
        @DisplayName("Case of zero average distance")
        void zeroDistanceAvg() {
            double traveledDistance = 100.0;
            double distanceAvg = 0.0;

            double result = Calculator.distanceCoefficient(traveledDistance, distanceAvg);
            assertEquals(0.0, result, 0.0001, "The coefficient should be 0.0");
        }
    }
    @Nested
    @DisplayName("Flight Speed Calculation")
    class FlightSpeedTests {

        @Test
        @DisplayName("Case of valid inputs")
        void validInputs() {
            double traveledDistance = 500.0;
            double flightDuration = 5.0;
            double distanceCoefficient = 0.8;

            double result = Calculator.flightSpeed(traveledDistance, flightDuration, distanceCoefficient);
            assertEquals(80.0, result, 0.0001, "The flight speed should be 80.0");
        }

        @Test
        @DisplayName("Case of zero flight duration")
        void zeroFlightDuration() {
            double traveledDistance = 500.0;
            double flightDuration = 0.0;
            double distanceCoefficient = 0.8;

            assertThrows(IllegalArgumentException.class,
                    () -> Calculator.flightSpeed(traveledDistance, flightDuration, distanceCoefficient),
                    "IllegalArgumentException should be thrown for zero flight duration.");
        }

        @Test
        @DisplayName("Case of zero traveled distance")
        void zeroTraveledDistance() {
            double traveledDistance = 0.0;
            double flightDuration = 5.0;
            double distanceCoefficient = 0.8;

            double result = Calculator.flightSpeed(traveledDistance, flightDuration, distanceCoefficient);
            assertEquals(0.0, result, 0.0001, "The flight speed should be 0.0 when traveled distance is 0.");
        }
    }
}