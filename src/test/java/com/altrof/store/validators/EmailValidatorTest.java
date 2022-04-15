package com.altrof.store.validators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// Check Email Regex Pattern
class EmailValidatorTest {

    private final EmailValidator emailValidatorTest = new EmailValidator();

    @Test
    public void checkCorrectEmailValidate() {
        assertThat(emailValidatorTest.test("test@test.com")).isTrue();
    }

    @Test
    public void checkIncorrectEmailValidateWithoutAt() {
        assertThat(emailValidatorTest.test("testtest.com")).isFalse();
    }

    @Test
    public void checkIncorrectEmailValidateWithoutDotInTheEnd() {
        assertThat(emailValidatorTest.test("test@test")).isFalse();
    }

    @Test
    public void checkIncorrectEmailValidateWithoutWordBeforeAt() {
        assertThat(emailValidatorTest.test("@test.com")).isFalse();
    }

    @Test
    public void checkIncorrectEmailValidateWithoutWordAfterAt() {
        assertThat(emailValidatorTest.test("test@")).isFalse();
    }
}