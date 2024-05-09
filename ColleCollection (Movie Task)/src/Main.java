import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String args[])
    {
    ArrayList<Movie> movies_list = new ArrayList<Movie>();

    System.out.println("Empty ?? : " + movies_list.isEmpty());
    System.out.println("Intial Size : " + movies_list.size());

  // adding objects of Movie CLass to arraylist OF Movie
    movies_list.add(new Movie("Jhonson Riche" , 7.4 , "14/7/2021", "Gansgter Squad"));
    movies_list.add(new Movie("Adam Zampa" , 9.4 , "4/3/2020", "La La Land"));
    movies_list.add(new Movie("Joss Butler" , 8.1 , "14/7/2005", "Note-Book"));

    //Displaying all object in the ArrayList
       for(Movie temp : movies_list)
            System.out.println(temp);



    // Deleting an object element from the Array list (Using Object name);
        movies_list.remove(new Movie("Adam Zampa", 9.4 , "4/3/2020", "La La Land"));



        // Deleting an object element from the Array list (Using index);
        movies_list.remove(1);




        // adding objects on specific indexes

        movies_list.add(1, new Movie("Ahmad ", 7.9 , "7/4/1990", "Ahmad's film"));
        movies_list.add(2, new Movie("Daoud", 9.9 , "7/4/1980", "MY film"));





          for(Movie temp : movies_list)
            System.out.println(temp);

   // reduced ratings
        for (Movie temp : movies_list)
        {
           System.out.println(temp.getRating()-1);

        }




    }

}