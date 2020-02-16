package readerblog.mates.readerblog.enums;

public enum StatusOfQueryOrFeedback {
    ACTIVE              ("ACTIVE"),
    ALL_HIDDEN          ("ALL_HIDDEN"),
    SOME_PART_HIDDEN    ("SOME_PART_HIDDEN"),
    WAIT_TO_REMOVE      ("WAIT_TO_REMOVE");

    private String name;

    StatusOfQueryOrFeedback(String name) {
        this.name = name;
    }
}
