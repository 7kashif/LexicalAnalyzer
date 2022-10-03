package com.example.lexicalanalyzer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnalyzerViewModel : ViewModel() {
    private val _outPut = MutableStateFlow(listOf(""))
    val outPut = _outPut.asStateFlow()


    fun extractKeyWords(inputString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newString = removeCommentsFromInputString(inputString)
            _outPut.emit(
                newString.split(" ", "\n").filter {
                    keywords.contains(it)
                }
            )
        }
    }

    fun extractOperators(inputString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newString = removeCommentsFromInputString(inputString)
            _outPut.emit(
                newString.split(" ", "\n").filter {
                    operators.contains(it)
                }
            )
        }
    }

    fun removeSpaces(inputString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _outPut.emit(
                listOf(inputString.replace("\\s".toRegex(), ""))
            )
        }
    }

    fun removeComments(inputString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _outPut.emit(
                listOf(
                    removeCommentsFromInputString(inputString)
                )
            )
        }
    }

    fun extractIdentifiers(inputString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newString = removeCommentsFromInputString(inputString)
            _outPut.emit(
                newString.split(" ", "\n").filter {
                    (!keywords.contains(it) && !operators.contains(it) && it.isNotEmpty())
                }
            )
        }
    }

    private fun removeCommentsFromInputString(inputString: String) =
        inputString.replace("//[^\\r\\n]*".toRegex(), "").replace(
            "(\\\"[^\\\"]*\\\"(?!\\\\))|(//[^\\n]*\$|/(?!\\\\)\\*[\\s\\S]*?\\*(?!\\\\)/)".toRegex(),
            ""
        )

}