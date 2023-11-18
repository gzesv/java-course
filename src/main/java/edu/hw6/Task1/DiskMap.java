package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final Map<String, String> map;
    public final Path path;
    public static final String SEPARATOR = ":";

    public DiskMap(Path path) {
        this.path = path;
        this.map = new HashMap<>();
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path.toFile()))) {
            map.forEach((key, value) -> writer.println(key + SEPARATOR + value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void read() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            clear();
            bufferedReader.lines()
                .map(line -> line.split(SEPARATOR))
                .forEach(line -> map.put(line[0], line[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
