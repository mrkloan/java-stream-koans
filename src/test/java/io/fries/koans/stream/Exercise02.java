package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise02 extends OnlineStore {

    /**
     * Create a stream with ascending ordered age values.
     * Use {@link Stream#sorted} to sort them.
     */
    @Test
    void sort_by_age() {
        List<Customer> customerList = mall.getCustomers();

        Stream<Integer> sortedAgeStream = customerList.stream()
                .map(Customer::getAge)
                .sorted();

        List<Integer> sortedAgeList = sortedAgeStream.collect(Collectors.toList());
        assertThat(sortedAgeList).containsExactly(21, 22, 22, 26, 27, 28, 32, 35, 36, 38);
    }

    /**
     * Create a stream with descending ordered age values.
     */
    @Test
    void desc_sort_by_age() {
        List<Customer> customerList = mall.getCustomers();

        Comparator<Integer> descOrder = (c1, c2) -> c2.compareTo(c1);
        Stream<Integer> sortedAgeStream = customerList.stream()
                .map(Customer::getAge)
                .sorted(descOrder);

        assertThat(isLambda(descOrder)).isTrue();
        List<Integer> sortedAgeList = sortedAgeStream.collect(Collectors.toList());
        assertThat(sortedAgeList).containsExactly(38, 36, 35, 32, 28, 27, 26, 22, 22, 21);
    }

    /**
     * Create a stream with top 3 rich customers using {@link Stream#limit} to limit the size of the stream.
     */
    @Test
    void top_3_rich_customer() {
        List<Customer> customerList = mall.getCustomers();

        Stream<String> top3RichCustomerStream = customerList.stream()
                .sorted(Comparator.comparing(Customer::getBudget).reversed())
                .map(Customer::getName)
                .limit(3);

        List<String> top3RichCustomerList = top3RichCustomerStream.collect(Collectors.toList());
        assertThat(top3RichCustomerList).containsExactly("Diana", "Andrew", "Chris");
    }

    /**
     * Create a stream with distinct age values using {@link Stream#distinct}.
     */
    @Test
    void distinct_age() {
        List<Customer> customerList = mall.getCustomers();

        Stream<Integer> distinctAgeStream = customerList.stream()
                .map(Customer::getAge)
                .distinct();

        List<Integer> distinctAgeList = distinctAgeStream.collect(Collectors.toList());
        assertThat(distinctAgeList).containsExactly(22, 27, 28, 38, 26, 32, 35, 21, 36);
    }

    /**
     * Create a stream with items' names stored in {@code Customer.wantsToBuy}.
     * Use {@link Stream#flatMap} to create a stream from each element of a stream.
     */
    @Test
    void items_customers_want_to_buy() {
        List<Customer> customerList = mall.getCustomers();

        Function<Customer, Stream<Item>> getItemStream = customer -> customer.getWantsToBuy().stream();
        Stream<String> itemStream = customerList.stream()
                .flatMap(getItemStream)
                .map(Item::getName);

        assertThat(isLambda(getItemStream)).isTrue();
        List<String> itemList = itemStream.collect(Collectors.toList());
        assertThat(itemList).containsExactly(
                "small table", "plate", "fork", "ice cream", "screwdriver", "cable", "earphone", "onion",
                "ice cream", "crisps", "chopsticks", "cable", "speaker", "headphone", "saw", "bond",
                "plane", "bag", "cold medicine", "chair", "desk", "pants", "coat", "cup", "plate", "fork",
                "spoon", "ointment", "poultice", "spinach", "ginseng", "onion"
        );
    }
}
