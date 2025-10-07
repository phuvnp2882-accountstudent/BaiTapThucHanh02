package com.example.baitapthuchanh02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baitapthuchanh02.ui.theme.BaiTapThucHanh02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapThucHanh02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NumberScreen()
                }
            }
        }
    }
}

@Composable
fun NumberScreen() {
    var input by remember { mutableStateOf("") }
    var list by remember { mutableStateOf(listOf<Int>()) }
    var error by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Thực hành 02", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Nhập vào số lượng") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                try {
                    val n = input.toInt()
                    if (n > 0) {
                        list = (1..n).toList()
                        error = ""
                    } else {
                        list = emptyList()
                        error = "Vui lòng nhập vào số nguyên dương!"
                    }
                } catch (_: Exception) {
                    list = emptyList()
                    error = "Dữ liệu bạn nhập không hợp lệ"
                }
            }) {
                Text("Tạo")
            }
        }

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        list.forEach { number ->
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(number.toString(), color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumberScreen() {
    BaiTapThucHanh02Theme {
        NumberScreen()
    }
}
