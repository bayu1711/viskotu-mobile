package com.viskotu.app.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.viskotu.app.designsystem.ViskotuColors
import com.viskotu.app.designsystem.ViskotuTypography

/**
 * Reusable Form Input Component matching `FORM_COMPONENTS.md` standards.
 * Provides consistent label spacing (`mb-2`), error handling (`text-red-600`), and border radii.
 */
@Composable
fun ViskotuInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    errorMessage: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true
) {
    val isError = !errorMessage.isNullOrBlank()

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = ViskotuTypography.labelLarge,
            color = if (isError) ViskotuColors.Error500 else ViskotuColors.Neutral900
        )
        Spacer(modifier = Modifier.height(8.dp)) // mb-2 (8px)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                if (placeholder.isNotBlank()) {
                    Text(
                        text = placeholder,
                        style = ViskotuTypography.bodyMedium,
                        color = ViskotuColors.Neutral400
                    )
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            modifier = Modifier.fillMaxWidth(),
            singleLine = singleLine,
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) ViskotuColors.Error500 else ViskotuColors.Primary500,
                unfocusedBorderColor = if (isError) ViskotuColors.Error500 else ViskotuColors.Neutral200,
                focusedContainerColor = ViskotuColors.White,
                unfocusedContainerColor = ViskotuColors.White,
                cursorColor = ViskotuColors.Primary500,
                focusedTextColor = ViskotuColors.Neutral900,
                unfocusedTextColor = ViskotuColors.Neutral900
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
        if (isError) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage ?: "",
                style = ViskotuTypography.bodySmall,
                color = ViskotuColors.Error500
            )
        }
    }
}
