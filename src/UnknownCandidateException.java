/**
 * This is an exception to be thrown when a candidate
 * is voted for and they don't appear on the ballot
 */
public class UnknownCandidateException extends Exception {
    private String candidateName;
    public UnknownCandidateException(String candidateName) {
        this.candidateName = candidateName;
    }
    public String gitUnknownCandidate(){
        return candidateName;
    }
}