package algoritm;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Stock {
  private String name;
  private int currentPrice;
  private int futurePrice;
}