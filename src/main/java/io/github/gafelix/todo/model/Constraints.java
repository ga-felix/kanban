package io.github.gafelix.todo.model;

class Constraints {

    /* User model constraints. These fields are intended to externalize configuration */

    static final short USERNAME_MIN_SIZE = 3;
    static final short USERNAME_MAX_SIZE = 12;

    static final short EMAIL_MIN_SIZE = 5;
    static final short EMAIL_MAX_SIZE = 64;

    static final short PASSWORD_MIN_SIZE = 6;
    static final short PASSWORD_MAX_SIZE = 32;

}