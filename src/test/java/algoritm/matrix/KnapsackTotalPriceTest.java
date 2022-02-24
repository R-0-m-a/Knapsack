package algoritm.matrix;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnapsackTotalPriceTest {

  private static Stream<Arguments> provideDataTotalPrice() {
    return Stream.of(
        Arguments.of(250, List.of(175, 133, 109, 210, 97), List.of(200, 125, 128, 228, 133), 55),
        Arguments.of(500, List.of(150, 199, 200, 168, 153), List.of(140, 175, 199, 121, 111), 0),
        Arguments.of(30, List.of(1, 2, 4, 6), List.of(5, 3, 5, 6), 6));
  }

  @ParameterizedTest
  @MethodSource("provideDataTotalPrice")
  void selectStock(int saving, List<Integer> currentValue, List<Integer> futureValue, int result) {
    assertEquals(result, KnapsackTotalPrice.selectStock(saving, currentValue, futureValue));
  }
}