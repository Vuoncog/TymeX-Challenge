package com.vuoncog.converter.validator

import com.vuoncog.converter.ui.models.TextFieldError

interface ValidationPattern {
    val rules: Map<(String) -> Boolean, TextFieldError>
    fun validator(text: String) : TextFieldError
}