package aleksandr.fedotkin.buyercryptomoney.presentation.ui.screens.main

import aleksandr.fedotkin.buyercryptomoney.presentation.ui.navigation.Navigation
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen() {

    Permission()

    Navigation()
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun Permission() {

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("LOG_TAG", "PERMISSION GRANTED")
        } else {
            Log.d("LOG_TAG", "PERMISSION DENIED")
        }
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) -> {
                Log.d("ExampleScreen", "Code requires permission")
            }
            else -> launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
