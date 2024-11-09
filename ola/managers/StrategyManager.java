package ola.managers;

import ola.strategies.driver.DefaultDriverMatchingStrategy;
import ola.strategies.driver.DriverMatchingStrategy;
import ola.strategies.pricing.PricingStrategy;
import ola.strategies.pricing.RatingBasedPricingStrategy;

public class StrategyManager {
    private static StrategyManager instance;
    private PricingStrategy pricingStrategy;
    private DriverMatchingStrategy driverMatchingStrategy;
    
    private StrategyManager() {
        // Set default strategies
        this.pricingStrategy = new RatingBasedPricingStrategy();
        this.driverMatchingStrategy = new DefaultDriverMatchingStrategy();
    }
    
    public static StrategyManager getInstance() {
        if (instance == null) {
            instance = new StrategyManager();
        }
        return instance;
    }
    
    // Pricing Strategy methods
    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
    
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
    
    // Driver Matching Strategy methods
    public DriverMatchingStrategy getDriverMatchingStrategy() {
        return driverMatchingStrategy;
    }
    
    public void setDriverMatchingStrategy(DriverMatchingStrategy driverMatchingStrategy) {
        this.driverMatchingStrategy = driverMatchingStrategy;
    }
}