package ee.taltech.inbankbackend.endpoint;

import ee.taltech.inbankbackend.exceptions.InvalidLoanAmountException;
import ee.taltech.inbankbackend.exceptions.InvalidLoanPeriodException;
import ee.taltech.inbankbackend.exceptions.InvalidPersonalCodeException;
import ee.taltech.inbankbackend.exceptions.NoValidLoanException;
import ee.taltech.inbankbackend.service.Decision;
import ee.taltech.inbankbackend.service.DecisionEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@CrossOrigin
public class DecisionEngineController {

    private final DecisionEngine decisionEngine;
    private final DecisionResponse response;

    @Autowired
    DecisionEngineController(DecisionEngine decisionEngine, DecisionResponse response) {
        this.decisionEngine = decisionEngine;
        this.response = response;
    }

    /**
     * A REST endpoint that handles requests for loan decisions.
     * The endpoint accepts POST requests with a request body containing the customer's personal ID code,
     * requested loan amount, and loan period.<br><br>
     * - If the loan amount or period is invalid, the endpoint returns a bad request response with an error message.<br>
     * - If the personal ID code is invalid, the endpoint returns a bad request response with an error message.<br>
     * - If an unexpected error occurs, the endpoint returns an internal server error response with an error message.<br>
     * - If no valid loans can be found, the endpoint returns a not found response with an error message.<br>
     * - If a valid loan is found, a DecisionResponse is returned containing the approved loan amount and period.
     *
     * @param request The request body containing the customer's personal ID code, requested loan amount, and loan period
     * @return A ResponseEntity with a DecisionResponse body containing the approved loan amount and period, and an error message (if any)
     */
    @PostMapping("/decision")
    public ResponseEntity<DecisionResponse> requestDecision(@RequestBody DecisionRequest request) {

        System.out.println("Received request:");
        System.out.println("personalCode: " + request.getPersonalCode());
        System.out.println("loanAmount: " + request.getLoanAmount());
        System.out.println("loanPeriod: " + request.getLoanPeriod());
        System.out.println("countryCode: " + request.getCountryCode());

        try {
            Decision decision = decisionEngine.
                    calculateApprovedLoan(request.getPersonalCode(), request.getLoanAmount(), request.getLoanPeriod(),  request.getCountryCode());
            response.setLoanAmount(decision.getLoanAmount());
            response.setLoanPeriod(decision.getLoanPeriod());
            response.setErrorMessage(decision.getErrorMessage());

            return ResponseEntity.ok(response);
        } catch (InvalidPersonalCodeException | InvalidLoanAmountException | InvalidLoanPeriodException e) {
            return handleErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NoValidLoanException e) {
            return handleErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return handleErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private ResponseEntity<DecisionResponse> handleErrorResponse(String errorMessage, HttpStatus status) {
        DecisionResponse response = new DecisionResponse();
        response.setLoanAmount(null);
        response.setLoanPeriod(null);
        response.setErrorMessage(errorMessage);

        return ResponseEntity.status(status).body(response);
    }

}
