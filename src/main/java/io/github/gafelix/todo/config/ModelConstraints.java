package io.github.gafelix.todo.config;

public class ModelConstraints {

    /* User model constraints. These fields are intended to externalize configuration */

    public static final byte USERNAME_MIN_SIZE = 3;
    public static final byte USERNAME_MAX_SIZE = 12;
    public static final byte USERNAME_MAX_TABLES = 10;

    public static final byte EMAIL_MIN_SIZE = 5;
    public static final byte EMAIL_MAX_SIZE = 64;

    public static final byte PASSWORD_MIN_SIZE = 6;
    public static final byte PASSWORD_MAX_SIZE = 32;

    /* Card model constraints. */
    public static final byte CARD_MAX_ASSIGNEES = 5;
    public static final byte CARD_TITLE_MIN_LENGTH = 5;
    public static final byte CARD_TITLE_MAX_LENGTH = 32;
    public static final byte CARD_DESCRIPTION_MIN_LENGTH = 8;
    public static final short CARD_DESCRIPTION_MAX_LENGTH = 512;

    /* Table model constraints. */
    public static final byte TABLE_MAX_COLUMNS = 8;
    public static final byte TABLE_LABEL_MAX_LENGTH = 8;
    public static final byte TABLE_MAX_ID_SIZE = 64;

}