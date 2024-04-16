public class Car extends Vehicle implements MeraInterface
{
    String a;
    @Override
    public String play()
    {
        a="played";
        return a ;

    }

    @Override
    public String stop()
    {
        a = "Stopped";
        return a;
    }


        // getter setter

        public String get_a()
        {
            return a;
        }

        public void set_a(String a)
        {
            this.a=a;

        }






}

