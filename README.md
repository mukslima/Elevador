# Sistema de Controle de Elevador
##### Padrões de Projeto de Software - Colaborativa 03

Este projeto implementa um sistema de controle de elevador utilizando conceitos de Orientação a Objetos e três padrões de projeto principais: Command, Observer e State. O objetivo é criar um sistema modular, flexível e de fácil manutenção, simulando o funcionamento de um elevador que atende a diferentes andares e possui interações com o usuário.

# Como funciona o sistema

O sistema permite ao usuário escolher se deseja subir ou descer, selecionar um andar entre 0 e 10, e controlar o movimento do elevador, mostrando mensagens de abertura e fechamento de portas nos momentos corretos. Há também a implementação de uma lógica de emergência e manutenção em caso de uso excessivo ou erros críticos.

# Funcionalidade Principal

- Subir e descer entre os andares.
- Abrir e fechar portas de acordo com o movimento do elevador.
- Entrar em estados de emergência e manutenção após uso excessivo ou comandos de emergência.
- Estado de emergência força uma pausa do sistema, retornando após um tempo definido.

# Estrutura do Código

### Elevador 
A classe principal que controla o movimento do elevador. Essa classe armazena o estado atual e as requisições de andares, além de gerenciar as mudanças de estados.

### PainelControle
Exibe as atualizações do estado do elevador, como o andar atual e mensagens sobre o status do elevador.

### SistemaElevador
Classe responsável por iniciar o sistema e gerenciar a interação entre o usuário e o elevador. Essa classe também lida com os comandos de manutenção e emergência.

# Padrões de Projeto Utilizados

## 1. Command
O padrão Command encapsula ações que o elevador pode realizar, como subir, descer, abrir ou fechar portas. Cada ação é representada por um comando, permitindo que o elevador seja controlado de maneira flexível, facilitando a execução ou cancelamento de operações.

### Implementação no projeto:
As classes como ComandoSubir, ComandoDescer, ComandoAbrirPorta, ComandoFecharPorta, ComandoAndar, ComandoElevador, ComandoEmergencia e ComandoManutencao encapsulam essas ações, permitindo flexibilidade na execução dos comandos sem que a lógica principal do elevador seja impactada.

## 2. Observer
O padrão Observer é usado para atualizar o painel de controle automaticamente quando o estado do elevador muda, como a chegada a um novo andar ou a alteração entre estados como subindo ou descendo.

### Implementação no projeto:
A classe PainelControle observa o elevador e é notificada sempre que uma mudança ocorre. A classe Elevador notifica o painel, garantindo que o usuário veja as informações mais recentes.

## 3. State
O padrão State gerencia os diferentes estados do elevador, como Subindo, Descendo, Parado, Emergência e Manutenção. Dependendo do estado atual, o comportamento do elevador varia, como abrir ou fechar portas, ou pausar as operações durante emergências e manutenções.

### Implementação no projeto:
Classes como EstadoSubindo, EstadoDescendo, EstadoParado, EstadoEmergencia e EstadoManutencao implementam o comportamento do elevador dependendo do estado em que ele se encontra.

# Diagrama de Classe
## 01. principal
![image](DC03.png)

## 02. State
![image](DC-State.png)

# Como Executar

1. Clone este repositório do GitHub usando o comando "git clone https://github.com/mukslima/Elevador" ou Baixe o repositório em formato ZIP clicando no botão 'Code' e, em seguida, em 'Download ZIP' 
2. Compile e execute o projeto Java.
3. Interaja com o sistema por meio do terminal, onde será possível ver a comandos de subir, descer, ou entrar a emergência e manutenção.

# Regras de Operação

1. Não é possível subir acima do 10º andar.
2. Não é possível descer abaixo do térreo (0º andar).

# Melhorias Futuras 

- Implementar um sistema de prioridade nas chamadas para otimizar o fluxo de passageiros.
- Adicionar suporte a múltiplos elevadores com controle de alocação.
- Melhorar o design da interface do usuário com uma GUI.

# Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para fazer um fork deste projeto e enviar pull requests com melhorias e correções de bugs.


