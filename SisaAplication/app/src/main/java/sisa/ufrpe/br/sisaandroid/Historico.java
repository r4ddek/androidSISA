package sisa.ufrpe.br.sisaandroid;

/**
 * Created by Leonardo on 28/10/2016.
 */

public class Historico {

    private int id;
    private String codigo;
    private boolean situacaoAprovado;
    private int qtde;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean getSituacao() {
        return situacaoAprovado;
    }

    public void setSituacao(boolean situacao) {
        this.situacaoAprovado = situacao;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Historico(String codigo, boolean situacaoAprovado, int qtde) {
        this.codigo = codigo;
        this.situacaoAprovado = situacaoAprovado;
        this.qtde = qtde;
    }
}
