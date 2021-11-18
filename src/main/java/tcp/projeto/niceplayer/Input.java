/*Serve de intermediario entre a interface gráfica e o parser*/
package tcp.projeto.niceplayer;

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Input {
        private String textStream = "A A A ";
        private String fileName;

        public String getFileName() {
            return fileName;
        }
        public String getTextStream() {
            return textStream;
        }
        public void setFileName(String name) {
                fileName = name;
        }
        public void setTextStream(String text) {
                textStream = text;
        }

        public Input(String _fileName) {
                fileName = _fileName;
        }

        //cria um arquivo
        public void createFile(){
                try {
                        File myObj = new File(getFileName());//cria um arquivo
                        if (myObj.createNewFile()) {//caso consiga criar o arquivo
                                //System.out.println("File created: " + myObj.getName());//imprime mensagem informando o nome do arquivo criado
                        } else {//caso o arquivo já exista
                                //System.out.println("File already exists.");//imprime mensagem de aviso
                        }
                } catch (IOException e) {//caso ocorra um erro
                        //System.out.println("An error occurred.");//imprime mensagem de aviso
                        e.printStackTrace();//busca o erro
                }
        }
        public void writeToFile(){
                try {
                        FileWriter myWriter = new FileWriter(getFileName());//cria um arquivo
                        myWriter.write(textStream);//escreve uma mensagem no arquivo
                        myWriter.close();//fecha o arquivo
                        //System.out.println("Successfully wrote to the file.");//imprime mensagem na linha de comando
                } catch (IOException e) {//caso ocorra erro
                        System.out.println("An error occurred.");//imprime aviso na linha de comando
                        e.printStackTrace();//busca o erro
                }
        }
        //salva um arquivo com o input
        public void saveFile (){
                //cria um arquivo
                createFile();
                //escreve no arquivo
                writeToFile();
        }
        public void readFile(){
                try {
                        File myObj = new File(getFileName());//cria um arquivo chamado nicefile
                        Scanner myReader = new Scanner(myObj);//cria scanner para o aquivo nicefile
                        while (myReader.hasNextLine()) {//enquanto houver proxima linha no arquivo
                                textStream += myReader.nextLine() + "\n";//a string data recebe a string da proxima linha
                                //System.out.println(textStream);//imprime a string data
                        }
                        myReader.close();//fecha o arquivo
                } catch (FileNotFoundException e) {//caso ocorra um erro
                        System.out.println("An error occurred.");//imprime mensagem de aviso
                        e.printStackTrace();//busca o erro
                }
        }
};
