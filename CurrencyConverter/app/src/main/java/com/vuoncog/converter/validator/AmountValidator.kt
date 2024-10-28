package com.vuoncog.converter.validator

import com.vuoncog.converter.ui.models.TextFieldError
import javax.inject.Inject

class AmountValidator @Inject constructor() : ValidationPattern {
    override val rules: Map<(String) -> Boolean, TextFieldError>
        get() = mapOf(
            { amount: String -> validateAmount(amount) } to TextFieldError.INVALID
        )

    override fun validator(text: String): TextFieldError {
        rules.forEach { (validator, errorType) ->
            if (!validator.invoke(text)) {
                return errorType
            }
        }
        return TextFieldError.NONE
    }

    private fun validateAmount(amount: String): Boolean {
        return try {
            amount.toDouble()
            true
        } catch (_: Exception) {
            false
        }
    }
}