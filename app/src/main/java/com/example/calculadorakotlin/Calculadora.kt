package com.example.calculadorakotlin

class Calculadora (){

    private var total:Float = 0F

    fun suma(num1:Float){
        this.total += num1
    }

    fun resta(num1:Float) {
        this.total -= num1
    }

    fun divicion(num1: Float){
        this.total /= num1
    }

    fun multiplicacion(num1: Float){
        this.total*=num1
    }

    fun reinicio(){
        this.total = 0F;
    }

    fun getTotal():Float{
        return this.total
    }

}