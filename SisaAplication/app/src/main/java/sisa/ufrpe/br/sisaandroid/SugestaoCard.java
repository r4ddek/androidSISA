package sisa.ufrpe.br.sisaandroid;

/**
 * Created by Paulo on 14/02/2017.
 */

public class SugestaoCard {

    private String titulo;
    private String descricao;

    public SugestaoCard(){

    }

    public SugestaoCard(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
