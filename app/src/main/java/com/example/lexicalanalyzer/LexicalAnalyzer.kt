package com.example.lexicalanalyzer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lexicalanalyzer.ui.theme.LexicalAnalyzerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LexicalAnalyzer(
    viewModel: AnalyzerViewModel = AnalyzerViewModel()
) {
    val (textValue, setTextValue) = remember {
        mutableStateOf("")
    }
    var title by remember {
        mutableStateOf("")
    }

    val outPutState by viewModel.outPut.collectAsState()

    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetContent = {
            OutPutBottomSheet(outPut = outPutState, title = title)
        },
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        BackHandler(sheetState.isVisible) {
            scope.launch {
                sheetState.animateTo(ModalBottomSheetValue.Hidden)
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {

            item {
                EditorTextField(textValue = textValue, setTextValue = setTextValue)
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), text = "Identifiers"
                    ) {
                        title = "Identifiers"
                        toggleSheet(scope, sheetState)
                        viewModel.extractIdentifiers(textValue)
                    }
                    ActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), text = "Operators"
                    ) {
                        title = "Operators"
                        toggleSheet(scope, sheetState)
                        viewModel.extractOperators(textValue)
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), text = "- Spaces"
                    ) {
                        title = "Spaced Removed"
                        toggleSheet(scope, sheetState)
                        viewModel.removeSpaces(textValue)
                    }
                    ActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), text = "- Comments"
                    ) {
                        title = "Comments Removed"
                        toggleSheet(scope, sheetState)
                        viewModel.removeComments(textValue)
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ActionButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f), text = "Keywords"
                    ) {
                        title = "Keywords"
                        toggleSheet(scope, sheetState)
                        viewModel.extractKeyWords(textValue)
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
fun toggleSheet(scope: CoroutineScope, sheetState: ModalBottomSheetState) {
    scope.launch {
        if (sheetState.isVisible) {
            sheetState.animateTo(ModalBottomSheetValue.Hidden)
        } else {
            sheetState.animateTo(ModalBottomSheetValue.Expanded)
        }
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LexicalAnalyzerPreview() {
    LexicalAnalyzerTheme {
        LexicalAnalyzer()
    }
}