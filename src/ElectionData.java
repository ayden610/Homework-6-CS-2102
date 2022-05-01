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


    public void processVote(String FirstVote, String SecondVote, String ThirdVote) {
        firstVotes.put(i, FirstVote);
        secondVotes.put(i, SecondVote);
        thirdVotes.put(i, ThirdVote);
        ++i;
    }
    public void addCandidate(String newCandiate) throws CandidateExistsException {
            if (ballot.contains(newCandiate)){
                throw new CandidateExistsException("The candidate " + newCandiate + "is already on the ballot");
            }
            else {
                ballot.add(newCandiate);
            }
    }
}
