import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class App {

	/** Nome do arquivo de dados. O arquivo deve estar localizado na raiz do projeto */
    static String nomeArquivoDados;
    
    /** Scanner para leitura de dados do teclado */
    static Scanner teclado;

    /** Vetor de produtos cadastrados */
    static Produto[] produtosCadastrados;

    /** Quantidade de produtos cadastrados atualmente no vetor */
    static int quantosProdutos = 0;

    static Bubblesort<Produto> ordenador;

    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /** Gera um efeito de pausa na CLI. Espera por um enter para continuar */
    static void pausa() {
        System.out.println("Digite enter para continuar...");
        teclado.nextLine();
    }

    /** Cabeçalho principal da CLI do sistema */
    static void cabecalho() {
        System.out.println("AEDs II COMÉRCIO DE COISINHAS");
        System.out.println("=============================");
    }
    
    static <T extends Number> T lerOpcao(String mensagem, Class<T> classe) {
        
    	T valor;
        
    	System.out.println(mensagem);
    	try {
            valor = classe.getConstructor(String.class).newInstance(teclado.nextLine());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
        		| InvocationTargetException | NoSuchMethodException | SecurityException e) {
            return null;
        }
        return valor;
    }
    
    /** Imprime o menu principal, lê a opção do usuário e a retorna (int).
     * Perceba que poderia haver uma melhor modularização com a criação de uma classe Menu.
     * @return Um inteiro com a opção do usuário.
    */
    static int menu() {
        cabecalho();
        System.out.println("1 - Procurar por um produto");
        System.out.println("2 - Ordenar produtos");
        System.out.println("3 - Embaralhar produtos");
        System.out.println("4 - Listar todos os produtos");
        System.out.println("0 - Finalizar");
        
        return lerOpcao("Digite sua opção: ", Integer.class);
    }
    
    /**
     * Lê os dados de um arquivo-texto e retorna um vetor de produtos. Arquivo-texto no formato
     * N  (quantidade de produtos) <br/>
     * tipo;descrição;preçoDeCusto;margemDeLucro;[dataDeValidade] <br/>
     * Deve haver uma linha para cada um dos produtos. Retorna um vetor vazio em caso de problemas com o arquivo.
     * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
     * @return Um vetor com os produtos carregados, ou vazio em caso de problemas de leitura.
     */
    static Produto[] lerProdutos(String nomeArquivoDados) {
    	
    	Scanner arquivo = null;
    	int numProdutos;
    	String linha;
    	Produto produto;
    	Produto[] produtosCadastrados;
    	
    	try {
    		arquivo = new Scanner(new File(nomeArquivoDados), Charset.forName("UTF-8"));
    		
    		numProdutos = Integer.parseInt(arquivo.nextLine());
    		produtosCadastrados = new Produto[numProdutos];
    		
    		for (int i = 0; i < numProdutos; i++) {
    			linha = arquivo.nextLine();
    			produto = Produto.criarDoTexto(linha);
    			produtosCadastrados[i] = produto;
    		}
    		quantosProdutos = numProdutos;
    		
    	} catch (IOException excecaoArquivo) {
    		produtosCadastrados = null;
    	} finally {
    		arquivo.close();
    	}
    	
    	return produtosCadastrados;
    }
    
    static Produto localizarProduto() {
        
    	Produto produto = null;
    	Boolean localizado = false;
    	
    	cabecalho();
    	System.out.println("Localizando um produto...");
        int idProduto = lerOpcao("Digite o identificador do produto desejado: ", Integer.class);
        for (int i = 0; (i < quantosProdutos && !localizado); i++) {
    		if (produtosCadastrados[i].hashCode() == idProduto) {
        		produto = produtosCadastrados[i];
        		localizado = true;
        	}
        }
        
        return produto;   
    }
    
    private static void mostrarProduto(Produto produto) {
    	
        cabecalho();
        String mensagem = "Dados inválidos para o produto!";
        
        if (produto != null){
            mensagem = String.format("Dados do produto:\n%s", produto);
        }
        
        System.out.println(mensagem);
    }
    
    static void ordenarProdutos(){
    	
        cabecalho();
        
        ordenador = new Bubblesort<>();

        produtosCadastrados = ordenador.ordenar(produtosCadastrados);        
        System.out.println("Tempo gasto com a ordenação dos produtos: " + ordenador.getTempoOrdenacao() + " ms.");
    }

    static void embaralharProdutos(){
        Collections.shuffle(Arrays.asList(produtosCadastrados));
    }

    /** Lista todos os produtos cadastrados, numerados, um por linha */
    static void listarTodosOsProdutos() {
    	
        cabecalho();
        System.out.println("\nProdutos cadastrados: ");
        for (int i = 0; i < quantosProdutos; i++) {
        	System.out.println(String.format("%02d - %s", (i + 1), produtosCadastrados[i].toString()));
        }
    }
    
    public static void main(String[] args) {
		teclado = new Scanner(System.in, Charset.forName("UTF-8"));
        nomeArquivoDados = "produtos.txt";
        produtosCadastrados = lerProdutos(nomeArquivoDados);
        
        int opcao = -1;
      
        do{
        	opcao = menu();
            switch (opcao) {
                case 1 -> mostrarProduto(localizarProduto());
                case 2 -> ordenarProdutos();
                case 3 -> embaralharProdutos();
                case 4 -> listarTodosOsProdutos();
                case 0 -> System.out.println("FLW VLW OBG VLT SMP.");
            }
            pausa();
        } while (opcao != 0);       

        teclado.close();    
    }
}