package com.example.visionbook.view.mainScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Импорт для getValue()
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext // Для Toast, если нужно
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
// Убедитесь, что используется androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
// import androidx.lifecycle.viewmodel.compose.viewModel // ProfileScreenVM больше не нужен здесь?
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.visionbook.R
import com.example.visionbook.data.MenuItem
import com.example.visionbook.models.AutoresizedText
import com.example.visionbook.models.NavigationItems
// import com.example.visionbook.view.navigation.AuthScreen // Не используется здесь
import com.example.visionbook.view.navigation.GraphRoute
import com.example.visionbook.view.navigation.SettingsScreen
import com.example.visionbook.viewmodels.AuthVM
// import com.example.visionbook.viewmodels.ProfileScreenVM // Закомментируем, если не используется

@Composable
fun SettingsProfileScreen(
    navController: NavController,
    onThemeUpdated: () -> Unit,
    // Удаляем viewModel: ProfileScreenVM = viewModel(), если он больше не нужен
    authViewModel: AuthVM // Получаем AuthVM
) {
    // Подписываемся на состояния из AuthVM
    // Используем collectAsStateWithLifecycle для безопасности в Compose
    val userInfo by authViewModel.userInfo.collectAsStateWithLifecycle()
    val isLoading by authViewModel.isLoadingUserInfo.collectAsStateWithLifecycle()
    val error by authViewModel.userInfoError.collectAsStateWithLifecycle()

    // Получаем контекст для возможных Toast
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 22.dp, end = 22.dp)
    ) {

        // --- Отображение информации о пользователе или загрузки/ошибки ---
        Surface( // Используем Surface для группировки и формы
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            shape = RoundedCornerShape(30.dp),
            tonalElevation = 1.dp, // Небольшая тень для выделения
            onClick = {
                // Можно перейти на полный профиль или редактирование, если нужно
                // navController.navigate(GraphRoute.PROFILE)
            }
        ) {
            // Внутри Surface показываем либо контент, либо загрузку, либо ошибку
            Box( // Используем Box для центрирования загрузки/ошибки
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 95.dp) // Примерная высота, как у Row с картинкой
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                contentAlignment = Alignment.CenterStart // Выравнивание по умолчанию
            ) {
                when {
                    isLoading -> {
                        // Показываем индикатор загрузки по центру
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    error != null -> {
                        // Показываем сообщение об ошибке
                        Text(
                            text = "Ошибка: $error",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                                .padding(horizontal = 10.dp) // Отступы для ошибки
                        )
                        // Можно добавить кнопку "Повторить"
                        // Button(onClick = { authViewModel.fetchUserInfo(authViewModel.sessionToken.value) }) { Text("Повторить") }
                    }
                    userInfo != null -> {
                        // Показываем информацию о пользователе, если она загружена
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Аватарка (можно использовать дефолтную или плейсхолдер)
                            AsyncImage(
                                model = null, // Замените на реальный URL аватара, если он есть в UserInfoResponse
                                // model = userInfo.avatarUrl, // Пример
                                error = painterResource(id = R.drawable.profile), // Плейсхолдер/ошибка
                                placeholder = painterResource(id = R.drawable.profile), // Плейсхолдер
                                contentDescription = "Avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    // .padding(top = 10.dp, bottom = 10.dp, start = 15.dp) // Убрали, т.к. отступы у Box
                                    .size(65.dp)
                                    .clip(CircleShape)
                            )

                            // --- Используем sessionCodeForDisplay из userInfo ---
                            userInfo?.sessionCodeForDisplay?.let { sessionCode ->
                                Column(modifier = Modifier.padding(start = 20.dp)) { // Используем Column для возможного добавления роли/имени
                                    AutoresizedText(
                                        text = sessionCode, // Отображаем код сессии
                                        style = MaterialTheme.typography.labelLarge // Сделаем покрупнее
                                    )
                                    // Можно добавить другие данные, например, роль:
                                    userInfo?.role?.let { role ->
                                        Text(
                                            text = role,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            } ?: run {
                                // Если sessionCodeForDisplay == null, но userInfo не null
                                Text(
                                    "Нет кода сессии",
                                    modifier = Modifier.padding(start = 50.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                    else -> {
                        // Состояние, когда не загрузка, не ошибка, но и данных нет (маловероятно при правильной логике)
                        Text("Нет данных пользователя", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        } // Конец Surface с информацией пользователя

        // Кнопка добавления фото (если она относится к профилю)
        // Возможно, ее стоит разместить внутри Row с информацией пользователя?
        /* IconButton(
             onClick = { navController.navigate(NavigationItems.CameraInProfile.route) },
             modifier = Modifier
                 .padding(end = 15.dp)
                 .size(35.dp)
         ) {
             Icon(
                 painter = painterResource(R.drawable.add_photo),
                 "Add_photo",
             )
         }*/

        // --- Меню настроек (остается без изменений) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp)
                .clip(RoundedCornerShape(15)) // Можно заменить на Surface с elevation
        ) {
            // ... (код для menuItems и MenuButton остается прежним)
            val menuItems = listOf(
                MenuItem(R.drawable.theme, "Theme", stringResource(R.string.theme)),
                MenuItem(R.drawable.profile_settings,"Profile Settings", stringResource(R.string.profile_settings)),
                MenuItem(R.drawable.notification,"Notification", stringResource(R.string.notification)),
                MenuItem(R.drawable.safety, "Security", stringResource(R.string.security)),
                MenuItem(R.drawable.language, "Language", stringResource(R.string.language)),
                MenuItem(R.drawable.faq, "FAQ", stringResource(R.string.faq)),
                // --- НОВОЕ: Кнопка Выход ---
                MenuItem(R.drawable.exit, "Logout", stringResource(R.string.logout)),
            )

            menuItems.forEach { menuItem ->
                MenuButton(
                    iconId = menuItem.iconId,
                    contentDescription = menuItem.contentDescription,
                    text = menuItem.text,
                    onThemeUpdated = onThemeUpdated, // Передаем лямбду для темы
                    navController = navController,
                    authViewModel = authViewModel // Передаем AuthVM для Logout
                )
            }
        }
    }
}

@Composable
fun MenuButton(
    iconId: Int,
    contentDescription: String,
    text: String,
    onThemeUpdated: () -> Unit, // Только для "Theme"
    navController: NavController,
    authViewModel: AuthVM // Нужен для Logout
) {
    Button(
        onClick = {
            when (contentDescription) {
                "Theme" -> onThemeUpdated() // Вызываем лямбду
                "Profile Settings" -> navController.navigate(SettingsScreen.ProfileSettings.route)
                "Notification" -> navController.navigate(SettingsScreen.Notification.route)
                "Security" -> navController.navigate(SettingsScreen.Security.route)
                "Language" -> navController.navigate(SettingsScreen.Language.route)
                "FAQ" -> navController.navigate(SettingsScreen.FAQ.route)
                // --- НОВОЕ: Обработка Logout ---
                "Logout" -> {
                    authViewModel.logout() // Вызываем logout во ViewModel
                    // Переходим на экран логина (убедитесь, что AuthScreen.Login - правильный роут)
                    navController.navigate(AuthScreen.Login.route) {
                        // Очищаем backstack, чтобы пользователь не мог вернуться назад кнопкой "Назад"
                        popUpTo(GraphRoute.MAIN) { inclusive = true } // или popUpTo(0) { inclusive = true }
                        launchSingleTop = true // Избегаем создания нескольких экземпляров экрана логина
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0),
        // Добавим цвета для кнопки Logout
        colors = if (contentDescription == "Logout") ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        ) else ButtonDefaults.buttonColors() // Стандартные цвета для остальных кнопок
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .padding(start = 25.dp)
                    .size(35.dp)
            )
            AutoresizedText(
                text = text,
                modifier = Modifier.padding(start = 20.dp),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}



