import java.util.LinkedList;
import java.util.Scanner;
import java.util.Hashtable;

class ElectionData {
    LinkedList<String> ballot = new LinkedList<String>();
    LinkedList<String> votes = new LinkedList<String>();
    Hashtable<Integer, String> firstVotes = new Hashtable<>();
    Hashtable<Integer, String> secondVotes = new Hashtable<>();
    Hashtable<Integer, String> thirdVotes = new Hashtable<>();
    Scanner keyboard = new Scanner(System.in);
    int i = 0;
    ElectionData() {
        this.ballot.add("Gompei");
        this.ballot.add("Husky");
    }

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    public void screen() throws DuplicateVotesException, UnknownCandidateException, CandidateExistsException {
        System.out.println("Enter A to add candidate, " +
                "T to tally the election, or V to vote:");
        String selection = keyboard.next();
        switch (selection.toLowerCase()) {
            case ("a"):
                System.out.println("Enter the candidate to add:");
                String addCan = keyboard.next();
                addCandidate(addCan);
                break;
            case ("v"):
                this.printBallot();
                System.out.println("Select your first vote:");
                String candidate1 = keyboard.next();
                System.out.println("Select your second vote:");
                String candidate2 = keyboard.next();
                System.out.println("Select your third vote:");
                String candidate3 = keyboard.next();
                processVote(candidate1, candidate2, candidate3);
                System.out.println("You voted for" + candidate1 + ", " + candidate2 + ", " + candidate3);
                break;
            case("t"):
                findWinnerMostFirstVotes();

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
        if (!ballot.contains(firstVote)){
            throw new UnknownCandidateException(firstVote);
        }
        else {firstVotes.put(i, firstVote);}

        if (!ballot.contains(secondVote)){
            throw new UnknownCandidateException(secondVote);
        }
            else if (firstVote.equals(secondVote)){
                throw new DuplicateVotesException(secondVote);
            } else{
                secondVotes.put(i, secondVote);
            }

        if (!ballot.contains(thirdVote)) {
            throw new UnknownCandidateException(thirdVote);
        } else if (firstVote.toLowerCase().equals(thirdVote.toLowerCase())
                    || secondVote.toLowerCase().equals(thirdVote.toLowerCase())){
                throw new DuplicateVotesException(thirdVote);
            } else{
                thirdVotes.put(i, thirdVote);
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
            if (ballot.contains(newCandiate)){
                throw new CandidateExistsException(newCandiate);
            }
            else {
                ballot.add(newCandiate);
            }
    }

    /**
     * This function returns the candidate with the most first votes,
     * given that they hold more than 50% of the vote
     *
     * @returns the winner of a given election based on the first vote percentage
     */
    public String findWinnerMostFirstVotes(){
        int numVotes = 0;
        for(String candidate : ballot){
            for(int j = 0; j < firstVotes.size(); ++j){
            if(firstVotes.get(i) == candidate){
                ++numVotes;
            }
            }
            double percentVotes = numVotes / firstVotes.size();
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
                if(firstVotes.get(j).toLowerCase().equals(candidate.toLowerCase())){
                    currPoints += 3;
                }
            }
            for(int j = 0; j < secondVotes.size(); ++j){
                if(secondVotes.get(j).toLowerCase().equals(candidate.toLowerCase())){
                    currPoints += 2;
                }
            }
            for(int j = 0; j < thirdVotes.size(); ++j){
                if(thirdVotes.get(j).toLowerCase().equals(candidate.toLowerCase())){
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
