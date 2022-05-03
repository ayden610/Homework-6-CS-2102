import org.junit.Test;

import static org.junit.Assert.*;

public class Examples {

    ElectionData Setup1() {

        ElectionData ED = new ElectionData();

        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (CandidateExistsException e) {
        }

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {
        }

        return (ED);

    }

    ElectionData Setup2() {

        ElectionData ED2 = new ElectionData();

        try {

            ED2.addCandidate("gompei");
            ED2.addCandidate("husky");
            ED2.addCandidate("ziggy");

        } catch (Exception e) {
        }

        try {

            ED2.processVote("gompei", "husky", "ziggy");
            ED2.processVote("ziggy", "gompei", "husky");
            ED2.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {
        }

        return (ED2);

    }


    @Test
    public void testMostFirstVotes() {
        assertEquals("gompei", Setup1().findWinnerMostFirstVotes());
    }

    @Test
    public void testrunoff() {
        assertEquals("Runoff required", Setup2().findWinnerMostFirstVotes());
    }

    @Test
    public void testMostPoints() {
        assertEquals("gompei", Setup1().findWinnerMostPoints());
    }

    @Test (expected = DuplicateVotesException.class)
    public void testDuplicateVotes() throws DuplicateVotesException, UnknownCandidateException,
            CandidateExistsException {

        ElectionData ED3 = new ElectionData();

        ED3.addCandidate("gompei");

        ED3.processVote("gompei", "gompei", "gompei");
    }

    @Test (expected = UnknownCandidateException.class)
    public void testUnkownCan() throws UnknownCandidateException, DuplicateVotesException {

        ElectionData ED4 = new ElectionData();

        ED4.processVote("gompei", "husky", "ziggy");
    }
}
