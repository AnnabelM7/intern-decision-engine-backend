package ee.taltech.inbankbackend.service;

import ee.taltech.inbankbackend.config.DecisionEngineConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

/**
 * Service class to handle age-related restrictions for loan eligibility.
 * This class checks if the customer is eligible for a loan based on their age
 * and the country's life expectancy adjusted by the loan period.
 */
@Service
public class AgeRestrictionService {

    private static final Map<String, Integer> LIFE_EXPECTANCIES = Map.of(
            "EE",  DecisionEngineConstants.LIFE_EXPECTANCY_ESTONIA,
            "LV", DecisionEngineConstants.LIFE_EXPECTANCY_LATVIA,
            "LT", DecisionEngineConstants.LIFE_EXPECTANCY_LITHUANIA
    );

    /**
     * Checks if the customer is eligible for a loan based on their age and the country's life expectancy.
     *
     * @param personalCode The personal code of the customer.
     * @param loanPeriod   The period (in months) of the loan.
     * @param country      The country of the customer (EE, LV, LT).
     * @return boolean     Returns true if the customer is eligible for the loan, false otherwise.
     */
    public boolean isEligibleForLoan(String personalCode, int loanPeriod, String country) {
        int age = calculateAge(personalCode);
        int maxLoanAge = LIFE_EXPECTANCIES.getOrDefault(country,80) - (loanPeriod / 12);
        return age >= DecisionEngineConstants.MINIMUM_AGE && age <= maxLoanAge;
    }

    /**
     * Calculates the age of a person based on their personal code.
     * The method assumes the personal code follows the Estonian format.
     *
     * @param personalCode The personal code of the customer.
     * @return int         The age of the person in years.
     */
    private int calculateAge(String personalCode) {
        int centuryCode = Integer.parseInt(personalCode.substring(0, 1));
        int year = Integer.parseInt(personalCode.substring(1, 3));
        int month = Integer.parseInt(personalCode.substring(3, 5));
        int day = Integer.parseInt(personalCode.substring(5, 7));

        if (centuryCode == 1 || centuryCode == 2) {
            year += 1800;
        } else if (centuryCode == 3 || centuryCode == 4) {
            year += 1900;
        } else if (centuryCode == 5 || centuryCode == 6) {
            year += 2000;
        }else if (centuryCode == 7 || centuryCode == 8) {
            year += 2100;
        }

        LocalDate birthDate = LocalDate.of(year, month, day);

        LocalDate today = LocalDate.now();

        Period period = Period.between(birthDate, today);
        return period.getYears();
    }
}
