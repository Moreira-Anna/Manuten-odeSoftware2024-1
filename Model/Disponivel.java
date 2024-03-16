package Model;


public class Disponivel extends Livro {
    String prateleira;
    int quantidade;
    int corredor;


    public Disponivel() {
        prateleira = " ";
        quantidade = 0;
        corredor = 0;
    }

    public Disponivel(String nome_livro, String editora, String genero, String prateleira, int quantidade, int corredor) {
        this.nomeLivro = nome_livro;
        this.editora = editora;
        this.genero = genero;
        this.prateleira = prateleira;
        this.quantidade = quantidade;
        this.corredor = corredor;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCorredor() {
        return corredor;
    }

    public void setCorredor(int corredor) {
        this.corredor = corredor;
    }
}
