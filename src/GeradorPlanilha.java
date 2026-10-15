import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GeradorPlanilha {

    public void exportarParaCSV(List<Aluno> alunos) {
        String nomeArquivo = "planilha_alunos.csv";

        try (FileWriter arquivo = new FileWriter(nomeArquivo);
             PrintWriter gravador = new PrintWriter(arquivo)) {

            // Adicionado "Nota 3" no cabeçalho
            gravador.println("Nome;Nota 1;Nota 2;Nota 3;Media");

            for (Aluno aluno : alunos) {
                // Adicionado getNota3() na hora de montar a linha
                String linha = aluno.getNome() + ";"
                        + aluno.getNota1() + ";"
                        + aluno.getNota2() + ";"
                        + aluno.getNota3() + ";"
                        + aluno.getMedia();

                gravador.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao gerar a planilha: " + e.getMessage());
        }
    }
}