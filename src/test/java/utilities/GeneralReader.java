package utilities;

import hooks.Hook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class GeneralReader {

    public static final String CONFIG_FILE = "Configuration.properties";
    public static final String CREDENTIALS_FILE = "Credentials.properties";

    public static String getMessageFile() {
        return "Message_" + Hook.getInstance().getLanguageType() + ".properties";
    }


    private GeneralReader() {
    }


    private static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            properties.load(reader);
        } catch (IOException e) {
            System.err.println("Error loading file: " + filePath);
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String filePath, String key) {
        Properties properties = loadProperties(filePath);
        return properties.getProperty(key);
    }

    public static String getBaseUrl(String key) {
        Properties properties = loadProperties(CONFIG_FILE);
        return properties.getProperty(key).replace("${url}", properties.getProperty("URL"));
    }

    public static String getFormattedProperty(String filePath, String key, String placeholder, String replacement) {
        String value = getProperty(filePath, key);
        if (value != null && placeholder != null && replacement != null) {
            return value.replace(placeholder, replacement);
        }
        return value;
    }

    /**
     * Adds a new property or updates an existing one in the properties file
     *
     * @param filePath Path to the properties file
     * @param key      Key to be updated/added
     * @param value    New value
     * @return true if operation succeeded, false otherwise
     */
    public static void setProperty(String filePath, String key, String value) {
        Path path = Paths.get(filePath);
        List<String> fileContent;

        try {
            // Dosyayı satır satır oku
            fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));

            boolean keyExists = false;

            // Her satırı kontrol et
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i).trim();

                // Yorum satırlarını ve boş satırları atla
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }

                // Anahtar-değer çiftlerini ayır
                String[] keyValue = line.split("=", 2);
                if (keyValue.length >= 1 && keyValue[0].trim().equals(key)) {
                    // Anahtar bulundu, değeri güncelle
                    fileContent.set(i, key + "=" + value);
                    keyExists = true;
                    break;
                }
            }

            // Anahtar yoksa dosyanın sonuna ekle
            if (!keyExists) {
                fileContent.add(key + "=" + value);
            }

            // Dosyayı yeniden yaz
            Files.write(path, fileContent, StandardCharsets.UTF_8);

        } catch (IOException e) {
            System.err.println("Error updating properties file: " + e.getMessage());
        }
    }

    /**
     * Removes a property from the properties file
     *
     * @param filePath Path to the properties file
     * @param key      Key to be removed
     * @return true if operation succeeded, false otherwise
     */
    public static boolean removeProperty(String filePath, String key) {
        Properties properties = loadProperties(filePath);
        if (properties == null) {
            return false;
        }

        properties.remove(key);

        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream(filePath), StandardCharsets.UTF_8)) {
            properties.store(writer, null);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
            return false;
        }
    }
}
