package com.lawal.banji.yahewa.input

import android.content.Context
import android.location.Location
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.google.android.gms.location.LocationServices
import com.lawal.banji.yahewa.request.getCurrentLocationSafely
import com.lawal.banji.yahewa.request.startLocationService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LocationButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Use My Location"
        )
    }
}

fun showInvalidZipCodeToast(context: Context) {
    Toast.makeText(
        context,
        "Invalid ZIP code. Please enter only numbers up to 5 digits.",
        Toast.LENGTH_SHORT
    ).show()
}

fun showFetchingLocationToast(context: Context) {
    Toast.makeText(context, "Fetching current location...", Toast.LENGTH_SHORT).show()
}

@Composable
fun ZipCodeInput(
    onZipCodeEntered: (String) -> Unit,
    onLocationFetched: (Location) -> Unit // Callback for location
) {
    var zipCode by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = zipCode,
            onValueChange = { value ->
                if (value.length <= 5 && value.all { it.isDigit() }) {
                    zipCode = value
                    isError = false
                } else {
                    isError = true
                }

                if (zipCode.length == 5) {
                    onZipCodeEntered(zipCode)
                    keyboardController?.hide()
                }
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester)
                .padding(end = 8.dp),
            placeholder = { Text("Enter ZIP Code") },
            isError = isError,
            maxLines = 1,
            singleLine = true,
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
                        isError = true
                        showInvalidZipCodeToast(context)
                    }
                }
            )
        )

        // Updated LocationButton implementation
        LocationButton(onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                val activity = context as? ComponentActivity
                if (activity != null) {
                    // Get current location using the LocationUtils function
                    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
                    val location = fusedLocationClient.getCurrentLocationSafely(activity)

                    if (location != null) {
                        showFetchingLocationToast(context) // Show feedback to the user
                        onLocationFetched(location) // Provide location to the caller
                        context.startLocationService() // Start the location service
                    } else {
                        Toast.makeText(context, "Unable to fetch location.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}



