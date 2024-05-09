import java.io.*;

public class utility {
        public static void writetofile(File file, String text){
            try{

                FileWriter writer= new FileWriter(file,true);
                BufferedWriter bwriter= new BufferedWriter(writer);
                writer.write(text);
                bwriter.newLine();
                bwriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static String readfromfile(File file){
            String data = "";
            try{
                FileReader reader = new FileReader(file);
                BufferedReader breader= new BufferedReader(reader);
                String text="";
                while ((text = breader.readLine()) != null) {
                    data += text+"\n";

                }
            }
            catch (FileNotFoundException e){

            }
            catch(IOException e) {
                throw new RuntimeException(e);
            }
            return data;
        }

    }

