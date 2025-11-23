package com.example.myarsitekturmvvm.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myarsitekturmvvm.R
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.selection.selectable

@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun FormSiswa(
    pilihanJK: List<String>,
    onSubmitButtonClicked : (MutableList<String>) -> Unit,
    modifier: Modifier = Modifier
){
    var txtNama by rememberSaveable { mutableStateOf("") }
    var txtAlamat by rememberSaveable { mutableStateOf("") }
    var txtGender by rememberSaveable { mutableStateOf("") }

    Scaffold(modifier=Modifier,
        topBar = {
            TopAppBar(
                title = {Text(stringResource(id= R.string.home), color = Color.White)},
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700)
                )
            )
        }
    ) {isiRuang ->
        Column(
            // START PERBAIKAN:
            // 1. Hapus Alignment.CenterHorizontally.
            // 2. Tambahkan padding horizontal (misalnya 20.dp) pada Column utama.
            modifier = Modifier
                .padding(isiRuang)
                .fillMaxWidth()
                .padding(horizontal = 20.dp), // <-- Padding agar tidak menempel di tepi
            verticalArrangement = Arrangement.Top,
            // Hapus: horizontalAlignment = Alignment.CenterHorizontally
            // END PERBAIKAN
        ) {
            OutlinedTextField(
                value = txtNama,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                // START PERBAIKAN: Gunakan fillMaxWidth()
                modifier = Modifier
                    .fillMaxWidth() // <-- Mengisi lebar yang tersedia (setelah padding)
                    .padding(top = 20.dp),
                // END PERBAIKAN
                label = { Text(text = "Nama Lengkap") },
                onValueChange = {
                    txtNama = it
                }
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth(), // <-- Mengisi lebar yang tersedia
                thickness = 1.dp,
                color = Color.Blue
            )
            // START PERBAIKAN: Row untuk Radio Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center // <-- Gunakan Start agar merapat ke kiri
            ) {
                pilihanJK.forEach { item ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = txtGender == item,
                                onClick = {
                                    txtGender = item
                                }
                            )
                            .padding(end = 16.dp), // Beri jarak antara Radio Button
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = txtGender == item,
                            onClick = {
                                txtGender = item
                            }
                        )
                        Text(text = item)
                    }
                }
            }
            // END PERBAIKAN
            HorizontalDivider(modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(), // <-- Mengisi lebar yang tersedia
                thickness = 1.dp,
                color = Color.Red
            )
            OutlinedTextField(
                value = txtAlamat,
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                // START PERBAIKAN: Gunakan fillMaxWidth()
                modifier = Modifier
                    .fillMaxWidth(), // <-- Mengisi lebar yang tersedia
                // END PERBAIKAN
                label = { Text(text = "Alamat Lengkap") },
                onValueChange = {
                    txtAlamat = it
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                // START PERBAIKAN: Gunakan fillMaxWidth()
                modifier = Modifier.fillMaxWidth(),
                // END PERBAIKAN
                enabled = txtAlamat.isNotEmpty() && txtNama.isNotEmpty() && txtGender.isNotEmpty(),
                onClick = {
                    val finalData = mutableListOf(txtNama, txtGender, txtAlamat)
                    onSubmitButtonClicked(finalData)
                }
            ) {
                Text(text = "Submit")
            }
        }
    }
}
