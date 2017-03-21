package sisa.ufrpe.br.sisaandroid;

import android.widget.CheckBox;
import android.widget.Spinner;

/**
 * Created by Leonardo on 23/10/2016.
 */

public class Disciplina {

    protected String nome, codigo, horarios;
    protected int id;
    protected int periodo;
    protected int area;

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Disciplina(String nome, String codigo, String horarios, int id, int periodo, int area) {
        this.nome = nome;
        this.codigo = codigo;
        this.horarios = horarios;
        this.id = id;
        this.periodo = periodo;
        this.area = area;
    }

    public Disciplina(int id){
        this.id = id;
    }
}
