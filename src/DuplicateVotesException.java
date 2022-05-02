/**
 * This is an exception to be thrown when a candidate
 * is voted for more than once in one person's vote
 */
public class DuplicateVotesException extends Exception {
    private String candidateName;
    public DuplicateVotesException(String candidateName) {
        this.candidateName = candidateName;
    }
    public String gitDuplicateCandidate(){
        return candidateName;
    }
}