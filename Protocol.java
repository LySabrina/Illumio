/**
 * ENUM to represent the known protocol and its values
 */
public enum Protocol {
    TCP(6),
    UDP(17),
    ICMP(1);

    private int value;

    private Protocol(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Protocol getFromNum(int num) {
        for (Protocol p : Protocol.values()) {
            if (p.getValue() == num) {
                return p;
            }
        }
        return null;
    }

}
