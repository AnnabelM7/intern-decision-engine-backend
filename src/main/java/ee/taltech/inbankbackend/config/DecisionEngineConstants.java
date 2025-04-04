package ee.taltech.inbankbackend.config;

/**
 * Holds all necessary constants for the decision engine.
 */
public class DecisionEngineConstants {
    public static final Integer MINIMUM_LOAN_AMOUNT = 2000;
    public static final Integer MAXIMUM_LOAN_AMOUNT = 10000;
    public static final Integer MAXIMUM_LOAN_PERIOD = 48;
    public static final Integer MINIMUM_LOAN_PERIOD = 12;
    public static final Integer SEGMENT_1_CREDIT_MODIFIER = 100;
    public static final Integer SEGMENT_2_CREDIT_MODIFIER = 300;
    public static final Integer SEGMENT_3_CREDIT_MODIFIER = 1000;

    public static final Integer MINIMUM_AGE = 18;
    /*
    Data from
    https://stat.ee/en/news/last-year-life-expectancy-estonia-was-highest-ever-there-was-decrease-healthy-life-years
     */
    public static final Integer LIFE_EXPECTANCY_ESTONIA = 79;
    public static final Integer LIFE_EXPECTANCY_LATVIA = 75;
    public static final Integer LIFE_EXPECTANCY_LITHUANIA = 77;
}
