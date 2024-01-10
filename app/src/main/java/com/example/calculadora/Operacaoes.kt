package com.example.calculadora

sealed class Operacoes(val symbol: String) {
    object Somar: Operacoes("+")
    object Subtrair: Operacoes("-")
    object Multiplicar: Operacoes("x")
    object Dividir: Operacoes("/")
}