package com.example.lexicalanalyzer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lexicalanalyzer.ui.theme.LexicalAnalyzerTheme
import com.example.lexicalanalyzer.ui.theme.mint
import com.example.lexicalanalyzer.ui.theme.sailorBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorTextField(textValue: String, setTextValue: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .border(1.dp, Color.DarkGray, RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .height(480.dp),
        value = textValue,
        onValueChange = setTextValue,
        placeholder = {
            Text(
                text = "Type code here...",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String, onClicked: () -> Unit) {
    Button(
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = sailorBlue,
            contentColor = mint
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun OutPutBottomSheet(outPut: List<String>, title: String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = sailorBlue
            )
        }
        items(items = outPut) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(0.5.dp, Color.Gray)
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = item,
                    fontSize = 20.sp,
                    color = sailorBlue
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SheetPreview() {
    LexicalAnalyzerTheme {
        OutPutBottomSheet(outPut = listOf("int", "main", "this", "void"), title = "KeyWords")
    }
}

@Preview(showBackground = true)
@Composable
private fun ActionButtonPreview() {
    LexicalAnalyzerTheme {
        ActionButton(text = "Identifiers") {}
    }
}