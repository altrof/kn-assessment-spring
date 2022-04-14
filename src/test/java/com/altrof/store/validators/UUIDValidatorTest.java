package com.altrof.store.validators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UUIDValidatorTest {
    private final UUIDValidator uuidValidatorTest = new UUIDValidator();

    @Test
    public void checkCorrectUuidValidate1() {
        assertThat(uuidValidatorTest.test("01234567-9ABC-DEF0-1234-56789ABCDEF0")).isTrue();
    }

    @Test
    public void checkCorrectUuidValidate2() {
        assertThat(uuidValidatorTest.test("fe465581-2407-482e-bdfd-f922201d6ec9")).isTrue();
    }

    @Test
    public void checkIncorrectUuidValidate1() {
        assertThat(uuidValidatorTest.test("f2407-482e-bdfd-f922201d6ec9")).isFalse();
    }

    @Test
    public void checkIncorrectUuidValidate2() {
        assertThat(uuidValidatorTest.test("fe465581-2407-482e-bdfd")).isFalse();
    }

    @Test
    public void checkIncorrectUuidValidate3() {
        assertThat(uuidValidatorTest.test("f2407-482e-bdfd-f922201d6ec9-")).isFalse();
    }

    @Test
    public void checkIncorrectUuidValidate4() {
        assertThat(uuidValidatorTest.test("f2407+482e+bdfd+f922201d6ec9")).isFalse();
    }
}