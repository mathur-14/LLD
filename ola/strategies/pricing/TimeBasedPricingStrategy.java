package ola.strategies.pricing;

import ola.models.TripMetaData;

public class TimeBasedPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(TripMetaData tripMetaData) {
        return tripMetaData.getDuration() * 60.0; // $60 per hour
    }
}