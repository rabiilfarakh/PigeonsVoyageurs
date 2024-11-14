package com.example.PigeonsVoyageurs.calculator;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Date;

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
}