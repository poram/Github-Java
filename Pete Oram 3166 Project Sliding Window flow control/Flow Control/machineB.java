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
class machineB
{
    public static void main(String args[])throws Exception
    {
        Socket ser = new Socket(InetAddress.getLocalHost(),10);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream print = new PrintStream(ser.getOutputStream());
        int i = 0, received =- 1, numFrames, withSend = 8;
        String receiveBuffer[] = new String[8];
        String choice; System.out.println();
        do{
        numFrames = Integer.parseInt(in.readLine());
        if(numFrames <= withSend - 1){
        for(i = 1; i <= numFrames; i++)
        {
        received =++received%8;
        receiveBuffer[received] = in.readLine();
        System.out.println("The frame that has been received from Machine A " + received + " is : " + receiveBuffer[received]);
        }
        withSend -= numFrames;
        System.out.println("\nConfirmation of data sent.\n");
        print.println(received + 1); withSend += numFrames; }
        else
        break;
        choice = in.readLine();
        }
        while(choice.equals("yes"));
    }
}
