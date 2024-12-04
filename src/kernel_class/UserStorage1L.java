package kernel_class;

import abstract_class.UserStorageSecondary;
import interfaces.UserStorage;
import components.queue.Queue;
import components.queue.Queue1L;

public class UserStorage1L extends UserStorageSecondary{

    /*
     * Private members -------------------------------------------------------------
     */
    

    /*
     * Constructors ----------------------------------------------------------------
     */

     /*
      * No argument constructor.
      */
    private void createNewRep(){
        storage.clear();
    }


    /*
     * Standard methods ------------------------------------------------------------
     */

    @Override
    public void clear(){
        this.createNewRep();
    }

    @Override
    public UserStorage newInstance(){
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public void transferFrom(UserStorage source){
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof UserStorage1L : ""
                + "Violation of: source is of dynamic type UserStorage1L";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        UserStorage1L localSource = new UserStorage1L();
        while(source.size()>0){
            User temp = source.removeAny();
            User newTemp = new User(temp.username(),temp.password(),temp.data());
            localSource.newUser(newTemp);
        }
    }


    /*
     * Kernel Methods ----------------------------------------------------------------
     */


     @Override
    public void newUser(String username, String password){
        Queue<String> addedQueue = new Queue1L<String>();
        User addedUser = new User(username,password,addedQueue);
        storage.add(storage.length(),addedUser);
    }

    @Override
    public void newUser(User account){
        storage.add(storage.length(),account);
    }

    @Override
    public User removeUser(String username){
        int i = 0;
        while(i < storage.length() && storage.entry(i).username()!=username){
            i++;
        }
        //this is safe and cannot result in an error because it is required that the
        //desired username exists in this
        User removed = storage.remove(i);

        return removed;
    }

    @Override
    public final User removeAny(){
        return storage.remove(0);
     }

    @Override
    public int size(){
        return 0;
    }
}
