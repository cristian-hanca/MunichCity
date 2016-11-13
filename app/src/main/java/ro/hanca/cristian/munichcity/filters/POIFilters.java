package ro.hanca.cristian.munichcity.filters;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

import ro.hanca.cristian.munichcity.AppContext;
import ro.hanca.cristian.munichcity.models.POI;

/**
 * Managed Filter for the POI view.
 */
public class POIFilters {

    private final List<Map.Entry<Float, POI>> original;
    private final ImmutableMap<Long, String> namesMap;

    private List<Map.Entry<Float, POI>> filtered;

    private String search;

    private boolean invalidate = false;

    public POIFilters(List<Map.Entry<Float, POI>> source) {
        this.original = source;
        this.filtered = Lists.newArrayList(this.original);

        search = "";
        this.invalidate = false;

        ImmutableMap.Builder<Long, String> builder = ImmutableMap.builder();
        for (Map.Entry<Float, POI> e : this.original) {
            builder.put(e.getValue().getId(), e.getValue().getName().toLowerCase());
        }
        namesMap = builder.build();
    }

    public List<Map.Entry<Float, POI>> getFiltered(boolean invalidate) {
        if (invalidate) {
            this.invalidate = true;
        }
        return getFiltered();
    }

    public List<Map.Entry<Float, POI>> getFiltered() {
        if (invalidate) {
            final String searchTerm = search.toLowerCase();
            filtered = Stream.of(original).filter(new Predicate<Map.Entry<Float, POI>>() {
                @Override
                public boolean test(Map.Entry<Float, POI> value) {
                    return namesMap.get(value.getValue().getId()).startsWith(searchTerm);
                }
            }).collect(Collectors.<Map.Entry<Float, POI>>toList());
        }
        return filtered;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.invalidate = true;
        this.search = search;
    }

}
