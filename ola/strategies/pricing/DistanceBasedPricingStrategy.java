package ola.strategies.pricing;

import ola.models.TripMetaData;

public class DistanceBasedPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(TripMetaData tripMetaData) {
        return tripMetaData.getDistance() * 10.0; // $10 per unit distance
    }
}