import java.util.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
class  Student
{
    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


public class Main {
    public static void main(String[] args) {

       /* Map<String , Double> Gun = new HashMap<String, Double>();
        Gun.put("AK-47" , 7.62);
        Gun.put("M4-16" , 5.56);
        Gun.put("Vector" , 9.99);

        Set<Integer> myset= new HashSet<Integer>(); //set has no get



        //System.out.println(Gun);

        for(String key : Gun.keySet())
        {
            System.out.println( key + " : " +  Gun.get(key));

        }*/
/*
        Comparator<String> comp = new Comparator<String>() {
            @Override
            // here we are using an anonymous class and overriding the logic of Sort
            public int compare(String s1, String s2) {
                if(s1.length()>s2.length())
                    return 1 ;
                else
                    return -1;
            }
        };



        List<String> names = new ArrayList<>();
        names.add("Daoud Haroon");
        names.add("Ahmad");
        names.add("Ahmad Junaid");
        names.add("Haroon");
        names.add("Amir");
        names.add("Ali");

        System.out.println(names);

        Collections.sort(names , comp); // can also define our own logic for sort using comparator , have to pass the comparator object defined above


        System.out.println(names);

*/
        Comparator<Student> comp = (Student s1, Student s2) -> s1.age> s2.age?1:-1;
     /*
        OR
            Comparator<Student> comp = new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    if(s1.age> s2.age)
                        return 1 ;
                    else
                        return -1 ;
                }
            };
*/

            List<Student> studs = new ArrayList<>();
            studs.add(new Student(25 , "Daoud"));
            studs.add(new Student(18 , "Daoud15"));
            studs.add(new Student(13 , "Daoud65"));

            for(Student s : studs)
                System.out.println(s);

            Collections.sort(studs , comp);

        System.out.println("\nSorted By age\n");

        for(Student s : studs)
            System.out.println(s);






    }


}
