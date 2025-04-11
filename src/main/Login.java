package main;

import java.util.Scanner;

public class Login {
    private ChatSystem chatSystem;

    public Login(ChatSystem chatSystem){
        this.chatSystem = chatSystem;
    }


    public Usuario logar(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Coloque seu email: ");
        String email = sc.nextLine();

        System.out.print("Coloque sua senha: ");
        String senha = sc.nextLine();

        for(Usuario user : chatSystem.getUsuarios().values()){
            if(user.getEmail().equals(email) && user.getSenha().equals(senha)){
                System.out.println("Login realizado com sucesso! Boas vindas " + user.getNome() + '!');
                return user; // conseguiu logar;
            }
        }

        return null; // não logou
    }
}
