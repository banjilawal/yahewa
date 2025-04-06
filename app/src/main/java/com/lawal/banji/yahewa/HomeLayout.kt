package com.lawal.banji.yahewa


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF90D8CEC9))
                .padding(16.dp),
            fontSize = 25.sp
        )
        Text(
            text = stringResource(id = R.string.location),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.current_temperature),
                    fontSize = 25.sp
                )
                Text(
                    text = stringResource(id = R.string.feels_like_temperature),
                    fontSize = 25.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.sunny),
                contentDescription = stringResource(id = R.string.image_description),
                modifier = Modifier.size(100.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.low_temperature),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 25.sp
        )
        Text(
            text = stringResource(id = R.string.high_temperature),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 25.sp
        )
        Text(
            text = stringResource(id = R.string.percent_humidity),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 25.sp
        )
        Text(
            text = stringResource(id = R.string.pressure),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 25.sp
        )
    }
}