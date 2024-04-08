package com.example.visionbook.view.camerasBookNProfile.itemsInCameras

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.R


@Composable
fun TextFieldCustom (placeholder: String, emailState: MutableState<String>, onValueChange: (String) -> Unit) {
    TextField(
        value = emailState.value,
        singleLine = true,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(percent = 30),
        trailingIcon = {
            IconButton(onClick = { emailState.value = "" }) {
                Icon(
                    painterResource(R.drawable.close),
                    "close",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        placeholder = {
            AutoresizedText(
                placeholder,
            )
        },
        // убираю нижнее подчеркивание
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun TextFieldPass(
    placeholder: String,
    secondPassword: Boolean = false,
    passwordToMatch: String? = null,
    passwordState: MutableState<String>,
    onValueChange: (String) -> Unit
) {
    val showPasswordState = remember { mutableStateOf(false) }

    // Проверяем на схожесть пароли, если это второй пароль
    val passwordMatches = if (secondPassword) {
        passwordState.value == passwordToMatch
    } else {
        true // Если это не второй пароль, то сразу считаем, что пароли совпадают
    }

    // Отображаем визуальное предупреждение, если пароли не совпадают
    val passwordColor = if (passwordMatches) Color.Unspecified else Color.Red

    TextField(
        value = passwordState.value,
        onValueChange = {
                newValue -> passwordState.value = newValue
        },
        placeholder = { Text(placeholder) },
        visualTransformation = if (showPasswordState.value) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.padding(bottom = 20.dp),
        trailingIcon = {
            IconButton(onClick = { showPasswordState.value = !showPasswordState.value }) {
                Icon(
                    painter = if (showPasswordState.value) painterResource(R.drawable.show_pass)
                    else painterResource(R.drawable.hide_pass),
                    contentDescription = if (showPasswordState.value) "Hide Password" else "Show Password",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        textStyle = TextStyle(color = passwordColor) // Устанавливаем цвет текста поля ввода в зависимости от совпадения паролей
    )
}
