package Utilities;

import java.io.*;
import java.util.*;

public class Data_Utils {
    private static final String FILE_PATH = "src/test/resources/test_data.csv";

    public static Map<String, String> readCustomer(String customerId) {
        Properties prop = new Properties();
        Map<String, String> data = new LinkedHashMap<>();
        try(FileInputStream fis = new FileInputStream(FILE_PATH)) {
            prop.load(fis);
            // loop through properties file and pick those that start with customerId
            for(String key: prop.stringPropertyNames()) {
                if (key.startsWith(customerId + ".")) {
                    String field = key.substring(customerId.length() + 1); // remove customerId
                    data.put(field, prop.getProperty(key));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

   public static void addCustomerInfo(String customerId, String key, String value) {
        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream(FILE_PATH)){
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prop.setProperty(customerId + "." + key, value);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for (String keys: prop.stringPropertyNames()) {
                writer.write(keys + "=" + prop.getProperty(keys));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    public static void writeCustomer(String customerId, Map<String, String> data) {
        Properties prop = new Properties();

        // Load existing properties from file
        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add all entries from the map, prefixing keys with customerId
        for (Map.Entry<String, String> entry : data.entrySet()) {
            prop.setProperty(customerId + "." + entry.getKey(), entry.getValue());
        }

        // Write all properties back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String key : prop.stringPropertyNames()) {
                writer.write(key + "=" + prop.getProperty(key));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> readCustomerFromCSV(String customerId) {
        Map<String, String> data = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine(); // header
            if (line == null) {
                System.out.println("CSV is empty");
                return data;
            }
            String[] headers = line.split(",");
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println("Checking row: " + Arrays.toString(values));
                if (values[0].trim().equals(customerId.trim())) {
                    for (int i = 0; i < headers.length; i++) {
                        data.put(headers[i], values[i]);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeCustomerToCSV(String customerId, Map<String, String> customerData) {
        String filePath = "src/test/resources/test_data.csv";
        List<String[]> allRows = new ArrayList<>();
        boolean found = false;
        String[] headers;

        // Ensure customerId is in the data map
        customerData.put("customerId", customerId);

        // Read existing data
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line == null) {
                throw new IOException("CSV header missing");
            }
            headers = line.split(",");
            allRows.add(headers);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values[0].trim().equals(customerId)) {
                    // Replace the row with new data
                    String[] newRow = new String[headers.length];
                    for (int i = 0; i < headers.length; i++) {
                        newRow[i] = customerData.getOrDefault(headers[i], "");
                    }
                    allRows.add(newRow);
                    found = true;
                } else {
                    allRows.add(values);
                }
            }
        } catch (FileNotFoundException e) {
            // If file does not exist, create header from customerData keys
            headers = customerData.keySet().toArray(new String[0]);
            allRows.add(headers);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (!found) {
            String[] newRow = new String[allRows.get(0).length];
            for (int i = 0; i < newRow.length; i++) {
                newRow[i] = customerData.getOrDefault(allRows.get(0)[i], "");
            }
            allRows.add(newRow);
        }

        // Write back
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : allRows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomerInfoToCSV(String customerId, String key, String value) {
        String filePath = "src/test/resources/test_data.csv";
        List<String[]> allRows = new ArrayList<>();
        int keyIndex = -1;
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line == null) return;
            String[] headers = line.split(",");
            allRows.add(headers);

            // Find or add the key column
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(key)) {
                    keyIndex = i;
                    break;
                }
            }
            if (keyIndex == -1) {
                keyIndex = headers.length;
                headers = Arrays.copyOf(headers, headers.length + 1);
                headers[keyIndex] = key;
                allRows.set(0, headers);
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values[0].trim().equals(customerId)) {
                    values = Arrays.copyOf(values, keyIndex + 1);
                    values[keyIndex] = value;
                    updated = true;
                } else if (values.length < keyIndex + 1) {
                    values = Arrays.copyOf(values, keyIndex + 1);
                }
                allRows.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write back
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : allRows) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
