package io.fries.koans.stream;

import java.util.List;
import java.util.Objects;

public class Mall {

    private final List<Shop> shops;
    private final List<Customer> customers;

    public Mall(final List<Shop> shops, final List<Customer> customers) {
        this.shops = shops;
        this.customers = customers;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Mall mall = (Mall) o;
        return Objects.equals(shops, mall.shops) &&
                Objects.equals(customers, mall.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shops, customers);
    }
}
