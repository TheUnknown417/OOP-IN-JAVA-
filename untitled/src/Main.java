// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
 class A
{
    // default Constructor
    public A()
    {
        System.out.println("In A");

    }

    //Parameterized Constructor
    public A(int n)
    {

        System.out.println("In Custom A");
    }
}


class B extends A
{
    public B()
    {
        super(5);
        System.out.println("In B");
    }
    public B(int n)
    {

        System.out.println("In Custom B");
    }

     int age =12;


}


public class Main{
    public static void main(String[] args) {

            B obj =new B();
            System.out.print(obj.age);



        }
    }
