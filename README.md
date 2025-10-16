# Checkpoint 5 - Parte 1: API Socket com Criptografia RSA

## Sobre o Projeto

Este projeto implementa um sistema de comunicação cliente-servidor em Java utilizando Sockets TCP. A principal característica é a segurança na troca de mensagens, garantida pela implementação do algoritmo de criptografia assimétrica RSA. Toda mensagem enviada do cliente para o servidor é criptografada e, ao ser recebida, é descriptografada para exibição.

---

## Integrantes do Grupo

* **Nome:** Arthur Bispo de Lima - **RM:** 557568
* **Nome:** João Paulo Moreira dos Santos - **RM:** 557808

---

## IDE Utilizada

O projeto foi desenvolvido utilizando a IDE: IntelliJ.

---

## Parâmetros RSA Utilizados

Para a geração das chaves Pública e Privada, foram utilizados os seguintes parâmetros, calculados com o auxílio da planilha `Dados RSA.xlsx`:

* **p:** 17
* **q:** 19
* **N (Módulo):** 323
* **φ(n) (Totiente):** 288
* **e (Expoente Chave Pública):** 5
* **d (Expoente Chave Privada):** 173

---

## Como Executar o Projeto

O sistema é composto por duas classes principais que devem ser executadas separadamente.

### 1. Executando o Servidor

Primeiro, execute o servidor para que ele comece a aguardar por conexões.

* **Classe principal:** `br.com.fiap.cp5.server.Servidor`
* **O que ele faz:** O servidor iniciará e ficará aguardando na porta 9999 por uma conexão do cliente.

### 2. Executando o Cliente

Com o servidor rodando, execute o cliente em um novo terminal.

* **Classe principal:** `br.com.fiap.cp5.client.Cliente`
* **O que ele faz:** O cliente se conectará automaticamente ao servidor. Em seguida, você poderá digitar mensagens no console. Cada mensagem será criptografada e enviada.

---

## Exemplo de Execução

Abaixo, um exemplo da comunicação entre o Cliente e o Servidor.

**Tela do Servidor recebendo e descriptografando a mensagem enviada pelo Cliente:**
![Tela do Servidor em Execução](https://raw.githubusercontent.com/ArthurBispo00/CP5-PART1-Server/master/imagem_tela_servidor.png)

**Tela do Cliente enviando a mensagem original e mostrando sua versão criptografada:**
![Tela do Cliente em Execução](https://raw.githubusercontent.com/ArthurBispo00/CP5-PART1-Server/master/imagem_tela_cliente.png)

---

## Validação com Simulador Externo

Conforme solicitado, foi realizado um teste de validação com o simulador **RSA Express Encryption-Decryption Calculator** da Drexel University para confirmar a corretude da nossa implementação.

* **Mensagem Original:** FIAP 2025
* **Mensagem Criptografada (gerada pelo nosso programa):** 185 99 12 207 223 84 250 84 287

![Tela do Cliente em Execução](https://raw.githubusercontent.com/ArthurBispo00/CP5-PART1-Server/master/Imagem_tela_drexel.png)