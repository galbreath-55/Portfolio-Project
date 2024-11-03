import components.queue.Queue1L;
import components.queue.Queue;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class ProofOfConcept {

    /**
     * User class for tracking information.
     */
    private final class User {
        /**
         * data in User.
         */
        private Queue<String> data;

        /**
         * Username associated with account.
         */
        private String username;
        /**
         * password associated with account.
         */
        private String password;
    }

    /**
     * primary database structure.
     */
    private Sequence<User> storage;

    /**
     * creator of initial representation.
     */
    private void createNewRep(){
        this.storage = new Sequence1L<User>();
    }

    /**
     * Standard no argument constructor.
     */
    public ProofOfConcept(){
        this.createNewRep();
    }

    //////////////////////////////////////////////
    // KERNEL METHODS
    //////////////////////////////////////////////
    
    /**
     * adds a user of @param username, and @param password
     * @param username - the desired new username.
     * @param password - the desired new password.
     */
    public final void newUser(String username, String password){
        for(int i = 0; i < this.storage.length();i++){
            if(this.storage.entry(i).username.equals(username)) {
                assert false : "ERROR: username is already in use";
            }
        }
        User newEntry = new User();
        newEntry.data = new Queue1L<String>();
        newEntry.username = username;
        newEntry.password = password;
        this.storage.add(this.storage.length(),newEntry);
    }

    /**
     * removes a user of given username and returns it.
     * @param username
     * @return
     */
    public final User removeUser(String username) {
        int i = 0;
        while(!this.storage.entry(i).username.equals(username) && i< this.storage.length()-1){
            i++;
        }
        User removedEntry = this.storage.remove(i);
        return removedEntry;
    }

    /**
     * returns the number of stored users.
     * @return number of users in storage.
     */
    public final int size(){
        return this.storage.length();
    }


    //////////////////////////////////////////////
    // SECONDARY METHODS
    //////////////////////////////////////////////
    
    /**
     * returns true if @param username and @param password exists in this.storage.
     * @param username
     * @param password
     * @return
     */
    public final boolean login(String username, String password){
        int i = 0;
        boolean entryFound = false;
        while(!entryFound && i<this.storage.length()){
            if(this.storage.entry(i).username.equals(username) && this.storage.entry(i).password.equals(password)){
                entryFound = true;
            }
            i++;
        }

        return entryFound;
    }

    /**
     * "Merges" two accounts by concatenating user2.data to user1.data and then deleting user2.
     * @param user1 the account for user2's data to be added to.
     * @param user2 The account to be removed
     */
    public final void mergeUser(String user1, String user2){
        User tempUser1 = this.removeUser(user1);
        User tempUser2 = this.removeUser(user2);
        tempUser1.data.append(tempUser2.data);
        this.storage.add(this.size(),tempUser1);
    }

    /**
     * adds data to the given user.
     * @param username
     * @param dataHeader
     * @param dataBody
     */
    public final void enterData(String username, String dataHeader, String dataBody){
        User tempUser = this.removeUser(username);
        tempUser.data.enqueue(dataHeader + ":" + dataBody);
        this.storage.add(this.size(),tempUser);
    }

    /**
     * print all user information into @param out
     * @param username
     * @param out
     */
     public final void printUser(String username, SimpleWriter out){
        User tempUser = this.removeUser(username);
        out.println("Username: " + tempUser.username);
        out.println("Password: " + tempUser.password);
        out.println("Data: ");
        Queue<String> tempData = tempUser.data.newInstance();
        while(tempUser.data.length()!=0){
            String dataString = tempUser.data.dequeue();
            out.println(dataString);
            tempData.enqueue(dataString);
        }
        tempUser.data.transferFrom(tempData);
        this.storage.add(this.size(),tempUser);
     }

     public static void main(String[] args) {
        ProofOfConcept accountHolder = new ProofOfConcept();
        accountHolder.newUser("galbreath.55","password123");
        accountHolder.newUser("newGalbreath.55","password1234");
        accountHolder.enterData("galbreath.55","Birthday","2/2/05");
        accountHolder.enterData("newGalbreath.55","Address","123 Example Road, Columbus OH");

        SimpleWriter out = new SimpleWriter1L();
        accountHolder.printUser("galbreath.55", out);
        out.println("");
        accountHolder.mergeUser("galbreath.55","newGalbreath.55");
        accountHolder.printUser("galbreath.55",out);
     }
}
