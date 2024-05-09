import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String text=" ";
        File file = new File("C:\\Users\\ausu\\Desktop\\Mental Cache\\email.csv");
        utility.writetofile(file,text);
        String data =utility.readfromfile(file);
        String dataArr[]=data.split("\n");
        for(String temp :dataArr){
            String wordArr[]=temp.split(";");
            System.out.println(wordArr[0] + "\t" + wordArr[1] + "\t" + wordArr[2] +"\t" + wordArr[3]);
    }
}
    }