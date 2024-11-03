package interfaces;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.queue.Queue;
import components.standard.Standard;

/**
 * User storage kernel with primary methods.
 */
public interface UserStorageKernel extends Standard<UserStorage> {
    /**
     * User class for tracking information.
     */
    public final class User {
        String username;
        String password;
        Queue<String> data;
    }

     /**
     * primary database structure.
     */
    Sequence<User> storage = new Sequence1L<User>();

    /**
     * adds a user of @param username, and @param password
     * @param username - the desired new username.
     * @param password - the desired new password.
     * @ensures this = #this + new user
     * @requires user of @param username does not already exist in #this
     */
    void newUser(String username, String password);

    /**
     * removes a user of given username and returns it.
     * @param username The username of the account to be removed
     * @return the User of the given username that was removed.
     * @ensures this = #this - removed user
     * @requires user of @param username exists in #this
     */
    User removeUser(String username);

    /**
     * returns the number of stored users.
     * @return number of users in storage.
     * @ensures this = #this
     */
    int size();

    /**
     * Finds the index of the username of @param username.
     * @param username The username of the account that should be returned.
     * @return the index of the user in the storage sequence.
     * @ensures this = #this
     * @requires user of @param username exists in #this
     */
    int userIndex(String username);
}
