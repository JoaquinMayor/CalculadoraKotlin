package com.example.calculadorakotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorakotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var calculadora = Calculadora()

        binding.total.apply {
            this.text = ""
        }

        val rootLayout = binding.main
        var new = true
        for(i in 0 until rootLayout.childCount){
            var child = rootLayout.getChildAt(i)
            if(child is TextView){

                child.setOnClickListener {
                    when(child.tag){
                        "C"->{
                            new = true
                            calculadora.reinicio()
                            binding.total.text = ""
                        }
                        "borrar"->{
                            binding.total.text = binding.total.text.removeRange(binding.total.text.length-1,binding.total.text.length)
                        }
                        "igual"->{
                            var text = binding.total.text
                            var number = ""
                            var numbers: MutableList<String> = mutableListOf()
                            var signs: MutableList<String> = mutableListOf()

                            for(i in 0 until text.length){
                                if(text.get(i).isDigit() || text.get(i) == '.'){
                                    number += text[i]
                                }else if(text[i] != ' '){
                                    numbers.add(number)
                                    signs.add(text[i].toString())
                                    number = ""
                                }
                            }
                            numbers.add(number)
                            var isFirst = true
                            var signPos = 0
                            for(i in 0 until numbers.size){
                                var numberpos = i
                                if(!new){
                                  numberpos++
                                }
                                if(numberpos>=numbers.size){
                                    break
                                }
                                var number = numbers.get(numberpos).toFloat()

                                var sign = ""
                                if(new && isFirst){
                                    sign = "+"
                                    isFirst = false
                                }else if(i<=signs.size){
                                    sign = signs.get(signPos)
                                    signPos++
                                }

                                when(sign){
                                    "+" -> calculadora.suma(number)
                                    "-" -> calculadora.resta(number)
                                    "X" -> calculadora.multiplicacion(number)
                                    "÷" -> calculadora.divicion(number)
                                }
                            }
                            if(new){
                                new =false
                            }

                            binding.total.text = calculadora.getTotal().toString()
                        }else -> {
                            var childText = child.text.toString()
                            var text = binding.total.text.toString()

                            if(child.tag.toString().isDigitsOnly() || child.tag.toString() == "coma"){
                                binding.total.text = text+childText
                            }else{
                                binding.total.text = " $text $childText "
                            }

                        }
                }

                }
            }
        }

    }



}