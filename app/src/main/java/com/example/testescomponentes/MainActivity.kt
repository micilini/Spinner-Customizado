package com.example.testescomponentes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.example.testescomponentes.spinner.CustomAdapter


class MainActivity : AppCompatActivity() {

    internal var nomeFrutas = arrayOf("Uvas", "Manga", "Abacaxi", "Morango")
    internal var imagensFrutas = intArrayOf(R.drawable.uva, R.drawable.manga, R.drawable.abacaxi, R.drawable.morango)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        criaSpinnerPersonalizado()//Método que chama os comandos capazes de personalizar o Spinner com Layout de Imagens + Texto
    }

    fun criaSpinnerPersonalizado(){

        //Seleciona a instancia de spinner existente dentro da nossa view (activity_main.xml)
        val spinner = findViewById<View>(R.id.spinner) as Spinner

        //Adiciona o método capaz de analisar todas as modificações que o usuário faz no Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //Quando o usuário seleciona uma determinada opção, é mostrado uma mensagem alegando a opção que ele escolheu
                Toast.makeText(
                    this@MainActivity,
                    "Você selecionou a posição: " + position + ", cujo valor é: " + nomeFrutas[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //Quando o usuário não seleciona nenhuma opção, é mostrada uma outra mensagem
                Toast.makeText(
                    this@MainActivity,
                    "Você não selecionou nenhuma opção",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        //Cria uma instância de CustomAdapter, enviando o contexto da aplicação principal juntamente com os atributos que armazenam os textos e o caminho das imagens
        val customAdapter = CustomAdapter(applicationContext, imagensFrutas, nomeFrutas)

        spinner.adapter = customAdapter//Faz a cola do Adapter com o Spinner setado na nossa view
    }

}