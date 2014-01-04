package br.com.projetoanimais.model;

public class Terreno {

    // modelo de entidade que controla o terreno em que o animal vive

    private int id; // id do terreno
    private String nome; // nome do terreno

    public Terreno(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
