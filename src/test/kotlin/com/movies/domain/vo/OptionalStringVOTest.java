package com.movies.domain.vo;

import com.movies.domain.exception.DataValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class OptionalStringVOTest {

    @DisplayName("should reject extremely large values")
    @TestFactory
    Stream<DynamicTest> shouldRejectExtremeInput() {
        return Stream.of(
            repeat("X", 10000),
            repeat("X", 100000),
            repeat("X", 1000000),
            repeat("X", 10000000),
            repeat("X", 20000000),
            repeat("X", 40000000)
        )
            .map(largeValue ->
                dynamicTest("Rejecting extremely large values",
                    () -> assertThrows(
                        DataValidationException.class, () -> extremelyLargeStringVO(largeValue))));
    }

    private void extremelyLargeStringVO(String value) {
        new OptionalStringVO(value, 1, 500, "fieldName", "Error Message");
    }
}