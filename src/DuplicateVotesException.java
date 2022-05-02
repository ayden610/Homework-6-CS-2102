public class DuplicateVotesException extends Exception {
    String candidateName;
    public DuplicateVotesException(String candidateName) {
        this.candidateName = candidateName;

    }
}