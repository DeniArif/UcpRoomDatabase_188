package com.example.ucp2.ui.viewmatakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.FormErrorStateMatkul
import com.example.ucp2.ui.viewmodel.MatakuliahEvent
import com.example.ucp2.ui.viewmodel.MatakuliahViewModel
import com.example.ucp2.ui.viewmodel.MtkUIState
import com.example.ucp2.ui.viewmodel.PenyediaViewModel

@Composable
fun InsertMatakuliahView(
  onBack: () -> Unit,
  onNavigate: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: MatakuliahViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}

@Composable
fun InesrtBodyMatkul(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MtkUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormMatakuliah(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isentryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormMatakuliah(
  matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
  onValueChange: (MatakuliahEvent) -> Unit,
  errorState: FormErrorStateMatkul = FormErrorStateMatkul(),
  modifier: Modifier = Modifier
){
    val sks = listOf("1","2","3")
    val semester = listOf("1","2","3","4","5","6","7","8")

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.nama,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(nama = it))
            },
            label = { Text("nama")},
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan nama")}
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kode,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kode = it))
            },
            label = { Text("kode")},
            isError = errorState.kode !=null,
            placeholder = { Text("Masukkan kode")}
        )
        Text(
            text = errorState.kode ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "SKS")
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            semester.forEach{ sks ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.sks == sks,
                        onClick =  {
                            onValueChange(matakuliahEvent.copy(sks = sks))
                        },
                    )
                    Text(
                        text = sks,
                    )
                }
            }
        }
        Text(
            text = errorState.sks ?:" ",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Semester")
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            semester.forEach{ smt ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.semester == smt,
                        onClick =  {
                            onValueChange(matakuliahEvent.copy(semester = smt))
                        },
                    )
                    Text(
                        text = smt,
                    )
                }
            }
        }
        Text(
            text = errorState.semester ?:" ",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.jenis,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(jenis = it))
            },
            label = { Text("Jenis")},
            isError = errorState.jenis !=null,
            placeholder = { Text("Masukkan Jenis")}
        )
        Text(
            text = errorState.jenis ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.dosenpengampu,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(dosenpengampu = it))
            },
            label = { Text("Dosen Pengampu")},
            isError = errorState.nama !=null,
            placeholder = { Text("Masukkan Dosen Pengampu")}
        )
        Text(
            text = errorState.dosenpengampu ?: "",
            color = Color.Red
        )
    }

}