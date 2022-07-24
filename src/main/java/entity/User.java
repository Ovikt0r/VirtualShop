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
    static int count = 1;
    int id = count++;
    String firstname;
    String lastname;
    double money;
}


