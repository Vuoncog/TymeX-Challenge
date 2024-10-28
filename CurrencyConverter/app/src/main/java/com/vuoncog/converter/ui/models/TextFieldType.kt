package com.vuoncog.converter.ui.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vuoncog.converter.R

enum class TextFieldType(
    val required: Boolean = false,
    @DrawableRes val leadingIcon: Int?,
    @StringRes val label: Int,
    @StringRes val placeholder: Int,
    val error: Map<TextFieldError, Int?>,
) {
    AMOUNT(
        required = true,
        leadingIcon = null,
        label = R.string.amount,
        placeholder = R.string.enter_the_transaction_amount,
        error = TextFieldError.amountError()
    );

    companion object {
        fun TextFieldType.getErrorMessage(textFieldError: TextFieldError) =
            this.error[textFieldError]
    }
}

sealed interface TextFieldError {
    object NONE : TextFieldError
    object INVALID : TextFieldError

    companion object {
        fun amountError() = mapOf<TextFieldError, Int?>(INVALID to R.string.number_is_invalid)
    }
}