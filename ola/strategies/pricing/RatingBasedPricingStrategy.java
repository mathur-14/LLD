package ola.strategies.pricing;

import ola.models.TripMetaData;

public class RatingBasedPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(TripMetaData tripMetaData) {
        double basePrice = calculateBasePrice(tripMetaData);
        return basePrice * (1 + tripMetaData.getRider().getRating().getValue() * 0.1);
    }
    
    private double calculateBasePrice(TripMetaData tripMetaData) {
        // Implementation for base price calculation
        return 100.0; // Simplified version
    }
}