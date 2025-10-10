package ua.foxminded.carservicerest.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarSpecificationTest {

    @Test
    void filterByMake_handlesNullAndBlank() {
        assertNull(CarSpecification.filterByMake(null));
        assertNull(CarSpecification.filterByMake(""));
        assertNotNull(CarSpecification.filterByMake("Ford"));
    }

    @Test
    void filterByModel_handlesNullAndBlank() {
        assertNull(CarSpecification.filterByModel(null));
        assertNull(CarSpecification.filterByModel(""));
        assertNotNull(CarSpecification.filterByModel("Ranger"));
    }

    @Test
    void filterByMinYear_handlesNullAndValue() {
        assertNull(CarSpecification.filterByMinYear(null));
        assertNotNull(CarSpecification.filterByMinYear(2000));
    }

    @Test
    void filterByMaxYear_handlesNullAndValue() {
        assertNull(CarSpecification.filterByMaxYear(null));
        assertNotNull(CarSpecification.filterByMaxYear(2020));
    }

    @Test
    void filterByCategory_handlesNullAndBlank() {
        assertNull(CarSpecification.filterByCategory(null));
        assertNull(CarSpecification.filterByCategory(""));
        assertNotNull(CarSpecification.filterByCategory("SUV"));
    }
}
