package dhaumikkumar.people_notes;

/**
 * Created by dhaumikkumar on 2014-07-23.
 */
public class FavoriteList
{
    //private variables
    int id;
    String name;
    String age;

    // Empty constructor
    public FavoriteList(){

    }
    // constructor
    public FavoriteList(int id, String name, String age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // getting id
    public int getId(){
        return this.id;
    }

    // setting id
    public void setId(int id){
        this.id = id;
    }

    // getting name
    public String getName(){
        return this.name;
    }

    // setting name
    public void setName(String name){
        this.name = name;
    }

    // getting Moviename
    public String getAge(){
        return this.age;
    }

    // setting Moviename
    public void setAge(String age){
        this.age = age;
    }

}

