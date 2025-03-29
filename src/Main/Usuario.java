package Main;

public class Usuario {
    private int id;
    private static int contID = 0;
    private String nome, email, senha;

    public Usuario(String nome, String email, String senha) {
        this.id = ++contID;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return nome;
    }
}