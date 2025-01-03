package com.example.ucp2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Dosen
import com.example.ucp2.repository.RepositoryDosen
import kotlinx.coroutines.launch

class DosenViewModel(private val  repositoryDosen: RepositoryDosen) : ViewModel() {

    var uiState by mutableStateOf(DosenUIState())

    fun updateState(dosenEvent: DosenEvent) {
        uiState = uiState.copy(
            dosenEvent = dosenEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.dosenEvent
        val errorStateDosen = FormErrorStateDosen(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jeniskelamin = if (event.jeniskelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong"

        )
        uiState = uiState.copy(isEntryValid = errorStateDosen)
        return errorStateDosen.isValidDosen()
    }

    fun saveData() {
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryDosen.insertDosen(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorStateDosen()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Input tidak valid. Periksa kembali data anda"
                    )
                }
            }
            } else {
                uiState = uiState.copy(
                    snackBarMessage = "Input Tidak valid."
                )
            }

        }
        fun resetSnackBarMessage() {
            uiState = uiState.copy(snackBarMessage = null)
        }
    }





data class DosenUIState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorStateDosen = FormErrorStateDosen(),
    val snackBarMessage: String? = null
)

data class FormErrorStateDosen(
    val nidn: String? = null,
    val nama: String? = null,
    val jeniskelamin: String? = null
) {
    fun isValidDosen(): Boolean {
        return nidn == null && nama == null &&
                jeniskelamin == null
    }
}

data class DosenEvent(
    val nidn: String = " ",
    val nama: String = " ",
    val jeniskelamin: String = " "
)

fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jeniskelamin = jeniskelamin
)