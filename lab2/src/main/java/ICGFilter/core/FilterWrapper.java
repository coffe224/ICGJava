package ICGFilter.core;

public class FilterWrapper {
    private final Filter filter;
    private final String name;
    private final String description;
    private final String iconPath;

    public FilterWrapper(Filter filter, String name, String description, String iconPath) {
        this.filter = filter;
        this.name = name;
        this.description = description;
        this.iconPath = iconPath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public Filter getFilter() {
        return filter;
    }
}
