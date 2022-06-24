import ueb9.Punkt;

public class Knoten {
    Knoten left;
    Punkt punkt;
    Knoten right;
    boolean x;

    public Knoten(Knoten left, Punkt punkt, Knoten right, boolean x) {
        this.left = left;
        this.punkt = punkt;
        this.right = right;
        this.x = x;
    }

    public Knoten getLeft() {
        return left;
    }

    public void setLeft(Knoten left) {
        this.left = left;
    }

    public Punkt getPunkt() {
        return punkt;
    }

    public void setPunkt(Punkt punkt) {
        this.punkt = punkt;
    }

    public Knoten getRight() {
        return right;
    }

    public void setRight(Knoten right) {
        this.right = right;
    }

    public boolean isX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
    }
}
