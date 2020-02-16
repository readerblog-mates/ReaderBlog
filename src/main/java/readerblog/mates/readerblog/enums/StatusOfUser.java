package readerblog.mates.readerblog.enums;

public enum StatusOfUser {
    ACTIVE              ("ACTIVE"),
    HAS_SOME_WARNING    ("HAS_SOME_WARNING"),
    CAN_ONLY_READ       ("CAN_ONLY_READ"),
    BANNED              ("BANNED");

    private String name;

    StatusOfUser(String name) {
        this.name = name;
    }
}