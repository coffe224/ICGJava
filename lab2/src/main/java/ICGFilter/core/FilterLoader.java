package ICGFilter.core;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FilterLoader {
    List<FilterInfo> filtersInfo = new ArrayList<>();
    Map<String, Filter> filters = new HashMap<>();

    public FilterLoader() {
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
                String iconPath = properties.getProperty("filter." + key + ".icon");
                filtersInfo.add(new FilterInfo(name, description, iconPath));

                String className = properties.getProperty("filter." + key + ".class");
                Filter filter = (Filter) Class.forName(className).getDeclaredConstructor().newInstance();
                filters.put(name, filter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FilterInfo> getFiltersInfo() {
        return filtersInfo;
    }

    public Map<String, Filter> getFilters() {
        return filters;
    }
}
