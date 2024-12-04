package interfaces;
import components.standard.Standard;
import components.queue.Queue;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * User storage kernel with primary methods.
 */
public interface UserStorageKernel extends Standard<UserStorage> {
    /**
     * User class for tracking information.
     */
    record User(String username,String password,Queue<String> data){}

    Sequence<User> storage = new Sequence1L<User>();

     /**
     * primary database structure.
     */
    /**
     * adds a user of @param username, and @param password
     * @param username - the desired new username.
     * @param password - the desired new password.
     * @ensures this = #this + new user
     * @requires user of @param username does not already exist in #this
     */
    void newUser(String username, String password);

    /**
     * adds a user of @param username, and @param password
     * @param account The account to be added
     * @ensures this = #this + new user
     * @requires user of account.username() does not already exist in #this
     */
    void newUser(User account);

    /**
     * removes a user of given username and returns it.
     * @param username The username of the account to return
     * @return the User of the given username that was removed.
     * @ensures this = #this - removed user
     * @requires user of @param username exists in #this
     */
    User removeUser(String username);

    /**
     * @return a random user
     * @requires |this| >= 1
     */
    User removeAny();

    /**
     * returns the number of stored users.
     * @return number of users in storage.
     * @ensures this = #this
     */
    int size();
}
