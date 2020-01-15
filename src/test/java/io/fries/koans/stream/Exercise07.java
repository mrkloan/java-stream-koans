package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise07 extends OnlineStore {

    /**
     * Create {@link IntStream} with customer ages by using {@link Stream#mapToInt}.
     * Then calculate the average of ages by using {@link IntStream#average}.
     */
    @Test
    void average_age() {
        List<Customer> customerList = mall.getCustomers();

        IntStream ageStream = null;
        OptionalDouble average = null;

        assertThat(average.getAsDouble()).isEqualTo(28.7);
    }

    /**
     * Create {@link LongStream} with all items' prices using {@link Stream#mapToLong}.
     * Then calculate the sum of prices using {@link LongStream#sum}.
     */
    @Test
    void how_much_to_buy_all_items() {
        List<Shop> shopList = mall.getShops();

        LongStream priceStream = null;
        long priceSum = 0;

        assertThat(priceSum).isEqualTo(60930L);
    }
}
