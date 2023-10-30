package com.example.horoscapp.ui.palmistry

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.example.horoscapp.R
import com.example.horoscapp.ui.theme.accent
import com.example.horoscapp.ui.theme.black_alpha
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun PalmistryScreen(
    modifier: Modifier = Modifier,
    backGroundColor: Color
) {
    if (permissionGranted()) {
        //Show UI
        OpenCamera()
//        Column(
//            modifier
//                .background(backGroundColor)
//                .fillMaxSize()
//        ) {
//
//        }
    } else {
        RequireCameraPermission(
            modifier
                .fillMaxSize()
                .background(backGroundColor)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OpenCamera() {

    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController =
        remember { LifecycleCameraController(context) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {paddingValues ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize().padding(paddingValues),
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also { previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)
                }
            }
        )
        Image(
            painter = painterResource(id = R.drawable.palmistry),
            contentDescription = "Palmistry photo",
            modifier = Modifier.fillMaxSize().background(black_alpha).padding(24.dp)
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun permissionGranted(): Boolean =
    rememberPermissionState(android.Manifest.permission.CAMERA).status.isGranted

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun RequireCameraPermission(modifier: Modifier) {
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(id = R.string.permission_request),
            color = accent,
            modifier = Modifier.padding(24.dp)
        )
        Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
            Text(stringResource(R.string.grant_permission))
        }
    }
}
