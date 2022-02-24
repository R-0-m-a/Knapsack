package algoritm.matrix;

import java.util.ArrayList;
import java.util.List;

public class KnapsackTotalPrice {

  private KnapsackTotalPrice() {
    throw new IllegalStateException("Utility class");
  }

  /*
   * Complete the 'selectStock' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER saving
   *  2. INTEGER_ARRAY currentValue
   *  3. INTEGER_ARRAY futureValue
   */

  public static int selectStock(int saving, List<Integer> currentValue, List<Integer> futureValue) {
    // Write your code here
    List<Integer> profit = new ArrayList<>();
    int n = currentValue.size();
    int[][] matrix = new int[n + 1][saving + 1];

    for (int i = 0; i < n; i++) {
      profit.add(futureValue.get(i) - currentValue.get(i));
    }

    for (int i = 1; i <= n; i++) {
      Integer value = currentValue.get(i - 1);
      Integer profitValue = profit.get(i - 1);

      for (int j = 1; j <= saving; j++) {
        if (value <= j) {
          matrix[i][j] = Math.max(matrix[i - 1][j], profitValue + matrix[i - 1][j - value]);
        } else {
          matrix[i][j] = matrix[i - 1][j];
        }
      }
    }
    return matrix[n][saving];
  }
}
