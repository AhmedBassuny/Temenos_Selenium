// java
package Utilities;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class Reporter {
    private static final DateTimeFormatter TS = DateTimeFormatter
            .ofPattern("uuuu-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    private static Reporter instance;

    private final boolean toConsole;
    private final boolean toFile;
    private final BufferedWriter writer;
    private final Object lock = new Object();

    private Reporter(boolean toConsole, boolean toFile, Path file) throws IOException {
        this.toConsole = toConsole;
        this.toFile = toFile;
        if (toFile) {
            Path parent = Objects.requireNonNull(file).toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
            this.writer = Files.newBufferedWriter(file,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE);
        } else {
            this.writer = null;
        }
    }

    public static Reporter init() throws IOException {
        return init(true, true, Path.of("target", "reports", "report.log"));
    }

    public static Reporter init(boolean toConsole, boolean toFile, Path file) throws IOException {
        synchronized (Reporter.class) {
            if (instance == null) {
                instance = new Reporter(toConsole, toFile, file);
            }
            return instance;
        }
    }

    public static Reporter get() {
        synchronized (Reporter.class) {
            if (instance == null) {
                throw new IllegalStateException("Reporter not initialized. Call Reporter.init(...) first.");
            }
            return instance;
        }
    }

    private String timestamp() {
        return TS.format(Instant.now());
    }

    private void write(String line) {
        if (toConsole) {
            System.out.println(line);
        }
        if (toFile) {
            synchronized (lock) {
                try {
                    writer.write(line);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    // fall back to console if file fails
                    System.err.println("Reporter: failed to write to file: " + e.getMessage());
                }
            }
        }
    }

    public void info(String msg) {
        write(format("INFO", msg));
    }

    public void warn(String msg) {
        write(format("WARN", msg));
    }

    public void error(String msg) {
        write(format("ERROR", msg));
    }

    private String format(String level, String msg) {
        return String.format("%s [%s] %s", timestamp(), level, msg);
    }

    public void reportMap(Map<String, String> map, String title) {
        write(format("REPORT", "---- " + title + " ----"));
        map.forEach((k, v) -> write(format("REPORT", k + " = " + v)));
        write(format("REPORT", "---- end " + title + " ----"));
    }

    public void close() {
        if (toFile && writer != null) {
            synchronized (lock) {
                try {
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        }
        synchronized (Reporter.class) {
            instance = null;
        }
    }
}
