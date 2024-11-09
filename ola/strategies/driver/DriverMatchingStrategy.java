package ola.strategies.driver;

import ola.models.Driver;
import ola.models.TripMetaData;

public interface DriverMatchingStrategy {
    Driver matchDriver(TripMetaData tripMetaData);
}