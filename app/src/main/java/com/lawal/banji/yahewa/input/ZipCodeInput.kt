package com.lawal.banji.yahewa.input

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ZipCodeInput(
    onZipCodeEntered: (String) -> Unit
) {
    var zipCode by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        TextField(
            value = zipCode,
            onValueChange = { value ->
                if (value.length <= 5 && (value.isEmpty() || value.all { it.isDigit() })) {
                    zipCode = value
                    isError = false
                } else {
                    isError = true
                    Toast.makeText(
                        context,
                        "Invalid ZIP code. Please enter only numbers up to 5 digits.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                if (zipCode.length == 5) {
                    onZipCodeEntered(zipCode)
                    keyboardController?.hide()
                }
            },
            maxLines = 1,
            placeholder = { Text("Enter ZIP Code") },
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (zipCode.length == 5) {
                        onZipCodeEntered(zipCode)
                        keyboardController?.hide()
                    } else {
                        Toast.makeText(
                            context,
                            "Invalid ZIP code. Please ensure it is 5 digits.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .padding(8.dp),
            singleLine = true
        )

        if (isError) {
            Text(
                text = "Invalid ZIP Code. Please enter a valid 5-digit number.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(400)
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

