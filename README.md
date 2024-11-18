# Projeto Qbank - A3

## Descrição do Projeto
O **Qbank** é um sistema bancário desenvolvido para permitir que os clientes realizem diversas operações financeiras por meio de um aplicativo. As funcionalidades incluem o gerenciamento de contas, transações, empréstimos, cartões de crédito e pagamentos de serviços externos.

---

## Dependências

Para construir e executar o projeto, você precisará incluir as seguintes dependências no seu arquivo `pom.xml`:

### Micronaut 4.6.6
- **Micronaut HTTP Server**: Para criação de servidores HTTP.
- **Micronaut Serialization Jackson**: Para serialização e desserialização de objetos JSON.

### Mockito
- **Mockito**: Biblioteca de simulação para testes em Java, utilizada para criar objetos simulados em testes unitários.

### Dependências do `pom.xml`
```xml
<dependencies>
  <dependency>
    <groupId>io.micronaut</groupId>
    <artifactId>micronaut-http-server-netty</artifactId>
    <scope>compile</scope>
  </dependency>
  <dependency>
    <groupId>io.micronaut.serde</groupId>
    <artifactId>micronaut-serde-jackson</artifactId>
    <scope>compile</scope>
  </dependency>
  <dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <scope>runtime</scope>
  </dependency>
  <dependency>
    <groupId>io.micronaut</groupId>
    <artifactId>micronaut-http-client</artifactId>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>io.micronaut.test</groupId>
    <artifactId>micronaut-test-junit5</artifactId>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <scope>test</scope>
    <version>5.5.0</version>
  </dependency>
  <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.14.1</version>
  </dependency>
  <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.9</version>
  </dependency>
</dependencies>
```

---

## Funcionalidades do Sistema

### 1. Módulo de Contas
- **Criação de Contas**: Permite que os usuários criem novas contas.
- **Visualização de Contas**: Os usuários podem visualizar suas contas ativas.
- **Atualização de Contas**: Possibilidade de modificar os dados da conta.
- **Exclusão de Contas**: Opção para encerrar contas existentes.

### 2. Módulo de Transações
- **Transferências**: Realização de transferências para:
    - Contas próprias
    - Terceiros
    - Outros bancos

### 3. Módulo de Empréstimos
- **Solicitação de Empréstimos**: Os usuários podem solicitar um empréstimo preenchendo um formulário.
- **Cálculo do Empréstimo**: O sistema calcula o valor máximo que o usuário pode emprestar e o plano de pagamento.
- **Pagamento de Parcelas**: Opção para pagar mensalmente ou agendar débito automático.

### 4. Módulo de Cartão de Crédito
- **Solicitação de Cartão de Crédito**: Os usuários podem solicitar um cartão de crédito preenchendo um formulário de inscrição.
- **Cálculo do Limite**: O sistema calcula o limite de crédito disponível.
- **Gestão de Pagamentos**: Opções para pagar manualmente ou agendar pagamentos com débito automático.

### 5. Módulo de Pagamento de Serviços
- **Pagamentos de Serviços Externos**: Permite o pagamento de mensalidades universitárias, contas gerais e serviços telefônicos.
- **Registro de Serviços**: Os usuários registram o serviço que desejam pagar, fornecendo os dados necessários.
- **Opções de Pagamento**: Possibilidade de pagamentos manuais ou agendados com débito automático.

---

## Estrutura Modular

O sistema é organizado em módulos distintos, cada um abordando uma área específica de operações bancárias, o que facilita a navegação e a experiência do usuário. A modularidade garante que cada funcionalidade possa ser aprimorada ou expandida sem afetar as demais.
