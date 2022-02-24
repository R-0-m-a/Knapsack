package algoritm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Knapsack {

  private Knapsack() {
    throw new IllegalStateException("Utility class");
  }

  @SuppressWarnings("unchecked")
  public static List<Stock> selectStock(int budget, List<Stock> stocks) {
    List<Stock> profitableStocks =
        stocks.stream()
            .filter(stock -> stock.getFuturePrice() - stock.getCurrentPrice() > 0)
            .collect(Collectors.toList());

    List<Stock>[] result = new ArrayList[budget + 1];
    Arrays.fill(result, new ArrayList<Stock>());

    for (int i = 1; i <= profitableStocks.size(); i++) {

      Stock stock = profitableStocks.get(i - 1);
      int price = stock.getCurrentPrice();

      for (int j = budget; j >= price; j--) {

        var prevProfit = getProfitByStocks(result[j]);
        var extraProfit = getProfitByStocks(result[j - price]);

        if (prevProfit < extraProfit + stock.getFuturePrice() - stock.getCurrentPrice()) {
          result[j] = new ArrayList<>(result[j - price]);
          result[j].add(stock);
        }
      }
    }
    return result[budget];
  }

  public static int getProfitByStocks(List<Stock> stocks) {
    return stocks.stream()
        .mapToInt(value -> value.getFuturePrice() - value.getCurrentPrice())
        .sum();
  }
}
