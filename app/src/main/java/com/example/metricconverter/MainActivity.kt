package com.example.metricconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.metricconverter.ui.theme.MetricConverterTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MetricConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AppBar()
        Content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(text = "Metric Converter")
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content() {
    var isExpandedMetric by remember { mutableStateOf(false) }
    var isExpandedFrom by remember { mutableStateOf(false) }
    var isExpandedTo by remember { mutableStateOf(false) }

    var metric by remember { mutableStateOf("") }
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }

    var input by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }

    var result by remember { mutableStateOf("") }

    // ===== Kalkulasi =====
    // Panjang
    if (metric == "Panjang" && input.isNotEmpty()) {
        if (from == to) result = ""
        if (from == "Kilometer" && to == "Meter") result = (input.toDouble() * 1000).toString()
        if (from == "Kilometer" && to == "Centimeter") result = (input.toDouble() * 100000).toString()
        if (from == "Kilometer" && to == "Milimeter") result = (input.toDouble() * 1000000).toString()
        if (from == "Meter" && to == "Kilometer") result = (input.toDouble() / 1000).toString()
        if (from == "Meter" && to == "Centimeter") result = (input.toDouble() * 100).toString()
        if (from == "Meter" && to == "Milimeter") result = (input.toDouble() * 1000).toString()
        if (from == "Centimeter" && to == "Kilometer") result = (input.toDouble() / 100000).toString()
        if (from == "Centimeter" && to == "Meter") result = (input.toDouble() / 100).toString()
        if (from == "Centimeter" && to == "Milimeter") result = (input.toDouble() * 10).toString()
        if (from == "Milimeter" && to == "Kilometer") result = (input.toDouble() / 1000000).toString()
        if (from == "Milimeter" && to == "Meter") result = (input.toDouble() / 1000).toString()
        if (from == "Milimeter" && to == "Centimeter") result = (input.toDouble() / 10).toString()
    }
    // Waktu
    if (metric == "Waktu" && input.isNotEmpty()) {
        if (from == to) result = ""
        if (from == "Jam" && to == "Menit") result = (input.toDouble() * 60).toString()
        if (from == "Jam" && to == "Detik") result = (input.toDouble() * 3600).toString()
        if (from == "Menit" && to == "Jam") result = (input.toDouble() / 60).toString()
        if (from == "Menit" && to == "Detik") result = (input.toDouble() * 60).toString()
        if (from == "Detik" && to == "Jam") result = (input.toDouble() / 3600).toString()
        if (from == "Detik" && to == "Menit") result = (input.toDouble() / 60).toString()
    }
    // Suhu
    if (metric == "Suhu" && input.isNotEmpty()) {
        if (from == to) result = ""
        if (from == "Celcius" && to == "Fahrenheit") result = (input.toDouble() * (9 / 5) + 32).toString()
        if (from == "Fahrenheit" && to == "Celcius") result = ((input.toDouble() - 32) - (5 - 9)).toString()
    }

    Column(
        modifier = Modifier.padding(all = 16.dp)
    ) {
        Card (
            modifier =  Modifier.padding(bottom = 20.dp)
        ) {
            Card (
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Aplikasi ini dibuat oleh : ", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
                Text(text = "Nama : Mohalim Rizal Kadamong")
                Text(text = "NIM : 210211060138")
            }
        }

        /*
        *
        * ===== METRIK =====
        *
        */
        Text(text = "Metrik :", modifier = Modifier.padding(bottom = 8.dp))
        ExposedDropdownMenuBox(
            expanded = isExpandedMetric,
            onExpandedChange = { newValue ->
                isExpandedMetric = newValue
            },
            modifier = Modifier.padding(bottom = 18.dp)
        ) {
            TextField(
                value = metric,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedMetric)
                },
                placeholder = {
                    Text(text = "Pilih Metrik")
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpandedMetric,
                onDismissRequest = {
                    isExpandedMetric = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = "Panjang")
                    },
                    onClick = {
                        metric = "Panjang"
                        isExpandedMetric = false
                        from = ""
                        to = ""
                        input = ""
                        result = ""
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Waktu")
                    },
                    onClick = {
                        metric = "Waktu"
                        isExpandedMetric = false
                        from = ""
                        to = ""
                        input = ""
                        result = ""
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(text = "Suhu")
                    },
                    onClick = {
                        metric = "Suhu"
                        isExpandedMetric = false
                        from = ""
                        to = ""
                        input = ""
                        result = ""
                    }
                )
            }
        }

        /*
        *
        * ===== DARI =====
        *
        */
        Text(text = "Dari :", modifier = Modifier.padding(bottom = 8.dp))
        ExposedDropdownMenuBox(
            expanded = isExpandedFrom,
            onExpandedChange = { newValue ->
                isExpandedFrom = newValue
            },
            modifier = Modifier.padding(bottom = 18.dp)
        ) {
            TextField(
                value = from,
                onValueChange = {},
                readOnly = true,
                enabled = metric.isNotEmpty(),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedFrom)
                },
                placeholder = {
                    Text(text = "Pilih Satuan")
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpandedFrom,
                onDismissRequest = {
                    isExpandedFrom = false
                }
            ) {
                when (metric) {
                    /*
                    *
                    * ===== PANJANG =====
                    *
                    */
                    "Panjang" -> {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Kilometer")
                            },
                            onClick = {
                                from = "Kilometer"
                                isExpandedFrom = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Meter")
                            },
                            onClick = {
                                from = "Meter"
                                isExpandedFrom = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Centimeter")
                            },
                            onClick = {
                                from = "Centimeter"
                                isExpandedFrom = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Milimeter")
                            },
                            onClick = {
                                from = "Milimeter"
                                isExpandedFrom = false
                            }
                        )
                    }

                    /*
                    *
                    * ===== WAKTU =====
                    *
                    */
                    "Waktu" -> {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Jam")
                            },
                            onClick = {
                                from = "Jam"
                                isExpandedFrom = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Menit")
                            },
                            onClick = {
                                from = "Menit"
                                isExpandedFrom = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Detik")
                            },
                            onClick = {
                                from = "Detik"
                                isExpandedFrom = false
                            }
                        )
                    }

                    /*
                    *
                    * ===== SUHU =====
                    *
                    */
                    "Suhu" -> {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Celcius")
                            },
                            onClick = {
                                from = "Celcius"
                                isExpandedFrom = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Fahrenheit")
                            },
                            onClick = {
                                from = "Fahrenheit"
                                isExpandedFrom = false
                            }
                        )
                    }
                }
            }
        }

        /*
        *
        * ===== KE =====
        *
        */
        Text(text = "Ke :", modifier = Modifier.padding(bottom = 8.dp))
        ExposedDropdownMenuBox(
            expanded = isExpandedTo,
            onExpandedChange = { newValue ->
                isExpandedTo = newValue
            },
            modifier = Modifier.padding(bottom = 18.dp)
        ) {
            TextField(
                value = to,
                onValueChange = {},
                readOnly = true,
                enabled = metric.isNotEmpty(),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpandedTo)
                },
                placeholder = {
                    Text(text = "Pilih Satuan")
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpandedTo,
                onDismissRequest = {
                    isExpandedTo = false
                }
            ) {
                when (metric) {
                    /*
                    *
                    * ===== PANJANG =====
                    *
                    */
                    "Panjang" -> {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Kilometer")
                            },
                            onClick = {
                                to = "Kilometer"
                                isExpandedTo = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Meter")
                            },
                            onClick = {
                                to = "Meter"
                                isExpandedTo = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Centimeter")
                            },
                            onClick = {
                                to = "Centimeter"
                                isExpandedTo = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Milimeter")
                            },
                            onClick = {
                                to = "Milimeter"
                                isExpandedTo = false
                            }
                        )
                    }

                    /*
                    *
                    * ===== WAKTU =====
                    *
                    */
                    "Waktu" -> {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Jam")
                            },
                            onClick = {
                                to = "Jam"
                                isExpandedTo = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Menit")
                            },
                            onClick = {
                                to = "Menit"
                                isExpandedTo = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Detik")
                            },
                            onClick = {
                                to = "Detik"
                                isExpandedTo = false
                            }
                        )

                    /*
                    *
                    * ===== SUHU =====
                    *
                    */
                    }
                    "Suhu" -> {
                        DropdownMenuItem(
                            text = {
                                Text(text = "Celcius")
                            },
                            onClick = {
                                to = "Celcius"
                                isExpandedTo = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Fahrenheit")
                            },
                            onClick = {
                                to = "Fahrenheit"
                                isExpandedTo = false
                            }
                        )
                    }
                }
            }
        }

        /*
        *
        * ===== INPUT =====
        *
        */
        val pattern = remember { Regex("^-?([0]{1}\\.{1}[0-9]+|[1-9]{1}[0-9]*\\.{1}[0-9]+|[0-9]+|0|-?\\d+)\$") }
        Text(text = "Nilai :", modifier = Modifier.padding(bottom = 8.dp))
        TextField(
            value = input,
            onValueChange = {
                input = it
                isValid = it.matches(pattern) || it.isEmpty()
            },
            singleLine = true,
            maxLines = 1,
            isError = !isValid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "Masukkan nilai") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        if (!isValid) Text(text = "Nilai harus berupa angka.", color = Color.Red)

        /*
        *
        * ===== HASIL =====
        *
        */
        Column (
            modifier = Modifier.padding(bottom = 13.dp, top = 18.dp)
        ) {
            if (input.isNotEmpty()) {
                if (result.isNotEmpty()) {
                    Text(text = "Hasil : $result $to")
                } else {
                    Text(text = "Hasil :")
                }
            } else {
                Text(text = "Hasil :")
            }
        }
    }
}