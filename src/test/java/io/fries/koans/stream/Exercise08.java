package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise08 extends OnlineStore {

    /**
     * Create a set of item names that are in {@code Customer.wantsToBuy} but not on sale in any shop.
     */
    @Test
    void items_not_on_sale() {
        Stream<Customer> customerStream = mall.getCustomers().stream();
        Stream<Shop> shopStream = mall.getShops().stream();

        List<String> itemListOnSale = null;
        Set<String> itemSetNotOnSale = null;

        assertThat(itemSetNotOnSale).hasSize(3);
        assertThat(itemSetNotOnSale).containsExactlyInAnyOrder("bag", "pants", "coat");
    }

    /**
     * Create a customer's name list including who are having enough money to buy all items they want which is
     * on sale.
     * Items that are not on sale can be counted as 0 money cost.
     * If there is several same items with different prices, customer can choose the cheapest one.
     */
    @Test
    void having_enough_money() {
        Stream<Customer> customerStream = mall.getCustomers().stream();
        Stream<Shop> shopStream = mall.getShops().stream();

        List<Item> onSale = null;
        Predicate<Customer> havingEnoughMoney = null;
        List<String> customerNameList = null;

        assertThat(customerNameList).hasSize(7);
        assertThat(customerNameList).containsExactly("Joe", "Patrick", "Chris", "Kathy", "Alice", "Andrew", "Amy");
    }
}
