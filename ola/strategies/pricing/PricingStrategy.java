package ola.strategies.pricing;

import ola.models.TripMetaData;

public interface PricingStrategy {
    double calculatePrice(TripMetaData tripMetaData);
}