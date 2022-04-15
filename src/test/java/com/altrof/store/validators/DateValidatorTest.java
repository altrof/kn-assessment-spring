package com.altrof.store.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest()
class DateValidatorTest {
    private final DateValidator dateValidatorUnderTest = new DateValidator();

    @Test
    public void checkDateFormatWhatNotSupport() {
        assertThat(dateValidatorUnderTest.test("14-02-2022")).isFalse();
    }

    @Test
    public void checkDateValidatorForWrongDayWithCorrectFormat() {
        assertThat(dateValidatorUnderTest.test("2022-02-45")).isFalse();
    }

    @Test
    public void checkDateValidatorForWrongMonthWithCorrectFormat() {
        assertThat(dateValidatorUnderTest.test("2022-15-02")).isFalse();
    }

    @Test
    public void checkDateFormatWhatUnSupport() {
        assertThat(dateValidatorUnderTest.test("2022/11/24")).isFalse();
    }

    @Test
    public void checkDateFormatWhatSupport1() {
        assertThat(dateValidatorUnderTest.test("2022-11-24")).isTrue();
    }
}