package algoritm.matrix;

import algoritm.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KnapsackWithObject {

  private KnapsackWithObject() {
    throw new IllegalStateException("Utility class");
  }

  public static List<Stock> selectStock(int budget, List<Stock> stocks) {
    List<Stock> profitableStocks =
        stocks.stream()
            .filter(stock -> stock.getFuturePrice() - stock.getCurrentPrice() > 0)
            .collect(Collectors.toList());

    List<Stock>[][] result = initResultMatrix(profitableStocks.size() + 1, budget + 1);

    for (int i = 1; i <= profitableStocks.size(); i++) {
      Stock stock = profitableStocks.get(i - 1);
      int profit = stock.getFuturePrice() - stock.getCurrentPrice();
      int price = stock.getCurrentPrice();

      for (int j = 1; j <= budget; j++) {

        List<Stock> prevStock = result[i - 1][j];

        if (price > j) {
          result[i][j] = prevStock;
        } else {
          List<Stock> extraStock = result[i - 1][j - price];

          var prev = getProfitByStocks(prevStock);
          var extra = getProfitByStocks(extraStock);

          if (extra + profit > prev) {
            result[i][j] = new ArrayList<>(extraStock);
            result[i][j].add(stock);
          } else {
            result[i][j] = prevStock;
          }
        }
      }
    }
    return result[profitableStocks.size()][budget];
  }

  @SuppressWarnings("unchecked")
  private static List<Stock>[][] initResultMatrix(int n, int m) {
    List<Stock>[][] result = new ArrayList[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        result[i][j] = new ArrayList<>();
      }
    }
    return result;
  }

  public static int getProfitByStocks(List<Stock> stocks) {
    if (stocks == null) {
      return 0;
    }
    return stocks.stream()
        .mapToInt(value -> value.getFuturePrice() - value.getCurrentPrice())
        .sum();
  }
}
