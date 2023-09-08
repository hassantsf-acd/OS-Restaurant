public class Table {
    private int id;
    private int occupiedBy;

    public Table(int id) {
        this.id = id;
        this.occupiedBy = -1;
    }

    public int getId() {
        return id;
    }

    public void reserve(int customerId) {
        occupiedBy = customerId;
    }

    public boolean isOccupied() {
        return occupiedBy != -1;
    }

    public int getOccupiedBy() {
        return occupiedBy;
    }

    public void release() {
        occupiedBy = -1;
    }
}