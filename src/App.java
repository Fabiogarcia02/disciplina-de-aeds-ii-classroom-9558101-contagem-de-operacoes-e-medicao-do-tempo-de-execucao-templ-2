import java.util.Random;
import java.util.function.Consumer;

/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {

    static final int[] TAMANHOS_TESTE_GRANDE = {
        31_250_000,
        62_500_000,
        125_000_000,
        250_000_000,
        500_000_000
    };

    static final int[] TAMANHOS_TESTE_MEDIO = {
        12_500,
        25_000,
        50_000,
        100_000,
        200_000
    };

    static final int[] TAMANHOS_TESTE_PEQUENO = {
        3,
        6,
        12,
        24,
        48
    };

    static final double NANO_TO_MILLI = 1.0 / 1_000_000;

    static Random aleatorio = new Random(42);

    static long operacoes;

    /**
     * Código de teste 1.
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;

        for (int i = 0; i < vetor.length; i += 2) {
            resposta += vetor[i] % 2;
            operacoes++;
        }

        return resposta;
    }

    /**
     * Código de teste 2.
     */
    static int codigo2(int[] vetor) {
        int contador = 0;

        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Código de teste 3.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {

            int menor = i;

            for (int j = i + 1; j < vetor.length; j++) {

                if (vetor[j] < vetor[menor])
                    menor = j;
            }

            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo).
     */
    static int codigo4(int n) {

        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    /**
     * Bubble Sort.
     */
    static int[] bubblesort(int[] vetor) {

        int valorref = vetor.length - 1;

        operacoes = 0;

        for (int i = valorref; i > 0; i--) {

            for (int j = 0; j < i; j++) {

                operacoes++;

                if (vetor[j] > vetor[j + 1]) {

                    int comparador = vetor[j];

                    vetor[j] = vetor[j + 1];

                    vetor[j + 1] = comparador;

                    operacoes++;
                }
            }
        }

        return vetor;
    }

    /**
     * Gerador de vetores aleatórios.
     */
    static int[] gerarVetor(int tamanho) {

        int[] vetor = new int[tamanho];

        for (int i = 0; i < tamanho; i++) {

            vetor[i] = aleatorio.nextInt(1, tamanho / 2);
        }

        return vetor;
    }

    //Inserção
     
        static  int [] insertionSort(int[] vetor) {
            for(int i = 1; i < vetor.length; i++){
                 int referencia = vetor[i];

                for( int j = i-j; j >= 0 &&  referencia< vetor[j]; j--){
                     vetor[j+1]= vetor[j];
                }
            }

             return vetor;
        }
        
         
          

    /**
     * Método responsável por testar uma função em vários tamanhos de vetor.
     */
    public static void testavetores(
            int[] tamanhosVetores,
            Consumer<int[]> funcao) {

        for (int tamanho : tamanhosVetores) {

            int[] vetor = gerarVetor(tamanho);

            operacoes = 0;

            long inicio = System.nanoTime();

            funcao.accept(vetor);

            long termino = System.nanoTime();

            double duracao =
                    (double) (termino - inicio) * NANO_TO_MILLI;

            System.out.println(
                    "Tamanho do vetor: "
                    + tamanho
                    + ", Operações: "
                    + operacoes
                    + ", Tempo: "
                    + duracao
                    + " ms"
            );
        }
    }

    public static void main(String[] args) {

        int opcao = 4;

        if (opcao == 1) {

            System.out.println("\n===== CÓDIGO 1 =====");

            testavetores(
                    TAMANHOS_TESTE_GRANDE,
                    vetor -> codigo1(vetor)
            );

        } else if (opcao == 2) {

            System.out.println("\n===== CÓDIGO 2 =====");

            testavetores(
                    TAMANHOS_TESTE_GRANDE,
                    vetor -> codigo2(vetor)
            );

        } else if (opcao == 3) {

            System.out.println("\n===== CÓDIGO 3 =====");

            testavetores(
                    TAMANHOS_TESTE_MEDIO,
                    vetor -> codigo3(vetor)
            );

        } else if (opcao == 4) {

            System.out.println("\n===== BUBBLE SORT =====");

            testavetores(
                    TAMANHOS_TESTE_PEQUENO,
                    vetor -> bubblesort(vetor)
            );
        }

        else if (opcao == 5) {

            System.out.println("\n===== INSERTION SORT =====");

            testavetores(
                    TAMANHOS_TESTE_PEQUENO,
                    vetor -> insertionSort(vetor)
            );
        }
    }
}

