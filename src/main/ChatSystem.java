package main;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatSystem {
    private HashMap<Integer, Usuario> usuarios = new HashMap<>();
    private ArrayList<Conversa> conversas = new ArrayList<>();

    public boolean cadastrarUsuario(Usuario user){

        if(usuarios.containsKey(user.getId())){
            System.out.println("Usuário com ID " + user.getId() + " já existe!");
            return false;
        }

        usuarios.put(user.getId(), user);
        System.out.println("Usuário " + user.getNome() + " cadastrado com sucesso!");
        return true;
    }

    public boolean deletarUsuario(int id){
        Usuario usuarioRemovido = usuarios.remove(id);

        if(usuarioRemovido != null){ // se o usuario foi encontrado
            // Remove o usuário das conversas
            for(Conversa conversa : conversas){
                conversa.getMensagens().removeIf(msg -> msg.getAutor().getId() == id);
                conversa.getParticipantes().removeIf(p -> p.getId() == id);

            }
            System.out.println("Usuário " + usuarioRemovido.getNome() + " com id " + id + " removido com sucesso.");
            return true;
        }else
            System.out.println("Usuário com ID " + id + " não existe");


        return false;
    }

    public void criarConversa(ArrayList<Usuario> participantes){
        if(participantes.size() < 2){
            System.out.println("Erro: Não foi possível criar a conversa, pois é necessário ter ao menos 2 participantes.");
        }
        else{
            Conversa novaConversa = new Conversa(participantes);
            conversas.add(novaConversa);
            System.out.println("Conversa criada com sucesso!");
        }
    }

    public boolean enviarMensagem(Usuario user, Conversa chat, Mensagem msg){
        // Somente usuários que participam da conversa podem enviar mensagens
        if(!chat.getParticipantes().contains(user)){
            System.out.println("O usuário " + user.getNome() + " não participa da conversa!");
            return true;
        }

        // tem ID único dentro da conversa
        chat.adicionarMensagem(msg);
        return false;
    }

    public void excluirMensagem(Usuario autor, Conversa chat, int idMsg){
        for(Mensagem mensagem : chat.getMensagens()){
            if(mensagem.getId() == idMsg && mensagem.getAutor().getId() == autor.getId()){
                mensagem.setConteudo("apagou a mensagem ");
                return;
            }
        }

        System.out.println("Mensagem não encontrada ou você não tem permissão para apagar a mensagem.");
    }

    public void exibirConversa(Conversa conversa){

        for(int i = 0; i < conversa.getMensagens().size(); i++){
            System.out.println(conversa.getMensagens().get(i));
        }
    }

    public ArrayList<Conversa> getConversas() {
        return conversas;
    }

    public HashMap<Integer, Usuario> getUsuarios() {
        return usuarios;
    }



    @Override
    public String toString() {
        return "ChatSystem{" +
                "\nconversas = " + conversas +
                "\nusuarios :\n " + usuarios;
    }
}
