package Model;

// Ana Moreira


public class Livro {
    String nomeLivro;
    String editora;
    String genero;
    public Livro() {}

    public Livro(String nomeLivro, String editora, String genero){
        this.nomeLivro = nomeLivro;
        this.editora = editora;
        this.genero = genero;
    }


    /**
     * @return the nomeLivro
     */
    public String getnomeLivro() {
        return nomeLivro;
    }

    /**
     * @param nomeLivro the nomeLivro to set
     */
    public void setnomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    /**
     * @return the editora
     */
    public String getEditora() {
        return editora;
    }

    /**
     * @param editora the editora to set
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
