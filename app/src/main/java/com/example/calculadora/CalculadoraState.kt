package com.example.calculadora

data class CalculadoraState(
    val n1: String = "",
    val n2: String = "",
    val operacao: Operacoes? = null
)