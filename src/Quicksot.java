import java.util.Comparator;

public class Quicksorr<T extends Comparable<T>> implements IOrdenator<T>{

    private T[] dadosOrdenados;
    private Comparator<T> comparador;
    private long comparacoes;
    private long movimentacoes;
    private long inicio;
    private long termino;

    public Quicksorr() {
        comparacoes = 0;
        movimentacoes = 0;
        setComparador(T::compareTo);
    }

    public Quicksorr(Comparator<T> comparador) {
        comparacoes = 0;
        movimentacoes = 0;
        setComparador(comparador);
    }

    @Override
    public void setComparador(Comparator<T> comparador) {
        this.comparador = comparador;
    }   

    @Override
    public T[] ordenar(T[] dados) {
        dadosOrdenados = dados;
        comparacoes = 0;
        movimentacoes = 0;
        iniciar();
        quicksort(0, dadosOrdenados.length - 1);
        terminar();
        return dadosOrdenados;
    }

    private void quicksort(int esq, int dir) {
        if (esq < dir) {
            int pivo = particionar(esq, dir);
            quicksort(esq, pivo - 1);
            quicksort(pivo + 1, dir);
        }
    }
}