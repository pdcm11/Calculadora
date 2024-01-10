package com.example.calculadora

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class CalculadoraViewModel: ViewModel() {

    var state by mutableStateOf(CalculadoraState())

    fun onAction(acao: Acao) {
        when(acao) {
            is Acao.Num -> enterNumero(acao.num)
            is Acao.Eliminar -> eliminar()
            is Acao.Limpar -> state = CalculadoraState()
            is Acao.Operacao -> enterOperacao(acao.operacao)
            is Acao.Decimal -> enterDecimal()
            is Acao.Calcular -> calcular()
        }
    }

    private fun enterOperacao(operacao: Operacoes) {
        if(state.n1.isNotBlank()) {
            state = state.copy(operacao = operacao)
        }
    }

    private fun calcular() {
        val n1 = state.n1.toDoubleOrNull()
        val n2 = state.n2.toDoubleOrNull()
        if(n1 != null && n2 != null) {
            val result = when(state.operacao) {
                is Operacoes.Somar -> n1 + n2
                is Operacoes.Subtrair -> n1 - n2
                is Operacoes.Multiplicar -> n1 * n2
                is Operacoes.Dividir -> n1 / n2
                null -> return
            }
            state = state.copy(
                n1 = result.toString().take(15),
                n2 = "",
                operacao = null
            )
        }
    }

    private fun eliminar() {
        when {
            state.n2.isNotBlank() -> state = state.copy(
                n2 = state.n2.dropLast(1)
            )
            state.operacao != null -> state = state.copy(
                operacao = null
            )
            state.n1.isNotBlank() -> state = state.copy(
                n1 = state.n1.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
        if(state.operacao == null && !state.n1.contains(".") && state.n1.isNotBlank()) {
            state = state.copy(
                n1 = state.n1 + "."
            )
            return
        } else if(!state.n2.contains(".") && state.n2.isNotBlank()) {
            state = state.copy(
                n2 = state.n2 + "."
            )
        }
    }

    private fun enterNumero(numero: Int) {
        if(state.operacao == null) {
            if(state.n1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                n1 = state.n1 + numero
            )
            return
        }
        if(state.n2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            n2 = state.n2 + numero
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}