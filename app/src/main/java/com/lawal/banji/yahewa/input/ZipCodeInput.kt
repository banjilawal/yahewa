package com.lawal.banji.yahewa.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ZipcodeInput(
    onZipcodeEntered: (String) -> Unit
) {
    var zipcode by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        TextField(
            value = zipcode,
            onValueChange = { value ->
                // Allow only numeric input and a maximum of 5 characters
                if (value.length <= 5 && (value.isEmpty() || value.all { it.isDigit() })) {
                    zipcode = value
                    isError = false
                } else {
                    isError = true // Set an error state for invalid characters
                }

                // If valid and exactly 5 digits, trigger the callback
                if (zipcode.length == 5) {
                    onZipcodeEntered(zipcode) // Notify the parent composable
                }
            },
            maxLines = 1, // TextField restricted to a single line
            placeholder = { Text("Enter ZIP Code") }, // Placeholder text when the field is empty
            isError = isError, // Highlight the text field if input is invalid
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Numeric keyboard
                imeAction = ImeAction.Done // "Done" keyboard action
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Trigger callback when the "Done" key is pressed
                    if (zipcode.length == 5) {
                        onZipcodeEntered(zipcode)
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (it.isFocused && !isFocused) {
                        // Perform any logic such as clearing content
                        zipcode = ""
                        isFocused = true
                    }
                },
            singleLine = true // Ensure input remains on one line
        )

        if (isError) {
            Text(
                text = "Invalid ZIP Code. Please enter a valid 5-digit number.",
                color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

