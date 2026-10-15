import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ferramenta para ler o que digitamos no terminal
        Scanner scanner = new Scanner(System.in);

        // Chamando as classes que criamos nos Passos 2 e 3
        BancoDeDados banco = new BancoDeDados();
        GeradorPlanilha gerador = new GeradorPlanilha();

        // Assim que o programa abre, ele já verifica se a tabela precisa ser criada
        banco.criarTabela();

        int opcao = 0;

        System.out.println("\n=== SISTEMA DE NOTAS 100% OFFLINE ===");

        // O sistema vai ficar rodando em loop até você digitar "3" para sair
        while (opcao != 3) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar novo aluno e notas");
            System.out.println("2 - Gerar Planilha (CSV) com todos os dados");
            System.out.println("3 - Sair");
            System.out.print("Sua escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Isso serve para "limpar" o Enter que sobra quando digitamos um número

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do Aluno: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite a Nota 1: ");
                    double nota1 = scanner.nextDouble();

                    System.out.print("Digite a Nota 2: ");
                    double nota2 = scanner.nextDouble();

                    System.out.print("Digite a Nota 3: ");
                    double nota3 = scanner.nextDouble();

                    // PASSO 1: Cria o aluno (a média é calculada sozinha aqui)
                    Aluno novoAluno = new Aluno(nome, nota1, nota2,nota3);

                    // PASSO 2: Salva no banco de dados SQLite
                    banco.salvarAluno(novoAluno);
                    break;

                case 2:
                    // Busca todos os alunos que estão salvos no banco
                    List<Aluno> listaAlunos = banco.buscarTodosAlunos();

                    if (listaAlunos.isEmpty()) {
                        System.out.println("Ainda não há alunos cadastrados para gerar a planilha.");
                    } else {
                        // PASSO 3: Manda a lista para o nosso gerador de CSV
                        gerador.exportarParaCSV(listaAlunos);
                    }
                    break;

                case 3:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        // Fecha o leitor do teclado ao sair do programa
        scanner.close();
    }
}