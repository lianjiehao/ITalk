package chatserver;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
	public static void main(String args[]){
		SocketClass socket = new SocketClass();
		socket.mSocket();
	}
}
