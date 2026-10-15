# SistemaEscola

Sistema simples de controle de notas de alunos, feito em Java com banco de dados SQLite.

> Rascunho de README baseado no que existe hoje no repositório (módulo IntelliJ, driver SQLite e o banco `banco_notas.db`). Itens marcados com **(preencher)** dependem do código-fonte, que ainda não foi enviado.

## Tecnologias

- Java (projeto IntelliJ IDEA)
- SQLite, via driver `sqlite-jdbc` 3.51.2.0

## Banco de dados

O arquivo `banco_notas.db` contém a tabela `alunos`:

| Coluna | Tipo | Observação |
|--------|------|------------|
| `id` | INTEGER | Chave primária, autoincremento |
| `nome` | TEXT | Obrigatório |
| `nota1` | REAL | Primeira nota |
| `nota2` | REAL | Segunda nota |
| `media` | REAL | Média das duas notas |

## Como executar

1. Instale um JDK **(preencher: versão)**.
2. Abra o projeto no IntelliJ IDEA.
3. Adicione o `sqlite-jdbc` como biblioteca do projeto (File > Project Structure > Libraries).
4. Execute a classe principal **(preencher: nome da classe)**.

O banco `banco_notas.db` deve ficar na pasta de onde o programa é executado.

## Funcionalidades

**(preencher)** Ex.: cadastrar aluno, lançar notas, calcular média, listar alunos.

## Estrutura do projeto

```
SistemaEscola/
├── src/                  (preencher: código-fonte)
├── banco_notas.db        banco SQLite com a tabela alunos
├── SistemaEscola.iml     configuração do módulo IntelliJ
└── README.md
```

## Autor

Vinicius Bomfim
