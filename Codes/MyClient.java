package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("localhost",1111);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream din = new DataInputStream(s.getInputStream());
        String str = null;

        while(true){

            System.out.print("\nMessage or Formulae ? (S/F):");
            String que = sc.nextLine();
//            System.out.println("que :"+que);
            if(que.toLowerCase().equals("s")){
                dout.writeUTF("s");

                System.out.print("Enter your Message :");
                str = sc.nextLine();

                dout.writeUTF(str.toString());
                str = din.readUTF();
                System.out.println("Server Says :"+str);

                System.out.print("\nTerminate ? (y/n) :");
                str = sc.nextLine();
                end(str,s,dout);
                System.out.println();
            }
            else if (que.toLowerCase().equals("f")) {
                dout.writeUTF("f");
                int var1,var2;
                String op;

                System.out.print("Enter value of var1 :");
                var1 = sc.nextInt();
                System.out.print("Enter value of var2 :");
                var2 = sc.nextInt();
                System.out.print("Enter Operator :");
                op = sc.next();

                String msg= op+":"+var1+":"+var2;
//                System.out.println("msg = "+msg);
                dout.writeUTF(msg);

                str = din.readUTF();
                System.out.println("Server Says :"+str);
                System.out.println();
                System.out.print("Terminate ? (y/n) :");
                str = sc.nextLine();
                String str1 = sc.nextLine();
                end(str1,s,dout);
                System.out.println();
            }else{
                System.out.println("Wrong Input");
            }

        }


    }

    private static void end(String res, Socket s, DataOutputStream dout) throws IOException {
        if(res.toLowerCase().equals("y")){
            dout.writeUTF("Yend");
            System.out.println("Bye...");
            System.exit(0);
        }
    }

}
