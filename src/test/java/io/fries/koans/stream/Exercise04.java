package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise04 extends OnlineStore {

    /**
     * Find the first customer who registered this online store by using {@link Stream#findFirst}.
     * The customerList are ascending ordered by registered timing.
     */
    @Test
    void first_registrant() {
        List<Customer> customerList = mall.getCustomers();

        Optional<Customer> firstCustomer = null;

        assertThat(firstCustomer.get()).isEqualTo(customerList.get(0));
    }

    /**
     * Check whether any customer older than 40 exists or not, by using {@link Stream#anyMatch}.
     */
    @Test
    void is_there_anyone_older_than_40() {
        List<Customer> customerList = mall.getCustomers();

        boolean olderThan40Exists = true;

        assertThat(olderThan40Exists).isFalse();
    }

    /**
     * Check whether all customer are older than 20 or not, by using {@link Stream#allMatch}.
     */
    @Test
    void is_everybody_older_than_20() {
        List<Customer> customerList = mall.getCustomers();

        boolean allOlderThan20 = false;

        assertThat(allOlderThan20).isTrue();
    }

    /**
     * Confirm that none of the customer has empty list for their {@code Customer.wantsToBuy}
     * by using {@link Stream#noneMatch}.
     */
    @Test
    void everyone_wants_something() {
        List<Customer> customerList = mall.getCustomers();

        boolean everyoneWantsSomething = false;

        assertThat(everyoneWantsSomething).isTrue();
    }
}
