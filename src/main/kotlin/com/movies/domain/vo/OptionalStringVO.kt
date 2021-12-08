package com.movies.domain.vo

class OptionalStringVO(
    value: String = "",
    minLength: Int,
    maxLength: Int,
    fieldName: String,
    errorMessage: String? = null
) : GenericVO(value, minLength, maxLength, fieldName, errorMessage) {

    init {
        super.validateOptionalValueLength()
    }

}