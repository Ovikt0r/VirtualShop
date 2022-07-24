package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Setter
@Getter
@ToString

public class User {
    private static int count = 0;
    private int id = ++count;
    private String firstName;
    private String lastName;
    private double money;
}


