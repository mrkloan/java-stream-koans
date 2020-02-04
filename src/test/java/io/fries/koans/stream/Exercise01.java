package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise01 extends OnlineStore {

    /**
     * Create a {@link Stream} from customerList only including customer who has more budget than 10000.
     * Use lambda expression for {@link Predicate} and {@link Stream#filter} for filtering.
     */
    @Test
    void find_rich_customers() {
        List<Customer> customerList = mall.getCustomers();

        Predicate<Customer> richCustomerCondition = null;
        Stream<Customer> richCustomerStream = null;

        assertThat(isLambda(richCustomerCondition)).isTrue();
        List<Customer> richCustomer = richCustomerStream.collect(Collectors.toList());
        assertThat(richCustomer).hasSize(2);
        assertThat(richCustomer).containsExactly(customerList.get(3), customerList.get(7));
    }

    /**
     * Create a {@link Stream} from customerList with age values.
     * Use method reference(best) or lambda expression(okay) for creating {@link Function} which will
     * convert {@link Customer} to {@link Integer}, and then apply it by using {@link Stream#map}.
     */
    @Test
    void how_old_are_the_customers() {
        List<Customer> customerList = mall.getCustomers();

        Function<Customer, Integer> getAgeFunction = null;
        Stream<Integer> ageStream = null;

        assertThat(isLambda(getAgeFunction)).isTrue();
        List<Integer> ages = ageStream.collect(Collectors.toList());
        assertThat(ages).hasSize(10);
        assertThat(ages).containsExactly(22, 27, 28, 38, 26, 22, 32, 35, 21, 36);
    }
}
