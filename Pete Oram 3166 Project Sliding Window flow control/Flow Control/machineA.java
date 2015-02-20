import java.util.*;
import java.net.*;
import java.io.*;
import java.rmi.*;
import java.io.Reader.*;
//Pete Oram 
//ITCS-3166
//Sliding Window program
//#800159179

//This program simulates a sliding window by sending data between two machines A and B
public class machineA
{
    public static void main(String args[])throws Exception
    {
        ServerSocket server = new ServerSocket(10);//
        Socket ser = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
        String senderBuffer[] = new String[8];
        PrintStream print;
        int sendPack = 0, withSend = 8, numFrames, received, i;
        String choice;
        do{
        print = new PrintStream(ser.getOutputStream());
        System.out.print("Please enter the number of frames : ");
        numFrames = Integer.parseInt(in.readLine());
        print.println(numFrames);
        if(numFrames <= withSend - 1){
        System.out.println("Please type "+ numFrames + " messages to be sent \n");
        for(i = 1; i <= numFrames; i++){
        senderBuffer[sendPack] = in.readLine();
        print.println(senderBuffer[sendPack]);
        sendPack =++sendPack%8;
        }
        withSend -= numFrames;
        System.out.print("Confirmation of data received");
        received = Integer.parseInt(in1.readLine());
        System.out.println(" for " + received + " frames");
        withSend += numFrames;
        }
        else{
        System.out.println("The number of frames you are trying to send is bigger that the window size!");
        break;
        }
        System.out.print("\nWould you like to send more frames at this time? : ");
        choice = in.readLine(); print.println(choice);
        }while(choice.equals("Yes"));
        ser.close();
    }
}
