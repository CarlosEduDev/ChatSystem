package Main;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatSystem {
    private HashMap<Integer, Usuario> usuarios = new HashMap<>();
    private ArrayList<Conversa> conversas = new ArrayList<>();

    public void cadastrarUsuario(String nome, String email, String senha){
        Usuario user = new Usuario(nome, email, senha);

        if(usuarios.containsValue(user.getId())){
            System.out.println("Usuário com ID " + user.getId() + " já existe!");
            return;
        }

        usuarios.put(user.getId(), user);
        System.out.println("Usuário " + nome + " criado com sucesso!");
    }

    public void deletarUsuario(int id){
        usuarios.remove(id);
        if(usuarios.equals(id))
            conversas.removeIf(conversa -> conversa.equals(id));
    }

    public void criarConversa(ArrayList<Usuario> conversa){
        if(conversa.size() < 2){
            System.out.println("Não foi possível criar a conversa, pois ");
        }
    }
}
