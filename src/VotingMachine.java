import java.util.Scanner;
public class VotingMachine {
    public Scanner keyboard = new Scanner(System.in);
    public static ElectionData thisElection = new ElectionData();

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : thisElection.getBallot()) {
            System.out.println(s);
        }
    }

        public void screen() throws CandidateExistsException {
        System.out.println("Enter A to add candidate, " +
                "T to tally the election, or V to vote:");
        String selection = keyboard.next();
        switch (selection.toLowerCase()) {
            case ("a"):
                System.out.println("Enter the candidate to add:");
                String addCan = keyboard.next();
                thisElection.addCandidate(addCan);
                break;
            case ("v"):
                    this.printBallot();
                    System.out.println("Select your first vote:");
                    String candidate1 = keyboard.next();
                    System.out.println("Select your second vote:");
                    String candidate2 = keyboard.next();
                    System.out.println("Select your third vote:");
                    String candidate3 = keyboard.next();
                    try{ thisElection.processVote(candidate1, candidate2, candidate3);
                    System.out.println("You voted for" + candidate1 + ", " + candidate2 + ", " + candidate3);
                } catch (UnknownCandidateException anException) {
                    System.out.println("The candidate" + anException.getUnknownCandidate() +
                            "is not on the ballot, would you like to write them in? press Y to write them in:");
                    String exceptionAnswer = keyboard.next();
                    switch (exceptionAnswer.toLowerCase()) {
                        case ("y"):
                            addWriteIn(anException.getUnknownCandidate());
                            break;
                        default:
                            break;
                    }

                } catch (DuplicateVotesException e) {
                    System.out.println("you cannot vote for the same candidate twice!");
                }
                break;
            case ("t"):
                thisElection.findWinnerMostFirstVotes();

        }
    }

    public static void addWriteIn(String candidateName) throws CandidateExistsException {
        try {
            thisElection.addCandidate(candidateName);
        } catch (CandidateExistsException e) {
            System.out.println("The candidate " + candidateName + " already exists.");
        }
        System.out.println("The candidate " + candidateName + " has been added to the ballot.");
    }
}
