package com.altrof.store.validators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DateValidatorTest {
    private final DateValidator dateValidatorUnderTest = new DateValidator();

    @Test
    void checkDateFormatWhatNotSupport() {
        assertThat(dateValidatorUnderTest.test("14-02-2022")).isFalse();
    }

    @Test
    void checkDateValidatorForWrongDayWithCorrectFormat() {
        assertThat(dateValidatorUnderTest.test("2022-02-45")).isFalse();
    }

    @Test
    void checkDateValidatorForWrongMonthWithCorrectFormat() {
        assertThat(dateValidatorUnderTest.test("2022-15-02")).isFalse();
    }

    @Test
    void checkDateFormatWhatUnSupport() {
        assertThat(dateValidatorUnderTest.test("2022/11/24")).isFalse();
    }

    @Test
    void checkDateFormatWhatSupport1() {
        assertThat(dateValidatorUnderTest.test("2022-11-24")).isTrue();
    }
}