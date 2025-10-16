package br.com.fiap.cp5.server;

import br.com.fiap.cp5.cripto.CriptografiaRSA;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        try {
            // 1. Iniciar o Servidor e definir a porta de escuta (ex: 9999)
            // O ServerSocket fica aguardando pedidos de conexão de clientes
            ServerSocket servidorSocket = new ServerSocket(9999);
            System.out.println("Servidor iniciado. Aguardando conexão do cliente na porta 9999...");

            // 2. Aceitar uma conexão de cliente
            // O método accept() bloqueia a execução até que um cliente se conecte
            Socket socketCliente = servidorSocket.accept();
            System.out.println("Cliente conectado: " + socketCliente.getInetAddress().getHostAddress());
            System.out.println("----------------------------------------------------------");


            // 3. Preparar os canais de comunicação (entrada e saída de dados)
            DataInputStream entrada = new DataInputStream(socketCliente.getInputStream());
            DataOutputStream saida = new DataOutputStream(socketCliente.getOutputStream());

            // 4. Loop de comunicação
            // O servidor ficará lendo as mensagens do cliente até receber "fim"
            while (true) {
                // Ler a mensagem criptografada enviada pelo cliente
                String mensagemCriptografada = entrada.readUTF();
                System.out.println("Mensagem criptografada recebida: " + mensagemCriptografada);

                // Descriptografar a mensagem usando nossa classe
                String mensagemOriginal = CriptografiaRSA.descriptografar(mensagemCriptografada);
                System.out.println("Mensagem descriptografada: " + mensagemOriginal);
                System.out.println(); // Apenas para pular uma linha e organizar

                // Verificar se a mensagem é o comando para encerrar
                if (mensagemOriginal.equalsIgnoreCase("fim")) {
                    System.out.println("Cliente solicitou o encerramento da conexão.");
                    break; // Sai do loop while
                }
            }

            // 5. Encerrar os recursos
            System.out.println("Encerrando conexão com o cliente.");
            entrada.close();
            saida.close();
            socketCliente.close();
            servidorSocket.close();

        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}