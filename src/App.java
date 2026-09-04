import java.util.Random;

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
    static final int[] TAMANHOS_TESTE_GRANDE =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] TAMANHOS_TESTE_MEDIO =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] TAMANHOS_TESTE_PEQUENO = {          3,          6,          12,          24,          48 };
    static final double NANO_TO_MILLI = 1.0/1_000_000;
    static Random aleatorio = new Random(42);
    static long operacoes;
    
    /**
     * Código de teste 1. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
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
     * Código de teste 2. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
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
     * Código de teste 3. Este método...
     * @param vetor Vetor com dados para teste.
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
     * Código de teste 4 (recursivo). Este método...
     * @param n Ponto inicial do algoritmo
     * @return Um inteiro que significa...
     */
    static int codigo4(int n) {
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

  static int[] bubblesort(int[] vetor) {
    int valorref = vetor.length - 1;
    operacoes = 0;

    for (int i = valorref; i > 0; i--) {

        for (int j = 0; j < i; j++) {

            operacoes++; // conta a comparação

            if (vetor[j] > vetor[j + 1]) {
                int comparador = vetor[j];
                vetor[j] = vetor[j + 1];
                vetor[j + 1] = comparador;

                operacoes++; // conta a troca
            }
        }
    }

    return vetor;
}











    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;      
    }
    
    public static void main(String[] args) {
        

        System.out.println("gerar vetor ");
        System.out.println("codigo 1 digete 1 e dois digite o 2");
        int opcao=3;
        long inicio,termino;
        double duracao;
        if(opcao==1){
        for(int i=0;i<TAMANHOS_TESTE_GRANDE.length;i++){
            int[]novo=  gerarVetor(TAMANHOS_TESTE_GRANDE[i]);
            System.out.println("vetor grande "+i+" codigo1 ");
            operacoes=0;
            inicio=System.nanoTime();
            codigo1(novo);
            termino=System.nanoTime();
            duracao=(double)(termino-inicio)*NANO_TO_MILLI;
            System.out.println("tamanho do vetor : "+ novo.length);
            System.out.println("quantidade de operação : "+operacoes);
            System.out.println("tempo por operação : "+duracao);
        }
    }else if(opcao==2){
    for(int i=0;i<TAMANHOS_TESTE_GRANDE.length;i++){
            int[]novo=  gerarVetor(TAMANHOS_TESTE_GRANDE[i]);
            System.out.println("vetor grande "+i+" codigo2 ");
            operacoes=0;
            inicio=System.nanoTime();
            codigo2(novo);
            termino=System.nanoTime();
            duracao=(double)(termino-inicio)*NANO_TO_MILLI;
            System.out.println("tamanho do vetor : "+ novo.length);
            System.out.println("quantidade de operação : "+operacoes);
            System.out.println("tempo por operação : "+duracao);
        }
    }else if(opcao==3){
        for(int i=0;i<TAMANHOS_TESTE_MEDIO.length;i++){
            int[]novo=  gerarVetor(TAMANHOS_TESTE_MEDIO[i]);
            System.out.println("vetor grande "+i+" codigo3 ");
            operacoes=0;
            inicio=System.nanoTime();
            codigo1(novo);
            termino=System.nanoTime();
            duracao=(double)(termino-inicio)*NANO_TO_MILLI;
            System.out.println("tamanho do vetor : "+ novo.length);
            System.out.println("quantidade de operação : "+operacoes);
            System.out.println("tempo por operação : "+duracao);
        }

    }else if (opcao == 4) {

    for (int i = 0; i < TAMANHOS_TESTE_PEQUENO.length; i++) {

        int[] novo = gerarVetor(TAMANHOS_TESTE_PEQUENO[i]);

        System.out.println("\n==============================");
        System.out.println("BUBBLE SORT");
        System.out.println("Tamanho do vetor: " + novo.length);

        System.out.print("Vetor antes: ");
        for (int j = 0; j < novo.length; j++) {
            System.out.print(novo[j] + " ");
        }

        inicio = System.nanoTime();

        bubblesort(novo);

        termino = System.nanoTime();

        duracao = (double) (termino - inicio) * NANO_TO_MILLI;

        System.out.print("\nVetor depois: ");
        for (int j = 0; j < novo.length; j++) {
            System.out.print(novo[j] + " ");
        }

        System.out.println("\nQuantidade de operações: " + operacoes);
        System.out.println("Tempo: " + duracao + " ms");
    }
}

Para testar


    
}
}
