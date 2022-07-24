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
    static int count;
    int id = count++;
    double price;
    String name;

}
