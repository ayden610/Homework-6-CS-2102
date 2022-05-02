import java.util.LinkedList;
import java.util.Hashtable;

class ElectionData {
    LinkedList<String> ballot = new LinkedList<>();
    Hashtable<Integer, String> firstVotes = new Hashtable<>();
    Hashtable<Integer, String> secondVotes = new Hashtable<>();
    Hashtable<Integer, String> thirdVotes = new Hashtable<>();

    int i = 0;
    ElectionData() {
        this.ballot.add("gompei");
        this.ballot.add("husky");
    }

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }




    /**
     * This function takes the three candidates that someone votes for and
     * adds the votes to the running tally
     *
     * @param firstVote name of the first candidate votes for
     * @param secondVote name of the second candidate votes for
     * @param thirdVote name of the third candidate votes for
     * @throws DuplicateVotesException
     * throws when a candidate is voted for multiple times on one ballot
     * @throws UnknownCandidateException
     * throws when a candidate not on the ballot is voted for
     */
    public void processVote(String firstVote, String secondVote, String thirdVote)
            throws DuplicateVotesException, UnknownCandidateException{
        String firstvote = firstVote.toLowerCase();
        String secondvote = secondVote.toLowerCase();
        String thirdvote = thirdVote.toLowerCase();
        if (!ballot.contains(firstvote)){
            throw new UnknownCandidateException(firstvote);
        }
        else {firstVotes.put(i, firstvote);}

        if (!ballot.contains(secondvote)){
            throw new UnknownCandidateException(secondvote);
        }
            else if (firstvote.equals(secondvote)){
                throw new DuplicateVotesException(secondvote);
            } else{
                secondVotes.put(i, secondvote);
            }

        if (!ballot.contains(thirdvote)){
            throw new UnknownCandidateException(thirdvote);
        }else if (firstvote.equals(thirdvote)
                    || secondvote.equals(thirdvote)){
                throw new DuplicateVotesException(thirdvote);
            } else{
                thirdVotes.put(i, thirdvote);
            }

        ++i;
    }

    /**
     * This function takes a candidate name, checks if it is on the ballot,
     * and if it isn't, adds it on the ballot.
     *
     * @param newCandiate the candidate to be added to the ballot
     * @throws CandidateExistsException is thrown when the candidate is already on the ballot
     */
    public void addCandidate(String newCandiate) throws CandidateExistsException {
            if (ballot.contains(newCandiate.toLowerCase())){
                throw new CandidateExistsException(newCandiate.toLowerCase());
            }
            else {
                ballot.add(newCandiate.toLowerCase());
            }
    }

    /**
     * This function returns the candidate with the most first votes,
     * given that they hold more than 50% of the vote
     *
     * @returns String of the winner of a given election based on the first vote percentage
     */
    public String findWinnerMostFirstVotes(){
        int numVotes = 0;
        for(String candidate : ballot){
            for(int j = 0; j < firstVotes.size(); ++j){
            if(firstVotes.get(j).equals(candidate)){
                ++numVotes;
            }
            }
            double percentVotes = (numVotes * 1.0)  / (firstVotes.size() * 1.0);
            if(percentVotes > 0.5){
                return candidate;
            }
        }
        return "Runoff required";
    }

    /**
     * This function returns the winner of an election based on a ranked vote system,
     * each first giving 3 points, second giving 2, and third giving 1
     *
     * @return the winner based on the above math
     */
    public String findWinnerMostPoints(){
        String candidateMostPoints = ballot.get(0);
        int currPoints = 0;
        int bestPoints = 0;
        for(String candidate : ballot){
            for(int j = 0; j < firstVotes.size(); ++j){
                if(firstVotes.get(j).equalsIgnoreCase(candidate)){
                    currPoints += 3;
                }
            }
            for(int j = 0; j < secondVotes.size(); ++j){
                if(secondVotes.get(j).equalsIgnoreCase(candidate)){
                    currPoints += 2;
                }
            }
            for(int j = 0; j < thirdVotes.size(); ++j){
                if(thirdVotes.get(j).equalsIgnoreCase(candidate)){
                    currPoints +=1;
                }
            }
            if(currPoints > bestPoints){
                candidateMostPoints = candidate;
                bestPoints = currPoints;
            }
        }
        return candidateMostPoints;
    }
}
