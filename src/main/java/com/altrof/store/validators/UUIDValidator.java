package com.altrof.store.validators;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Component
public class UUIDValidator implements Predicate<String> {
    private static final Predicate<String> IS_UUID_VALID =
            Pattern.compile(
                    "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                    Pattern.CASE_INSENSITIVE
            ).asPredicate();

    @Override
    public boolean test(String uuid) {
        return IS_UUID_VALID.test(uuid);
    }
}
