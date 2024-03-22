package entity;

public class Libro {
    private int id;
    private String title;
    private String year_publication;
    private Double price;
    private int id_author;

    private Autor objAutor;
    public Libro() {
    }

    public Libro(int id, String title, String year_publication, Double price, int id_author, Autor objAutor) {
        this.id = id;
        this.title = title;
        this.year_publication = year_publication;
        this.price = price;
        this.id_author = id_author;
        this.objAutor = objAutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(String year_publication) {
        this.year_publication = year_publication;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public Autor getObjAutor() {
        return objAutor;
    }

    public void setObjAutor(Autor objAutor) {
        this.objAutor = objAutor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year_publication='" + year_publication + '\'' +
                ", price=" + price +
                ", id_author=" + id_author +
                ", objAutor=" + objAutor +
                '}';
    }
}
