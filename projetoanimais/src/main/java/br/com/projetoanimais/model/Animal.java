package br.com.projetoanimais.model;

public class Animal {

    private int id;
    private int idTerreno;
    private String nome;

    public Animal(int id, String nome, int idTerreno) {
        super();
        this.id = id;
        this.idTerreno = idTerreno;
        this.nome = nome;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTerreno(){
        return idTerreno;
    }

    public void setIdTerreno(int idTerreno){
        this.idTerreno = idTerreno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", idTerreno=" + idTerreno +
                ", nome='" + nome + '\'' +
                '}';
    }
}