ublic class Testavetores {

    private static final double NANO_TO_MILLI = 1.0 / 1_000_000;

    public void testavetores(int[] tamanhosVetores, Consumer<int[]> funcao) {
        for (int tamanho : tamanhosVetores) {
            int[] vetor = gerarVetor(tamanho);

            long inicio = System.nanoTime();

            funcao.accept(vetor);

            long termino = System.nanoTime();

            double duracao = (double) (termino - inicio) * NANO_TO_MILLI;

            System.out.println(
                "Tamanho do vetor: " + tamanho +
                ", Tempo: " + duracao + " ms"
            );
        }
    }
}