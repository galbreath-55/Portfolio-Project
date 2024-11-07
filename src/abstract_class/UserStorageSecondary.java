package abstract_class;

import components.queue.Queue;
import components.simplewriter.SimpleWriter;
import interfaces.UserStorage;

public abstract class UserStorageSecondary implements UserStorage{
 
    /*
     * public members -----------------------------------------------
    */


    /*
     * Common methods from Object ----------------------------------
    */

    @Override
    public String toString(){
        String result = "";
        if(storage.length()!=0){
        for(int i = 0; i< storage.length();i++) {
            result+="Username: " + storage.entry(i).username() + "/n";
            result += "Password: " + storage.entry(i).password() + "/n";
            if(storage.entry(i).data().length() >0) {
                result+="Data: " + storage.entry(i).data().toString();
            }
        }
    }

        return result;
    }

    @Override
    public boolean equals(Object other){
        boolean isEqual=true;
        String strOther = other.toString();
        String strThis = this.toString();
        
        if(!other.getClass().toString().equals("UserStorage")){
            isEqual = false;
        } else if (!strOther.substring(0,10).equals("Username: ")){
            isEqual = false;
        } else if(!strOther.equals(strThis)){
            isEqual=false;
        }

        return isEqual;
    }

     
    /*
     * Secondary Methods from Object ----------------------------------
    */
    @Override
    public boolean login(String username, String password){
        int i = 0;
        boolean entryFound = false;
        while(!entryFound && i<storage.length()){
            if(storage.entry(i).username().equals(username) && storage.entry(i).password().equals(password)){
                entryFound = true;
            }
            i++;
        }

        return entryFound;
    }

    @Override
    public final void mergeUser(String user1, String user2){
        User tempUser1 = this.removeUser(user1);
        User tempUser2 = this.removeUser(user2);
        tempUser1.data().append(tempUser2.data());
        storage.add(this.size(),tempUser1);
    }

    @Override
    public final void enterData(String username, String dataHeader, String dataBody){
        User tempUser = this.removeUser(username);
        tempUser.data().enqueue(dataHeader + ":" + dataBody);
        storage.add(this.size(),tempUser);
    }

    public final void printUser(String username, SimpleWriter out){
        User tempUser = this.removeUser(username);
        out.println("Username: " + tempUser.username());
        out.println("Password: " + tempUser.password());
        out.println("Data: ");
        Queue<String> tempData = tempUser.data().newInstance();
        while(tempUser.data().length()!=0){
            String dataString = tempUser.data().dequeue();
            out.println(dataString);
            tempData.enqueue(dataString);
        }
        tempUser.data().transferFrom(tempData);
        storage.add(this.size(),tempUser);
     }


}
