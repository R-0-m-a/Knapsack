package algoritm.matrix;

import static org.junit.jupiter.api.Assertions.*;

import algoritm.Stock;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnapsackWithObjectTest {

  private static Stream<Arguments> provideData() {
    return Stream.of(
        Arguments.of(
            250,
            List.of(
                Stock.builder().name("Apple").currentPrice(175).futurePrice(200).build(),
                Stock.builder().name("Google").currentPrice(133).futurePrice(125).build(),
                Stock.builder().name("Amazon").currentPrice(109).futurePrice(128).build(),
                Stock.builder().name("Facebook").currentPrice(210).futurePrice(228).build(),
                Stock.builder().name("VK").currentPrice(97).futurePrice(133).build()),
            55),
        Arguments.of(
            500,
            List.of(
                Stock.builder().name("Apple").currentPrice(150).futurePrice(140).build(),
                Stock.builder().name("Google").currentPrice(199).futurePrice(175).build(),
                Stock.builder().name("Amazon").currentPrice(200).futurePrice(199).build(),
                Stock.builder().name("Facebook").currentPrice(168).futurePrice(121).build(),
                Stock.builder().name("VK").currentPrice(153).futurePrice(111).build()),
            0),
        Arguments.of(
            30,
            List.of(
                Stock.builder().name("Apple").currentPrice(1).futurePrice(5).build(),
                Stock.builder().name("Google").currentPrice(2).futurePrice(3).build(),
                Stock.builder().name("Amazon").currentPrice(4).futurePrice(5).build(),
                Stock.builder().name("Facebook").currentPrice(6).futurePrice(6).build()),
            6));
  }

  @ParameterizedTest
  @MethodSource("provideData")
  void selectStock(int saving, List<Stock> stocks, int expectedResult) {

    var result = KnapsackWithObject.selectStock(saving, stocks);
    result.stream().forEach(System.out::println);

    int profitByStocks = KnapsackWithObject.getProfitByStocks(result);
    System.out.println("Total profit -- " + profitByStocks );

    assertEquals(expectedResult, profitByStocks);
  }
}