package com.example.myapplication.view.addScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.view.camerasBookNProfile.itemsInCameras.TextFieldCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndFilters() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableListOf(
            "VisionBook",
            "Iglobruuuh"
        )
    }
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(30),
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            items.add(text)
            active = false
            text = ""
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(stringResource(R.string.search),
                style = MaterialTheme.typography.titleMedium)

        },
        trailingIcon = {
            if (active) {
                Icon(
                    painterResource(R.drawable.close),
                    "close",
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()){
                            text = ""
                        } else {
                            active = false
                        }
                    }
                )
            }

        }
    ){
        items.forEach {
            Row (
                modifier = Modifier.padding(14.dp)
            ) {
                Icon(painter = painterResource(R.drawable.history), "History",
                    modifier = Modifier.padding(end = 10.dp))
                Text(text = it)
            }
        }
    }

}