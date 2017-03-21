package sisa.ufrpe.br.sisaandroid;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonardo on 28/10/2016.
 */

public class Aluno implements Serializable{

    private String cpf, nome, email, password, sFavorita;
    private int sCurso, sIngresso, periodoIngresso,  periodoTrancado, horaDedicada;
    private boolean[] cSpareTimes;
    private boolean cDedicacao;
    private ArrayList<Historico> historico, disciplinaAcompanhada, disciplinaReprovada;

    public ArrayList<Historico> getDisciplinaAcompanhada() {
        return disciplinaAcompanhada;
    }

    public void setDisciplinaAcompanhada(ArrayList<Historico> disciplinaAcompanhada) {
        this.disciplinaAcompanhada = disciplinaAcompanhada;
    }

    public ArrayList<Historico> getDisciplinaReprovada() {
        return disciplinaReprovada;
    }

    public void setDisciplinaReprovada(ArrayList<Historico> disciplinaReprovada) {
        this.disciplinaReprovada = disciplinaReprovada;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getPeriodoIngresso() {
        return periodoIngresso;
    }

    public void setPeriodoIngresso(int periodoIngresso) {
        this.periodoIngresso = periodoIngresso;
    }

    public int getPeriodoTrancado() {
        return periodoTrancado;
    }

    public void setPeriodoTrancado(int periodoTrancado) {
        this.periodoTrancado = periodoTrancado;
    }

    public int getHoraDedicada() {
        return horaDedicada;
    }

    public void setHoraDedicada(int horaDedicada) {
        this.horaDedicada = horaDedicada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getsCurso() {
        return sCurso;
    }

    public void setsCurso(int sCurso) {
        this.sCurso = sCurso;
    }

    public int getsIngresso() {
        return sIngresso;
    }

    public void setsIngresso(int sIngresso) {
        this.sIngresso = sIngresso;
    }

    public String getsFavorita() {
        return sFavorita;
    }

    public void setsFavorita(String sFavorita) {
        this.sFavorita = sFavorita;
    }

    public boolean[] getcSpareTimes() {
        return cSpareTimes;
    }

    public void setcSpareTimes(boolean[] cSpareTimes) {
        this.cSpareTimes = cSpareTimes;
    }

    public boolean iscDedicacao() {
        return cDedicacao;
    }

    public void setcDedicacao(boolean cDedicacao) {
        this.cDedicacao = cDedicacao;
    }

    public ArrayList<Historico> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Historico> historico) {
        this.historico = historico;
    }

    public Aluno(String cpf, String nome, String email, String password,  int sIngresso, int periodoIngresso, String sFavorita) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.password = password;

        this.sIngresso = sIngresso;
        this.periodoIngresso = periodoIngresso;
        this.sFavorita = sFavorita;
    }

    @Override
    public String toString(){
        return "Aluno [cpf=" + cpf + ", nome=" + nome + ", " +
                "email=" + email + ", senha=" + password + ", anoIngresso=" + sIngresso +
                ", areaDePreferencia=" + sFavorita + ", semestreIngresso=" + periodoIngresso + ", tempoEstudoExtraClasse=" + horaDedicada +
                ", disciplinasPagas=" + historico +", qtdPeriodosTrancados="+ periodoTrancado +
                ",disciplinasReprovadas="+ disciplinaReprovada +",disciplinasAcompanhadas" + disciplinaAcompanhada +"]";
    }
}
