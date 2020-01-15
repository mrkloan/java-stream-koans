package io.fries.koans.stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Exercise06 extends OnlineStore {

    /**
     * Create a stream with string values "a" "b" "c" by using {@link Stream#of}.
     */
    @Test
    void stream_from_values() {
        Stream<String> abcStream = null;

        List<String> abcList = abcStream.collect(Collectors.toList());
        assertThat(abcList).contains("a", "b", "c");
    }

    /**
     * Create a stream only with multiples of 3, starting from 0, size of 10, by using {@link Stream#iterate}.
     */
    @Test
    void number_stream() {
        Stream<Integer> numbers = null;

        List<Integer> numbersList = numbers.collect(Collectors.toList());
        assertThat(numbersList).contains(0, 3, 6, 9, 12, 15, 18, 21, 24, 27);
    }
}
