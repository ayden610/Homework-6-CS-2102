/**
 * This is an exception to be thrown when someone
 * attempts to add a candidate on the ballot that already exists
 */
public class CandidateExistsException extends Exception {
    String candidateName;
    public CandidateExistsException(String candidateName) {
        this.candidateName = candidateName;

    }
}
