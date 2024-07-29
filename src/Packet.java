public class Packet {
    private String data;
    private String source;
    private String destination;

    public Packet(String data, String source, String destination) {
        this.data = data;
        this.source = source;
        this.destination = destination;
    }

    public String getData() {
        return data;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}



