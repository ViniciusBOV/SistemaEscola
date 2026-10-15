public class Aluno {

    // Atributos (características do aluno)
    private String nome;
    private double nota1;
    private double nota2;
    private double nota3;
    private double media;

    // Construtor: é chamado quando criamos um novo Aluno
    public Aluno(String nome, double nota1, double nota2, double nota3) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        // Já calcula a média automaticamente na hora que o aluno é criado!
        this.media = calcularMedia(nota1,nota2,nota3);
    }

    // Método privado que faz o cálculo da média
    private double calcularMedia(double n1, double n2, double n3) {
        return (n1 + n2 + n3) / 3.0;
    }

    public String getNome() { return nome; }
    public double getNota1() { return nota1; }
    public double getNota2() { return nota2; }
    public double getNota3() { return nota3; } // Novo getter
    public double getMedia() { return media; }

    // Um método para facilitar a visualização se mandarmos imprimir o aluno
    @Override
    public String toString() {
        return "Aluno: " + nome + " | Média: " + media;
    }
}