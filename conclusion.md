# My conclusion for task 101

## Mistakes I found:
* Incorrect maximum loan period.
* Missing credit score calculation. 
* UI had wrong loan period.

## Suggested improvements that I made:
* DecisionEngineController
  * Returning more specific error messages.
  * Refactoring the catch blocks.
* DecisionResponse and DecisionRequest and Decision
  * Since the loanAmount field in DecisionRequest is of type Long, I changed it to long for consistency.
  * Same for loanPeriod (made both Integer)
  * Same in Decision file - changed to Long
* Every ...Exception file
  * No need to store the message and cause manually. (Throwable class does it)
  * Used super(...) because Throwable can handle messages and code seems cleaner.
* DecisionEngine
  * Changed to long where needed
  * Also simplified loan amount validation in verifyImports file
  * I added the credit score calculation logic based on the provided formula.

## What was well done:
* Classes seem to follow SRP (Single-Responsibility Principle) quite well
* OCP (Open/Closed Principle) is good. New functionality (like credit score calculation) can be added with minimal changes to existing code.
* Subtypes (InvalidLoanAmountException, InvalidPersonalCodeException) seem to substitute their base class (Exception) correctly.
* There are no overly complex interfaces, and the current classes do not enforce implementation of methods that are not relevant.
* UI was well done - simple and functional.

# My conclusion for task 102
* Implemented age related restrictions to decision engine.
* Added the ability to select a country (EE, LV, LT) when applying for a loan.
  * Adjusted age limits based on the selected country’s life expectancy.
  * Currently, only Estonian personal codes are accepted for validation.


