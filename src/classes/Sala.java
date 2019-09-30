package classes;

public class Sala {
    private int idSala;
    private int capacidade;
    
    public Sala(){
        this.idSala = 0;
        this.capacidade = 0;
    }

    public int getIdSala() {
        return idSala;
    }
    
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
