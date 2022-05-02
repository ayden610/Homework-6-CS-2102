public class UnknownCandidateException extends Exception {
    String candidateName;
    public UnknownCandidateException(String candidateName) {
        this.candidateName = candidateName;

    }
}