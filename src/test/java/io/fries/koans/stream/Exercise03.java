package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise03 extends OnlineStore {

    /**
     * Count how many items there are in {@code Customer.wantsToBuy} using {@link Stream#count}.
     */
    @Test
    void how_many_items_wanted() {
        List<Customer> customerList = mall.getCustomers();

        long sum = customerList.stream()
                .flatMap(customer -> customer.getWantsToBuy().stream())
                .count();

        // Autre implémentation possible
//        long sum = customerList.stream()
//                .mapToLong(customer -> customer.getWantsToBuy().size())
//                .sum();

        assertThat(sum).isEqualTo(32L);
    }

    /**
     * Find the richest customer's budget by using {@link Stream#max} and {@link Comparator#naturalOrder}.
     * Don't use {@link Stream#sorted}.
     */
    @Test
    void richest_customer() {
        List<Customer> customerList = mall.getCustomers();

        Comparator<Integer> comparator = Comparator.naturalOrder();
        Optional<Integer> richestCustomer = customerList.stream()
                .map(Customer::getBudget)
                .max(comparator);

        assertThat(comparator.getClass().getSimpleName()).isEqualTo("NaturalOrderComparator");
        assertThat(richestCustomer.get()).isEqualTo(12000);
    }

    /**
     * Find the youngest customer by using {@link Stream#min}.
     * Don't use {@link Stream#sorted}.
     */
    @Test
    void youngest_customer() {
        List<Customer> customerList = mall.getCustomers();

        Comparator<Customer> comparator = Comparator.comparing(Customer::getAge);
        Optional<Customer> youngestCustomer = customerList.stream()
                .min(comparator);

        assertThat(youngestCustomer.get()).isEqualTo(customerList.get(8));
    }
}
