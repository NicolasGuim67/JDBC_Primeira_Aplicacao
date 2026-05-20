Aplicação de Inserção de Dados com Java JDBC e MySQL
Este projeto é um exemplo prático de integração entre o Java 25 e o banco de dados MySQL utilizando a API JDBC (Java Database Connectivity) e o gerenciador de dependências Maven.

O objetivo principal da aplicação é realizar a inserção segura de novos registros em uma tabela chamada pessoas, utilizando boas práticas de isolamento de credenciais, gerenciamento de conexões e tratamento de exceções personalizadas.

🚀 Funcionalidades
Conexão Segura: Criação e fechamento de conexões com o banco de dados via JDBC.

PreparedStatement: Utilização de consultas parametrizadas para evitar ataques de SQL Injection.

Isolamento de Credenciais: Configurações de acesso ao banco separadas do código-fonte por meio de arquivo de propriedades.

Tratamento de Exceções Personalizado: Uso de RuntimeExceptions customizadas para capturar erros de banco de dados e manter o código limpo. 

📁 Estrutura do Código
A aplicação está dividida em duas frentes principais (pacotes):

1. Pacote application
Program.java: Contém o ponto de entrada da aplicação. Ele abre a conexão com o banco de dados, prepara o comando INSERT com os dados de uma pessoa (Nome, Nascimento, Sexo, Peso, Altura e Nacionalidade), executa a query e garante que todos os recursos (Connection e Statement) sejam fechados corretamente no bloco finally.

2. Pacote db
DbException.java: Uma classe de exceção personalizada que estende RuntimeException. Ela captura erros nativos do SQL (SQLException) e os relança de forma controlada, evitando poluir as assinaturas dos métodos com throws.

DbIntegrityException.java: Exceção preparada para tratar problemas relacionados à integridade referencial do banco de dados (como tentar deletar um registro que possui chaves estrangeiras vinculadas).


📁 Configuração do Projeto
1. Dependências do Maven (pom.xml)
O projeto utiliza o Java 25 e necessita do driver oficial do MySQL para funcionar. Certifique-se de que o seu arquivo pom.xml possua a seguinte estrutura:

XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>IntegracaoJDBC</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>25</maven.compiler.source>
        <maven.compiler.target>25</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- Driver de Conexão do MySQL para o JDBC -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>9.1.0</version>
        </dependency>
    </dependencies>
</project>
2. Dados de Conexão (db.properties)
Para que a classe DataBaseConnection consiga se conectar ao seu banco de dados local, crie um arquivo chamado db.properties dentro da pasta src/main/resources/ com as seguintes variáveis:

Properties
user=root
password=my@sqldbroot1
dburl=jdbc:mysql://localhost:3306/cadastro?useSSL=false&serverTimezone=UTC 


🗄️ Estrutura da Tabela no MySQL
A aplicação foi desenhada para interagir com o esquema de banco de dados cadastro. Antes de rodar o programa, execute o comando SQL abaixo no seu MySQL para criar a tabela necessária:

SQL
CREATE DATABASE cadastro;
USE cadastro;

CREATE TABLE pessoas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    nascimento DATE NOT NULL,
    sexo CHAR(1),
    peso DECIMAL(5,2),
    altura DECIMAL(3,2),
    nacionalidade VARCHAR(30)
);
