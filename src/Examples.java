import org.junit.Test;
import static org.junit.Assert.*;

public class Examples {

    ElectionData Setup1 () {

        ElectionData ED = new ElectionData();

        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED);

    }

    ElectionData Setup2 () {

        ElectionData ED2 = new ElectionData();

        try {

            ED2.addCandidate("grompi");
            ED2.addCandidate("husky");
            ED2.addCandidate("ziggy");

        } catch (Exception e) {}

        try {

            ED2.processVote("grompi", "husky", "grompi");
            ED2.processVote("grompi", "husky", "ziggy");
            ED2.processVote("grompi", "ziggy", "husky");

        } catch (Exception e) {}

        return (ED2);
    }

    ElectionData Setup3 () {

        ElectionData ED3 = new ElectionData();

        try {

            ED3.addCandidate("grompi");
            ED3.addCandidate("husky");
            ED3.addCandidate("ziggy");
            ED3.addCandidate("grompi");

        } catch (Exception e) {}

        try {

            ED3.processVote("gompei", "husky", "ziggy");
            ED3.processVote("gompei", "ziggy", "husky");
            ED3.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return (ED3);
    }

    @Test
    public void testMostFirstVotes () {
        assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
    }

    @Test ( expected = DuplicateVotesException.class)
    public void testDublicateVotes() throws DuplicateVotesException {
        Setup2().findWinnerMostFirstVotes();
    }
}
