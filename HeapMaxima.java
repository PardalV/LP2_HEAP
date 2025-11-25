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
        garantirCapacidade();
        this.quantidade++;
        this.heap[this.quantidade] = novaCarga;
        subir(this.quantidade);
    }

    public Carga removerMaximo(){
        if(this.quantidade == 0){
            System.out.println("A Heap está vazia, não há o que remover");
            return null;
        }
        Carga raiz = this.heap[1];
        this.heap[1] = this.heap[this.quantidade];
        this.heap[this.quantidade] = null;
        this.quantidade--;
        descer(1);
        return raiz;
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
        return this.quantidade == 0;
    }

    private void subir(int i){
        int pai = i/2;
        while(i > 1 && this.heap[i].eMaiorQue(this.heap[pai])){
            trocar(i, pai);
            i = pai;
            pai = i/2;
        }
    }

    private void descer(int i){
        int fiE, fiD, maior;
        while(2 * i <= this.quantidade){
            fiE = 2 * i;
            fiD = 2 * i + 1;
            maior = fiE;
            if (fiD <= this.quantidade && this.heap[fiD].eMaiorQue(this.heap[fiE])){
                maior = fiD;
            }
            if(this.heap[maior].eMaiorQue(this.heap[i])){
                trocar(i, maior);
                i = maior;
            } else {
                break;
            }
        }
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
    public Carga[] getVetorCopia(){
        Carga[] copia = new Carga[this.quantidade + 1];
        for(int i = 1; i <= this.quantidade; i ++){
            copia[i] = this.heap[i];
        }
        return copia;
    }
}