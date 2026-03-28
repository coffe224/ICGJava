package ICGFilter;

import ICGFilter.core.FilterWrapper;
import ICGFilter.core.FilterManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        FilterManager filterManager = new FilterManager();
        List<FilterWrapper> filters = filterManager.getFilters();

        System.out.println(filters.size());
        for (FilterWrapper filter : filters) {
            System.out.println(filter.getName());
            System.out.println(filter.getDescription() + "\n");
        }
    }
}