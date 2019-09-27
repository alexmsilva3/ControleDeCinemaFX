package classes;

import java.util.Date;
import java.sql.Time;

public class Sessao {
    private int idSessao;
    private Filme filme;
    private Sala sala;
    private Date data;
    private Time horario;
    private int valorIngresso;
    private int ingressosDisponiveis;
    
    public Sessao(){
        this.idSessao = 0;
        this.filme = new Filme();
        this.sala = new Sala();
        this.data = new Date();
        this.horario = new Time(00);
        this.valorIngresso = 0;
        this.ingressosDisponiveis = 0;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public int getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(int valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    public int getIngressosDisponiveis() {
        return ingressosDisponiveis;
    }

    public void setIngressosDisponiveis(int ingressosDisponiveis) {
        this.ingressosDisponiveis = ingressosDisponiveis;
    }
    
        //venda de ingressos (calcular valor total da compra)
}
