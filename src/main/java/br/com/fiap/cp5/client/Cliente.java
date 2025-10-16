package br.com.fiap.cp5.client;

import br.com.fiap.cp5.cripto.CriptografiaRSA;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            // 1. Conectar ao servidor
            // O endereço "localhost" significa "esta mesma máquina"
            // A porta deve ser a mesma que o servidor está escutando (9999)
            Socket socket = new Socket("localhost", 9999);
            System.out.println("Conectado ao servidor!");
            System.out.println("----------------------------------------------------------");

            // 2. Preparar os canais de comunicação e o leitor do console
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            Scanner console = new Scanner(System.in);

            // 3. Loop de comunicação
            // O cliente ficará enviando mensagens até que o usuário digite "fim"
            while (true) {
                System.out.print("Digite sua mensagem (ou 'fim' para sair): ");
                String mensagemOriginal = console.nextLine();

                // Criptografar a mensagem antes de enviar
                String mensagemCriptografada = CriptografiaRSA.criptografar(mensagemOriginal);
                System.out.println("-> Mensagem criptografada enviada: " + mensagemCriptografada);
                System.out.println(); // Apenas para pular uma linha

                // Enviar a mensagem criptografada para o servidor
                saida.writeUTF(mensagemCriptografada);
                saida.flush(); // Garante que a mensagem seja enviada imediatamente

                // Verificar se é hora de encerrar
                if (mensagemOriginal.equalsIgnoreCase("fim")) {
                    System.out.println("Encerrando a conexão.");
                    break; // Sai do loop while
                }
            }

            // 4. Encerrar os recursos
            console.close();
            entrada.close();
            saida.close();
            socket.close();

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}