package com.example.calculadora

sealed class Acao {
    data class Num(val num: Int): Acao()
    object Limpar: Acao()
    object Eliminar: Acao()
    data class Operacao(val operacao: Operacoes): Acao()
    object Calcular: Acao()
    object Decimal: Acao()
}