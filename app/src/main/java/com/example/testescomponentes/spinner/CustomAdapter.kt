package com.example.testescomponentes.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.testescomponentes.R

//Quando temos um Spinner customizado, devemos criar uma instância customizada de Adapter que herda de BaseAdapter
class CustomAdapter(internal var context: Context, internal var imagensFrutas: IntArray, internal var nomeFrutas: Array<String>) : BaseAdapter() {//Aqui estamos recebendo como parâmetro o contexto, junto com as imagens e o nome das frutas

    internal var inflter: LayoutInflater//Cria o atributo que será necessário para Inflar o arquivo 'spinner_custom_layout.xml' dentro do elemento Spinner existente na 'activity_main.xml'

    init {
        //Bloco de código que é executado quando a classe 'CustomAdapter' é instanciada
        inflter = LayoutInflater.from(context)//A primeira coisa que a gente faz é inflar o Layout com o contexto de MainActivity
    }

    //Retorna o número total de itens que devem ser carregados no Spinner
    override fun getCount(): Int {
        return imagensFrutas.size//Poderiamos usar tanto nomesFrutas.size quanto imagensFrutas.size que não alteraria em nada pois ambos possuem o mesmo tamanho
    }

    //Método que retorna o nome da fruta. Este método foi feito para ser usado fora da classe.
    override fun getItem(i: Int): Any? {
        return nomeFrutas[i]
    }

    //Método usado para retornar o id do item selecionado.
    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    //Para que usar os métodos getItem e getItemId se já não conseguimos fazer isso por meio do onItemSelected existente na MainActivity.kt?

    /*
    Resposta de um usuário do Stackoverflow: A finalidade ou uso do método depende principalmente do desenvolvedor e não está vinculado a um aplicativo baseado em banco de dados. use-o a seu favor para criar código claro/legível/reutilizável.
    Ou seja, esses dois métodos atuam como funcionalidades opcionais que podemos usar caso queiramos uma outra maneira de selecionar um item ou um id.
    */

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {

        //É importante ressaltar que a função getView atua neste momento como uma especie de LOOP, e o número de laços será definido pelo retorno do método getCount.
        //E é dessa forma que ele vai inflando e preenchendo os elementos do 'spinner_custom_layout.xml' com seus respectivos valores.

        val view = inflter.inflate(R.layout.spinner_custom_layout,null)//Primeiro ele infla o layout do arquivo 'spinner_custom_layout.xml'.

        val icon = view.findViewById<View>(R.id.imageView) as ImageView?//Em seguida acessamos o elemento do tipo imagem.

        val names = view.findViewById<View>(R.id.textView) as TextView?//Em seguida acessamos o elemento do tipo texto.

        icon!!.setImageResource(imagensFrutas[i])//Aqui estamos setando a imagem existente no arquivo inflado com aquela que se encontra no nosso array de imagens.

        names!!.text = nomeFrutas[i]//Aqui estamos setando o texto do arquivo inflado com aquela que se encontra no nosso array de nomes.

        //Para saber mais como funciona as duplas exclamações acesse: https://micilini.com/conteudos/android/nullsafety-e-excecoes

        return view//Retorna a View criada
    }

}