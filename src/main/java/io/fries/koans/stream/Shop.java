package io.fries.koans.stream;

import java.util.List;
import java.util.Objects;

public class Shop {

    private final String name;
    private final List<Item> items;

    public Shop(final String name, final List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) &&
                Objects.equals(items, shop.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, items);
    }
}
