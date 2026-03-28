package ICGFilter.core;

import java.awt.image.ImageFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FilterManager {
    List<FilterWrapper> filters = new ArrayList<>();

    public FilterManager() {
        loadFilters();
    }

    private void loadFilters() {
        Properties properties = new Properties();
        try (InputStream is = getClass().getResourceAsStream("/filters.properties")) {
            Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
            properties.load(reader);
            String[] filterKeys = properties.getProperty("filters").split(",");
            for (String key : filterKeys) {
                String name = properties.getProperty("filter." + key + ".name");
                String description = properties.getProperty("filter." + key + ".description");
                String iconPath = properties.getProperty("filter." + key + ".iconPath");

                String className = properties.getProperty("filter." + key + ".class");
                Filter filter = (Filter) Class.forName(className).getDeclaredConstructor().newInstance();

                FilterWrapper wrapper = new FilterWrapper(filter, name, description, iconPath);
                filters.add(wrapper);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // открытие и парсинг filters.properties
        // создание классов FilterWrapper
    }

    public List<FilterWrapper> getFilters() {
        return filters;
    }
}
