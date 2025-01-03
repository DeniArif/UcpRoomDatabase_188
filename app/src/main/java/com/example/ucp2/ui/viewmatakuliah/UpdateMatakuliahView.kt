package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.cotumwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.UpdateMatakuliahViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateMtkView(
  onBack: () -> Unit,
  onNavigate: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: UpdateMatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
  val uiState = viewModel.updateUIState
  val snackbarHostState = remember { SnackbarHostState() }
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(uiState.snackBarMessage) {
    uiState.snackBarMessage?.let { message ->
      coroutineScope.launch {
        snackbarHostState.showSnackbar(
          message = message,
          duration = SnackbarDuration.Long
        )
        viewModel.resetSnackBarMessage()
      }
    }
  }

  Scaffold(
    modifier = modifier,
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    topBar = {
      TopAppBar(
        judul = "Edit Matakuliah",
        showBackButton = true,
        onBack = onBack,
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp)
    ) {
      InesrtBodyMatkul(
        uiState = uiState,
        onValueChange = { updateEvent ->
          viewModel.updateState(updateEvent)
        },
        onClick = {
          coroutineScope.launch {
            if (viewModel.validateFields()) {
              viewModel.updateData()
              delay(600)
              withContext(Dispatchers.Main) {
                onNavigate()
              }
            }
          }
        },
        dosenList = viewModel.dosenList
      )
    }
  }
}
