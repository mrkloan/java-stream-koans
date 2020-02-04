package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static org.assertj.core.api.Assertions.assertThat;

class Exercise09 extends OnlineStore {

    /**
     * Implement a {@link Collector} which can create a String with comma separated names shown in the assertion.
     * The collector will be used by serial stream.
     */
    @Test
    void simplest_string_join() {
        List<Customer> customerList = mall.getCustomers();

        Supplier<StringBuilder> supplier = StringBuilder::new;
        BiConsumer<StringBuilder, String> accumulator = (acc, name) -> acc.append(acc.length() == 0 ? name : "," + name);
        BinaryOperator<StringBuilder> combiner = StringBuilder::append;
        Function<StringBuilder, String> finisher = StringBuilder::toString;

        Collector<String, ?, String> toCsv = new SimpleCollector<>(
                supplier,
                accumulator,
                combiner,
                finisher,
                Collections.emptySet()
        );
        String nameAsCsv = customerList.stream().map(Customer::getName).collect(toCsv);
        assertThat(nameAsCsv).isEqualTo("Joe,Steven,Patrick,Diana,Chris,Kathy,Alice,Andrew,Martin,Amy");
    }

    /**
     * Implement a {@link Collector} which can create a {@link Map} with keys as item and values as {@link Set}
     * of customers who are wanting to buy that item.
     * The collector will be used by parallel stream.
     */
    @Test
    void map_keyed_by_items() {
        List<Customer> customerList = mall.getCustomers();

        Supplier<Map<String, Set<String>>> supplier = ConcurrentHashMap::new;

        BiConsumer<Map<String, Set<String>>, Customer> accumulator = (acc, customer) -> customer
                .getWantsToBuy()
                .forEach(item -> acc
                        .computeIfAbsent(item.getName(), key -> new HashSet<>())
                        .add(customer.getName())
                );

        BinaryOperator<Map<String, Set<String>>> combiner = (first, second) -> Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (firstCustomers, secondCustomers) -> {
                            firstCustomers.addAll(secondCustomers);
                            return firstCustomers;
                        }
                ));

        // Étant donné que notre collecteur possède la caractéristique `IDENTITY_FINISH`, le `finisher` ne sera pas utilisé.
        Function<Map<String, Set<String>>, Map<String, Set<String>>> finisher = null;

        Collector<Customer, ?, Map<String, Set<String>>> toItemAsKey = new SimpleCollector<>(
                supplier,
                accumulator,
                combiner,
                finisher,
                EnumSet.of(CONCURRENT, IDENTITY_FINISH)
        );
        Map<String, Set<String>> itemMap = customerList.stream().parallel().collect(toItemAsKey);
        assertThat(itemMap.get("plane")).containsExactlyInAnyOrder("Chris");
        assertThat(itemMap.get("onion")).containsExactlyInAnyOrder("Patrick", "Amy");
        assertThat(itemMap.get("ice cream")).containsExactlyInAnyOrder("Patrick", "Steven");
        assertThat(itemMap.get("earphone")).containsExactlyInAnyOrder("Steven");
        assertThat(itemMap.get("plate")).containsExactlyInAnyOrder("Joe", "Martin");
        assertThat(itemMap.get("fork")).containsExactlyInAnyOrder("Joe", "Martin");
        assertThat(itemMap.get("cable")).containsExactlyInAnyOrder("Diana", "Steven");
        assertThat(itemMap.get("desk")).containsExactlyInAnyOrder("Alice");
    }

    /**
     * Create a {@link String} of "n"th bit ON.
     * For example:
     * "1" will be "1"
     * "3" will be "001"
     * "5" will be "00001"
     * "1,3,5" will be "10101"
     * "1-3" will be "111"
     * "7,1-3,5" will be "1110101"
     */
    @Test
    void bit_list_to_bit_string() {
        String bitList = "22-24,9,42-44,11,4,46,14-17,5,2,38-40,33,50,48";

        Supplier<List<Integer>> supplier = ArrayList::new;

        BiConsumer<List<Integer>, String> accumulator = (acc, rawString) -> {
            List<String> tokens = List.of(rawString.split("-"));
            List<Integer> indexes = tokens.stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            if (indexes.size() == 1) {
                acc.add(indexes.get(0));
            } else if (indexes.size() == 2) {
                final int lowerBound = indexes.get(0);
                final Integer upperBound = indexes.get(1);

                acc.addAll(Stream.iterate(lowerBound, index -> index + 1)
                        .limit(upperBound - lowerBound + 1)
                        .collect(Collectors.toList())
                );
            } else {
                throw new IllegalStateException("A range cannot contain more than 2 values");
            }
        };

        BinaryOperator<List<Integer>> combiner = null;

        Function<List<Integer>, String> finisher = acc -> {
            long max = acc.stream()
                    .max(Comparator.naturalOrder())
                    .orElseThrow(() -> new IllegalStateException("Accumulator should not be empty on the finishing stage"));

            List<String> bitString = Stream.generate(() -> "0")
                    .limit(max)
                    .collect(Collectors.toList());

            acc.forEach(index -> bitString.set(index - 1, "1"));

            return String.join("", bitString);
        };

        Collector<String, List<Integer>, String> toBitString = new SimpleCollector<>(
                supplier,
                accumulator,
                combiner,
                finisher,
                Collections.emptySet()
        );

        String bitString = Arrays.stream(bitList.split(",")).collect(toBitString);
        assertThat(bitString).isEqualTo("01011000101001111000011100000000100001110111010101");
    }
}
