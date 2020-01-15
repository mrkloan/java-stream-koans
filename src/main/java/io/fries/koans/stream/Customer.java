package io.fries.koans.stream;

import java.util.List;
import java.util.Objects;

class Customer {

    private final String name;
    private final Integer age;
    private final Integer budget;
    private final List<Item> wantsToBuy;

    Customer(final String name, final Integer age, final Integer budget, final List<Item> wantsToBuy) {
        this.name = name;
        this.age = age;
        this.budget = budget;
        this.wantsToBuy = wantsToBuy;
    }

    String getName() {
        return name;
    }

    Integer getAge() {
        return age;
    }

    Integer getBudget() {
        return budget;
    }

    List<Item> getWantsToBuy() {
        return wantsToBuy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(age, customer.age) &&
                Objects.equals(budget, customer.budget) &&
                Objects.equals(wantsToBuy, customer.wantsToBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, budget, wantsToBuy);
    }
}
