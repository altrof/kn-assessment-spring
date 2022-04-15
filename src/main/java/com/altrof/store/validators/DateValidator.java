package com.altrof.store.validators;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class DateValidator implements Predicate<String> {

    private static final Predicate<String> IS_DATE_VALID =
            Pattern.compile(
                    "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String date) {
        return IS_DATE_VALID.test(date);
    }
}
