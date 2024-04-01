package com.example.myapplication.view.addScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndFilters() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(true) }
    var items = remember {
        mutableListOf(
            "VisionBook",
            "Iglobruuuh"
        )
    }
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
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
            Text(stringResource(R.string.search))
        },
        trailingIcon = {
            if (active) {
                Icon(
                    painterResource(R.drawable.close),
                    "close",
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                    }
                )
            }

        }
    ) {
        items.forEach {
            Row(
                modifier = Modifier.padding(14.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.history), "History",
                    modifier = Modifier.padding(end = 10.dp).size(30.dp)
                )
                Text(text = it)
            }
        }
    }

}