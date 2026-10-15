import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Tela extends JFrame {

    private BancoDeDados banco;
    private GeradorPlanilha gerador;

    private JTextField campoNome;
    private JTextField campoNota1;
    private JTextField campoNota2;
    private JTextField campoNota3; // Novo campo de texto

    public Tela() {
        banco = new BancoDeDados();
        gerador = new GeradorPlanilha();
        banco.criarTabela();

        setTitle("Sistema de Notas 100% Offline");
        // Aumentei um pouquinho a altura da janela para caber a nova nota
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Mudei o layout de (4, 2) para (5, 2) porque agora temos uma linha a mais!
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("  Nome do Aluno:"));
        campoNome = new JTextField();
        add(campoNome);

        add(new JLabel("  Nota 1:"));
        campoNota1 = new JTextField();
        add(campoNota1);

        add(new JLabel("  Nota 2:"));
        campoNota2 = new JTextField();
        add(campoNota2);

        // Adicionando a Nota 3 na tela
        add(new JLabel("  Nota 3:"));
        campoNota3 = new JTextField();
        add(campoNota3);

        JButton btnSalvar = new JButton("Salvar Aluno");
        JButton btnGerar = new JButton("Gerar Planilha CSV");

        add(btnSalvar);
        add(btnGerar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAluno();
            }
        });

        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarPlanilha();
            }
        });
    }

    private void salvarAluno() {
        try {
            String nome = campoNome.getText();
            double nota1 = Double.parseDouble(campoNota1.getText().replace(",", "."));
            double nota2 = Double.parseDouble(campoNota2.getText().replace(",", "."));
            // Lendo a nota 3 do campo de texto
            double nota3 = Double.parseDouble(campoNota3.getText().replace(",", "."));

            // Agora passamos as 3 notas
            Aluno aluno = new Aluno(nome, nota1, nota2, nota3);
            banco.salvarAluno(aluno);

            // Deixei a formatação da média com duas casas decimais para ficar mais bonito
            String mediaFormatada = String.format("%.2f", aluno.getMedia());
            JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!\nMédia calculada: " + mediaFormatada);

            campoNome.setText("");
            campoNota1.setText("");
            campoNota2.setText("");
            campoNota3.setText(""); // Limpa o campo da nota 3 também

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Digite apenas números nas notas!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gerarPlanilha() {
        List<Aluno> listaAlunos = banco.buscarTodosAlunos();

        if (listaAlunos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ainda não há alunos cadastrados.");
        } else {
            gerador.exportarParaCSV(listaAlunos);
            JOptionPane.showMessageDialog(this, "Planilha 'planilha_alunos.csv' gerada com sucesso na pasta do projeto!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tela janela = new Tela();
            janela.setVisible(true);
        });
    }
}