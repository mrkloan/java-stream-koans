package io.fries.koans.stream;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

import static java.util.Objects.requireNonNull;

class OnlineStore {

    private static final InputStream inventory = OnlineStore.class.getClassLoader().getResourceAsStream("inventory.json");
    static final Mall mall = new Gson().fromJson(new InputStreamReader(requireNonNull(inventory)), Mall.class);

    static <T> boolean isLambda(T reference) {
        return reference.getClass().getSimpleName().contains("$$Lambda");
    }
}
