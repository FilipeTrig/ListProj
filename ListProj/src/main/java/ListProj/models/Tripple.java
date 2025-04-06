package ListProj.models;

public class Tripple {
    private String date;
    private int entries;
    private int weight;

    public Tripple() {
    }

    public Tripple(String date, int entries, int weight) {
        this.date = date;
        this.entries = entries;
        this.weight = weight;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getEntries() {
        return entries;
    }
    public void setEntries(int entries) {
        this.entries = entries;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }    
    @Override
    public String toString() {
        return "Tripple{" +
                "date='" + date + '\'' +
                ", entries=" + entries +
                ", weight=" + weight +
                '}';
    }
}
