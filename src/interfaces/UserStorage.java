package interfaces;

import components.simplewriter.SimpleWriter;

/**
 * {@code UserStorage} enhanced with secondary methods.
 */
public interface UserStorage extends UserStorageKernel{

    /**
     * returns true if @param username and @param password exists in this.storage.
     * @param username the desired username of the user to be logged in
     * @param password the desired password of the user to be logged in
     * @return if a user with the corresponding name and password is found
     * @ensures this = #this
     */
    boolean login(String username, String password);

    /**
     * "Merges" two accounts by concatenating user2.data to user1.data and then deleting user2.
     * @param user1 the account for user2's data to be added to.
     * @param user2 The account to be removed
     * @ensures this = #this - user2
     * @requires user of username @param user1, and user of username @param user2 exists in #this
     */
    void mergeUser(String user1, String user2);

    /**
     * adds data to the given user in the format data = dataHeader + ": " + dataBody
     * @param username the username of the account data is to be added to
     * @param dataHeader the header of the data to be added
     * @param dataBody the body of the data to be added
     * @ensures user.data = #user.data + @param dataHeader + @param dataBody
     * @requires user of @param username exists in #this
     */
    void enterData(String username, String dataHeader, String dataBody);

    /**
     * print all user information into @param out
     * @param username the username of the desired account to print
     * @param out the location that account information is to be written to
     * @ensures this = #this
     * @requires user of @param username exists in #this.
     */
     void printUser(String username, SimpleWriter out);
}
