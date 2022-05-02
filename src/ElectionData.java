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
    int i = 1;
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

    public void screen() {
        this.printBallot();
        System.out.println("Select your first vote");
        String candidate1 = keyboard.next();
        firstVotes.put(i, candidate1);
        System.out.println("You voted for " + candidate1 + " as your first choice");
        System.out.println("Select your second vote");
        String candidate2 = keyboard.next();
        secondVotes.put(i, candidate2);
        System.out.println("You voted for " + candidate2 + " as your second choice");
        System.out.println("Select your third vote");
        String candidate3 = keyboard.next();
        thirdVotes.put(i, candidate3);
        System.out.println("You voted for " + candidate3 + " as your third choice");
        ++i;
    }

    public int countVotes(String forcand) {
        int numvotes = 0;
        for (String s : votes) {
            if (s.equals(forcand))
                numvotes = numvotes+1;
        }
        return numvotes;
    }


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

    public void addCandidate(String newCandiate) throws CandidateExistsException {
            if (ballot.contains(newCandiate)){
                throw new CandidateExistsException(newCandiate);
            }
            else {
                ballot.add(newCandiate);
            }
    }
}
