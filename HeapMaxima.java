public class HeapMaxima{
    private Carga[] heap;
    private int quantidade;
    private int capacidade;

    public HeapMaxima(int capacidadeInicial){
        this.capacidade = capacidadeInicial;
        this.quantidade = 0;
        this.heap = new Carga[capacidade + 1];
    }

    public void inserir(Carga novaCarga){

    }

    public void removerMaximo(){

    }

    public Carga consultarTopo(){
        if(quantidade == 0){
            return null;
        }
        return heap[1];
    }

    public int tamanho(){
        return this.quantidade;
    }

    public boolean estaVazia(){
        
    }
    private void trocar(int i, int j){
        Carga aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    private void garantirCapacidade(){
        if(quantidade >= capacidade){
            Carga[] novo = new Carga[capacidade * 2 + 1];
            for (int i = 0; i <= quantidade; i ++){
                novo[i] = heap[i];
            }
            this.heap = novo;
            this.capacidade = capacidade * 2 + 1;
        }
    }
}