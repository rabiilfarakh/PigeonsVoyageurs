package com.example.PigeonsVoyageurs.calculator;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

            assertEquals(5261.0, result);

            System.out.println("the actual: "+result);
        }
        @Test
        @DisplayName("Negative coordinates case")
        void NegativeCoordinatesCase(){
            Coordinate start = new Coordinate(-33.954, -59.115);
            Coordinate end = new Coordinate(-78.846, -23.652);

            double result = Math.round(Calculator.calculateDistance(start, end));

            assertEquals(5261.0, result);

            System.out.println("the actual: "+result);
        }
    }
}