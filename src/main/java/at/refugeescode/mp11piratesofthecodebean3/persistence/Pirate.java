package at.refugeescode.mp11piratesofthecodebean3.persistence;

public class Pirate {

    private Long id;

    private String name;

    private PieceOfEight pieceOfEight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PieceOfEight getPieceOfEight() {
        return pieceOfEight;
    }

    public void setPieceOfEight(PieceOfEight pieceOfEight) {
        this.pieceOfEight = pieceOfEight;
    }
}
