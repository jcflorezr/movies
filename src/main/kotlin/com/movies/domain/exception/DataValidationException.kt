package com.movies.domain.exception

data class DataValidationException(
    val fieldName: String,
    val fieldValue: String? = null,
    override val message: String
) : BadRequestException(
    message = "fieldName: $fieldName - fieldValue: $fieldValue",
    errorCode = errorCode,
    suggestion = message) {

    companion object {
        private const val errorCode = "invalid_field_value"
    }
}