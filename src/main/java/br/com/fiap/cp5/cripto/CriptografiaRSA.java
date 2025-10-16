package br.com.fiap.cp5.cripto;

import java.math.BigInteger;

/**
 * Classe utilitária para realizar a criptografia e descriptografia RSA.
 * Utiliza BigInteger para conseguir lidar com os números grandes gerados pelo RSA.
 */
public class CriptografiaRSA {

    // --- NOSSAS CONSTANTES DEFINIDAS NA PLANILHA ---

    // N = p * q
    private static final BigInteger N = new BigInteger("323");

    // E (Expoente da Chave Pública)
    private static final BigInteger E = new BigInteger("5");

    // D (Expoente da Chave Privada)
    private static final BigInteger D = new BigInteger("173");


    /**
     * Criptografa uma mensagem de texto.
     * @param mensagem A mensagem a ser criptografada.
     * @return Uma string contendo os números criptografados, separados por espaço.
     */
    public static String criptografar(String mensagem) {
        StringBuilder mensagemCriptografada = new StringBuilder();

        // Itera sobre cada caractere da mensagem
        for (char caractere : mensagem.toCharArray()) {
            // Converte o caractere para seu valor numérico (ASCII)
            int ascii = (int) caractere;

            // Transforma o valor ASCII em um BigInteger
            BigInteger m = BigInteger.valueOf(ascii);

            // Aplica a fórmula de criptografia RSA: c = m^e mod n
            BigInteger c = m.modPow(E, N);

            // Adiciona o número criptografado à string de resultado, seguido de um espaço
            mensagemCriptografada.append(c).append(" ");
        }

        return mensagemCriptografada.toString().trim(); // .trim() remove o espaço extra no final
    }

    /**
     * Descriptografa uma mensagem.
     * @param mensagemCriptografada A string de números criptografados, separados por espaço.
     * @return A mensagem original descriptografada.
     */
    public static String descriptografar(String mensagemCriptografada) {
        StringBuilder mensagemOriginal = new StringBuilder();

        // Separa a string de entrada em um array de números (baseado nos espaços)
        String[] blocosCriptografados = mensagemCriptografada.split(" ");

        // Itera sobre cada número criptografado
        for (String bloco : blocosCriptografados) {
            // Transforma a string do número em um BigInteger
            BigInteger c = new BigInteger(bloco);

            // Aplica a fórmula de descriptografia RSA: m = c^d mod n
            BigInteger m = c.modPow(D, N);

            // Converte o BigInteger resultante de volta para um int (valor ASCII)
            int ascii = m.intValue();

            // Converte o valor ASCII de volta para um caractere e o adiciona ao resultado
            mensagemOriginal.append((char) ascii);
        }

        return mensagemOriginal.toString();
    }

    // --- MÉTODO MAIN PARA TESTE RÁPIDO E ISOLADO ---
    // Este método serve apenas para validarmos se a classe funciona, antes de usá-la no Cliente/Servidor.
    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTE DA CLASSE DE CRIPTOGRAFIA ---");

        String mensagemOriginal = "FIAP 2025";
        System.out.println("Mensagem Original: " + mensagemOriginal);
        System.out.println();

        // 1. Criptografar a mensagem
        String mensagemCriptografada = criptografar(mensagemOriginal);
        System.out.println("Mensagem Criptografada: " + mensagemCriptografada);
        System.out.println();

        // 2. Descriptografar a mensagem
        String mensagemDescriptografada = descriptografar(mensagemCriptografada);
        System.out.println("Mensagem Descriptografada: " + mensagemDescriptografada);
        System.out.println();

        // 3. Validar o resultado
        if (mensagemOriginal.equals(mensagemDescriptografada)) {
            System.out.println("SUCESSO: A mensagem original e a descriptografada são iguais!");
        } else {
            System.out.println("FALHA: As mensagens são diferentes!");
        }

        System.out.println("--- FIM DO TESTE ---");
    }
}