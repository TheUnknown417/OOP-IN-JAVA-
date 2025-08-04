import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordsToNumbers {

    private static  final Map<String, Integer> Number_map = new HashMap<>();
    private static  final Map<String, Integer> Multiper_map = new HashMap<>();


    static
    {
        Number_map.put("zero",0);
        Number_map.put("one",1);                   Multiper_map.put("hundred",100);
        Number_map.put("two",2);                   Multiper_map.put("thousand",1000);
        Number_map.put("three",3);                 Multiper_map.put("lac",100000);
        Number_map.put("four",4);                  Multiper_map.put("million",1000000);
        Number_map.put("five",5);                  Multiper_map.put("billion",1000000000);
        Number_map.put("six",6);
        Number_map.put("seven",7);
        Number_map.put("eight",8);
        Number_map.put("nine",9);
        Number_map.put("ten",10);
        Number_map.put("eleven",11);
        Number_map.put("twelve",12);
        Number_map.put("thirteen",13);
        Number_map.put("fourteen",14);
        Number_map.put("fifteen",15);
        Number_map.put("sixteen",16);
        Number_map.put("seventeen",17);
        Number_map.put("eighteen",18);
        Number_map.put("nineteen",19);
        Number_map.put("twenty",20);
        Number_map.put("thirty",30);
        Number_map.put("forty",40);
        Number_map.put("fifty",50);
        Number_map.put("sixty",60);
        Number_map.put("seventy",70);
        Number_map.put("eighty",80);
        Number_map.put("ninety",90);

    }


    public static int Convert(String input)
    {
        input =  input.toLowerCase().replaceAll(" and" , "").replaceAll("-", " ");
        String[] words = input.split("\\s+");

        int current = 0 ;
        int total = 0;

        for(String word : words) {
            if(Number_map.containsKey(word))
            {
              current +=  Number_map.get(word);
            }
            else if (word.equals("Hundred"))
            {
                current*=100;
            }
            else if (Multiper_map.containsKey(word))
            {
                current *= Multiper_map.get(word);
                total += current;
                current=0;
            }

            else
                throw new IllegalArgumentException(word + " Not a valid word");
        }


        return current + total ;
    }


    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number In Words: ");
        String input = sc.nextLine();

        try
        {
            int result = Convert(input);
            System.out.println("Number in Words : " + result);
        }
        catch (Exception e)
        {
            System.out.println("Error : " + e.getMessage());
        }
    }

    }



