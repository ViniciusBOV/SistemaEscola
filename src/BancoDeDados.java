import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {

    private static final String URL = "jdbc:sqlite:banco_notas.db";

    private Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco: " + e.getMessage());
        }
        return conexao;
    }

    public void criarTabela() {
        // Adicionamos a nota3 REAL aqui
        String sql = "CREATE TABLE IF NOT EXISTS alunos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT NOT NULL,"
                + "nota1 REAL,"
                + "nota2 REAL,"
                + "nota3 REAL,"
                + "media REAL"
                + ");";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void salvarAluno(Aluno aluno) {
        // Agora são 5 interrogações (?)
        String sql = "INSERT INTO alunos (nome, nota1, nota2, nota3, media) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, aluno.getNome());
            pstmt.setDouble(2, aluno.getNota1());
            pstmt.setDouble(3, aluno.getNota2());
            pstmt.setDouble(4, aluno.getNota3()); // Injetando a nota 3
            pstmt.setDouble(5, aluno.getMedia());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    public List<Aluno> buscarTodosAlunos() {
        List<Aluno> listaDeAlunos = new ArrayList<>();
        String sql = "SELECT * FROM alunos";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                double nota1 = rs.getDouble("nota1");
                double nota2 = rs.getDouble("nota2");
                double nota3 = rs.getDouble("nota3"); // Lendo a nota 3

                // Recria o aluno com as 3 notas
                Aluno aluno = new Aluno(nome, nota1, nota2, nota3);
                listaDeAlunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos: " + e.getMessage());
        }
        return listaDeAlunos;
    }
}