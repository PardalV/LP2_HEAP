public class Carga {
    private int id;
    private int tipo;
    private int urgencia;
    private int peso;
    private String descricao;
    private int prioridade;

    public Carga(int id, int tipo, int urgencia, int peso, String descricao){
        this.id = id;
        this.tipo = tipo;
        this.urgencia = urgencia;
        this.peso = peso;
        this.descricao = descricao;
        prioridade = (urgencia * 10) + (peso * 2) + (tipo * 5);
    }

    public int getPrioridade(){
        return prioridade
    }

    public int getUrgencia(){
        return this.urgencia;
    }
    public int getPeso(){
        return this.peso;
    }
    public int getId(){
        return this.id;
    }

    public boolean eMaiorQue(Carga outro){
        if(this.prioridade == outro.getPrioridade()){
            if(this.urgencia == outro.getUrgencia){
                if(this.peso == outro.getPeso){
                    if(this.id < outro.getId){
                        return true;
                    } else {
                        return false;
                    }
                } else if(this.peso > outro.peso){
                    return true;
                } else {
                    return false
                }
            } else if(this.urgencia > outro.getUrgencia){
                return true;
            } else {
                return false
            }
        }else if (this.prioridade > outro.getPrioridade()){
            return true;
        } else {
            return false;
        }
    }

}