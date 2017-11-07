package ru.kpfu.univer.service.models;

import java.util.Map;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class Feature {
    private String feature;
    private Map<Category, Integer> map;

    public Feature(String feature) {
        this.feature = feature;
    }

    public Feature(String feature, Map<Category, Integer> map) {
        this.feature = feature;
        this.map = map;
    }

    public void addCategory(Category category) {
        this.map.put(category, 0);
    }

    public void incrementCategory(Category category) {
        this.map.put(
                category,
                this.map.get(category) + 1
        );
    }

    public String getFeature() {
        return feature;
    }

    public Map<Category, Integer> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "feature='" + feature + '\'' +
                ", map=" + map +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature1 = (Feature) o;

        return feature != null ? feature.equals(feature1.feature) : feature1.feature == null;
    }

    @Override
    public int hashCode() {
        return feature != null ? feature.hashCode() : 0;
    }
}
