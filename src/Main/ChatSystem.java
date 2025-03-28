package Main;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatSystem {
    private HashMap<Integer, Usuario> usuarios = new HashMap<>();
    private ArrayList<Conversa> conversas = new ArrayList<>();

    public void cadastrarUsuario(Usuario user){

        if(usuarios.containsValue(user.getId())){
            System.out.println("Usuário com ID " + user.getId() + " já existe!");
            return;
        }

        usuarios.put(user.getId(), user);
        System.out.println("Usuário " + user.getNome() + " criado com sucesso!");
    }

    public void deletarUsuario(int id){
        Usuario usuarioRemovido = usuarios.remove(id);

        // Remove o usuário das conversas
        for(Conversa conversa : conversas){
            conversa.getParticipantes().removeIf(participante -> participante.getId() == id);
        }
        System.out.println("Usuário com id " + id + " removido com sucesso.");
    }

    public void criarConversa(ArrayList<Usuario> participantes){
        if(participantes.size() < 2){
            System.out.println("Erro: Não foi possível criar a conversa, pois é necessário ter ao menos 2 participantes.");
        }
        Conversa novaConversa = new Conversa(participantes);
        conversas.add(novaConversa);
        System.out.println("Conversa criada com sucesso!");
    }

    public void enviarMensagem(Usuario users, Conversa chat, String conteudo){
        // Somente usuários que participam da conversa podem enviar mensagens
        if(!chat.getParticipantes().contains(users)){
            System.out.println("O usuário " + users.getNome() + " não participa da conversa!");
        }

        // tem ID único dentro da conversa
        Mensagem msg = new Mensagem(conteudo, users);
        chat.adicionarMensagem(msg);
        System.out.println("Mensagem enviada.");
    }

    public void excluirMensagem(Usuario autor, Conversa chat, int idMsg){
        for(Mensagem mensagem : chat.getMensagens()){
            if(mensagem.getId() == idMsg && mensagem.getAutor().getNome().equals(autor)){
                mensagem.setConteudo(autor.getNome() + " apagou a mensagem");
                return;
            }
        }

        System.out.println("Mensagem não encontrada ou você não tem permissão para apagar a mensagem.");
    }

    public void exibirConversa(Mensagem mensagem, Usuario user){
        // O sistema deve exibir todas as mensagens de uma conversa, mostrando:
        //[horário de envio] nome do usuário: conteúdo da mensagem

        System.out.println("[" + mensagem.getHorarioEnvio().getHours() + ":" + mensagem.getHorarioEnvio().getMinutes() + ":" + mensagem.getHorarioEnvio().getSeconds() + "]" + ' ' + user.getNome() + ": " + mensagem.getConteudo());
    }

    @Override
    public String toString() {
        return "ChatSystem{" +
                "\nconversas = " + conversas +
                "\nusuarios :\n " + usuarios;
    }
}
