package ru.kpfu.univer.service.models;

import java.util.List;
import java.util.Optional;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
public class FeatureSet {
    private List<Feature> features;

    public FeatureSet(List<Feature> features) {
        this.features = features;
    }

    public void addFeature(Feature feature) {
        final Optional<Feature> feature1 = this.findFeature(feature.getFeature());
        feature1.ifPresent(feature2 -> features.remove(feature2));
        features.add(feature);
    }

    public Optional<Feature> findFeature(String name) {
        return features.stream().filter(
                feature -> feature.getFeature().equals(name)
        ).findFirst();
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
