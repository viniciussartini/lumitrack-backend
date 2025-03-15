# Lumitrack

## Introdução

Projeto desenvolvido para o 2º semestre do curso de Análise e Desenvolvimento de Sistemas da PUC-MG.

Neste repositório será desenvolvido o back-end. O front-end por ser acessado [aqui]().

LumiTrack é uma aplicação inteligente que monitora o consumo de energia elétrica, ajudando a identificar desperdícios e otimizar os custos com soluções personalizadas.

## Tecnologias Utilizadas

![Logo Java](./src/public/logo-java.svg) ![Logo Spring Boot](./src/public/logo-spring-boot.svg) ![Logo Maven](./src/public/logo-maven.svg) ![Logo PostgreSQL](./src/public/logo-postgresql.svg)

## Controle de Versão

![Logo Git](./src/public/logo-git.svg) ![Logo Github](./src/public/logo-github.svg)

O projeto segue a seguinte convenção para o nome de branches:

- `main`: versão estável já testada do software
- `feature`: lançamento de funcionalidades do projeto
- `enhancement`: melhoria de funcionalidades do projeto
- `bug-fix`: correção de problemas no software

Para issues segue a seguinte convenção:

- `documentation`: melhorias ou acréscimos à documentação
- `bug`: uma funcionalidade encontra-se com problemas
- `enhancement`: uma funcionalidade precisa ser melhorada
- `feature`: uma nova funcionalidade precisa ser introduzida

Utilizado o Github Projetc para organização das tarefas do projeto, estruturado com as seguintes listas:

- **Backlog:** recebe as tarefas a serem trabalhadas e representa o Backlog do produto.
- **To Do:** representa os itens que estão sendo trabalhados.
- **In progress:** tarefas iniciadas.
- **In Review:** tarefas em revisão.

## Requisitos

### Requisitos Funcionais

|ID    | Descrição do Requisito  | Prioridade |
|------|-----------------------------------------|----|
|RF-001| A aplicação deve permitir cadastro e implementação de diferentes níveis de acesso para administradores, moderador e usuários comuns. | MÉDIA |
|RF-002| A aplicação deve permitir o cadastro e autenticação dos usuários. | ALTA |
|RF-003| A aplicação deverá permitir que o usuário administrador cadastre usuário moderador, registre aparelhos e insira dados contendo nome, tensão e potência. | MÉDIA |
|RF-004| A aplicação deverá permitir que o usuário moderador registre aparelhos e insira dados contendo nome, tensão e potência. | MÉDIA |
|RF-005| O usuário comum deve ser capaz de solicitar a inclusão de novos aparelhos a usuários moderadores ou administradores. | BAIXA |
|RF-006| A aplicação deve permitir o registro de múltiplas edificações com dados como localização, área total, e tipo de uso (residencial, comercial etc.). | ALTA |
|RF-007| A aplicação deve permitir cadastrar e vincular cômodo à edificação correspondente. | ALTA |
|RF-008| A aplicação deve permitir cadastrar e vincular aparelhos em cada cômodo. | ALTA |
|RF-009| O sistema calculará o consumo total de energia para cada edificação, somando os consumos de cada cômodo e aparelhos existentes. | ALTA |
|RF-010| A aplicação deve manter um histórico de consumo detalhado, permitindo revisões e análises ao longo do tempo. | MÉDIA |
|RF-011| A aplicação deve fornecer dashboards para visualização de dados de consumo em gráficos, tabelas, permitindo que o usuário visualize quais áreas da casa consomem mais energia. | BAIXA |
|RF-012| A aplicação deve permitir comparar o histórico de consumo com valores de potência e tensão predefinidos por usuário administrador ou moderador para aparelhos, tendo como base datas definidas pelo usuário. | MÉDIA |
|RF-013| A aplicação deve permitir que o usuário simule o valor total da conta de luz ao fim do mês, com base no consumo total de energia registrado ou simulado, e no valor do kWh fornecido pelo usuário. | ALTA |
|RF-014| A aplicação deve permitir avaliar a eficiência energética com base nos dados coletados e oferecer sugestões como a substituição de equipamentos ou a alteração de horários de uso. | BAIXA |
|RF-015| A aplicação deve permitir criar relatórios automáticos que mostram padrões de consumo, ineficiências e sugestões de melhoria. | MÉDIA |
|RF-016| A aplicação deve permitir a exportação de dados e relatórios em formato CSV. | BAIXA |

### Requisitos Não Funcionais

|ID     | Descrição do Requisito  |Prioridade |
|-------|-------------------------|----|
|RNF-001| A interface deve ser intuitiva, permitindo que usuários sem conhecimento técnico possam navegar e utilizar as funcionalidades com facilidade. | ALTA |
|RNF-002| A aplicação deve ser acessível em diferentes dispositivos (desktop, tablets, smartphones). |  MÉDIA |
|RNF-003| O código deve ser modular e documentado para facilitar a manutenção e futuras atualizações. |  ALTA |
|RNF-004| A aplicação deve ser compatível com os principais navegadores (Google Chrome, Firefox, Microsoft Edge). |  ALTA |
|RNF-005| A aplicação deve ser desenvolvido seguindo boas práticas e convenções de codificação em C#. |  BAIXA |
|RNF-006| A aplicação deve incluir medidas básicas de segurança |  BAIXA |

## Arquitetura da Solução

### Diagrama de Classes

```mermaid
classDiagram

    direction LR

    Usuario "1"-- "*" Edificacao
    Edificacao "1" *-- "*"Comodo
    Comodo "1..*"-- "0..*" Aparelho

    class Usuario {
        -Integer Id
        -String Nome
        -String Email
        -String Senha
        +CadastrarUsuario()
        +VisualizarEdificacoes()
    }
    
    class Edificacao {
        -Integer Id
        -String Nome
        -String Rua
        -String CEP
        -String Cidade
        -String Estado
        +ConsumoTotal()
        +GerarRelatorio() void
    }
    class Aparelho {
        -Integer Id
        -String Nome
        -Double Potencia
        -Double Tensao
        +CadastrarAparelho()void
    }
    class Comodo {
        -Integer Id
        -String Nome
        +CalcularConsumo()double
        +GerarRelatorio()void
        +VincularAparelho()Aparelho
    }
```

## Modelo ER (Projeto Conceitual)

![Modelo ER do Lumitrack]()

## Projeto da Base de Dados

```mermaid
erDiagram

Usuario ||--|{Edificacao : ""
Edificacao ||--|{Comodo : ""
Comodo ||--|{Aparelho : ""
Comodo ||--|{Consumo : ""
Aparelho ||--|{Consumo : ""

Usuario {
    Int id PK
    Char nome
    Char email
    Char perfil
    Char senha
}

Edificacao {
    Int id PK
    Char nome
    Char rua
    Char cidade
    Char estado
    Char cep
    Int usuario_id FK
}

Comodo {
    Int id PK
    Char nome
    Int edificacao_id FK
}

Aparelho {
    Int id PK
    Char nome
    Double potencia
    Double tensao
    Int comodo_id FK
}

Consumo {
    Int id PK
    DateTime data_registro
    Int uso_minuto
    Double consumo_aparelho
    Int comodo_id FK
    Int aparelho_id FK
}
```
