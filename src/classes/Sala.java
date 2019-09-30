package classes;

public class Sala {
    private Integer idSala;
    private Integer capacidade;
    
    public Sala(){
        this.idSala = 0;
        this.capacidade = 0;
    }

    public Integer getIdSala() {
        return idSala;
    }
    
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public Integer getCapacidade() {
        return capacidade;
    }
    
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
