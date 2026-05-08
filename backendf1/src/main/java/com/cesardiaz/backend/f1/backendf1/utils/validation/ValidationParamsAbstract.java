package com.cesardiaz.backend.f1.backendf1.utils.validation;

import io.micrometer.common.util.StringUtils;

import java.util.List;

public abstract class ValidationParamsAbstract {


    /**
     * Adds the field name to the violations list if the given string value
     * is null, empty, or blank.
     *
     * @param value     the string value to check
     * @param fieldName the name of the field to report if invalid
     * @param params    the list collecting all violated field names
     */
    protected void validateBlank(String value, String fieldName, List<String> params) {
        if (StringUtils.isBlank(value))
            params.add(fieldName);
    }

    /**
     * Adds the field name to the violations list if the given value is null.
     * <p>
     * Use this for non-string fields such as {@code Integer} or {@code Long}
     * where {@code isBlank()} does not apply.
     * </p>
     *
     * @param value     the value to check
     * @param fieldName the name of the field to report if null
     * @param params    the list collecting all violated field names
     */
    protected void validateNull(Object value, String fieldName, List<String> params) {
        if (value == null)
            params.add(fieldName);
    }
}
