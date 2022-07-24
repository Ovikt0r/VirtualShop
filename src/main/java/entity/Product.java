package entity;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Setter
@ToString
public class Product {
    private static int count = 0;
    private int id = ++count;
    private double price;
    private String name;

}
