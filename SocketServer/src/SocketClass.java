package chatserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class SocketClass {
	ServerSocket ss = null;
	Socket s = null;
	Map<String, Socket> map = new HashMap<String, Socket>();
	JSONObject mJSONObject=null;
	public void mSocket() {
		try {
			ss = new ServerSocket(8001);
			while (true) {
				System.out.println("准备阻塞...");
				s = ss.accept();
				System.out.println("阻塞完成。。。");
				
				InputStreamReader ins = null;
				PrintStream out = null;
				BufferedReader in =null;
				//认证
				ins = new InputStreamReader(s.getInputStream(),"UTF-8");
				in = new BufferedReader(ins);
				map.put(in.readLine(), s);
				System.out.println("认证成功！！！");
				new Thread(new Runnable() {
					InputStreamReader ins = null;
					PrintStream out = null;
					BufferedReader in =null;
					int tag=0;
					@Override
					public void run() {
						try {
							//消息侦听
							while (true) {
								ins = new InputStreamReader(s.getInputStream(),"UTF-8");
								in = new BufferedReader(ins);
								System.out.println("服务器准备接受消息。。。");
								String msg=in.readLine();
								System.out.println("服务器收到消息");
								mJSONObject=JSONObject.fromObject(msg);
								String to=mJSONObject.getString("to");
								String content = mJSONObject.getString("content");
								String from = mJSONObject.getString("from");
								System.out.println("message from "+from+" to "+to+" : "
										+ content);
								//转发给目标
								if((Socket) map.get(to)!=null){//判定对方在线
									 PrintWriter pout = null;  
									 pout = new PrintWriter(new BufferedWriter(    
					                         new OutputStreamWriter(((Socket) map.get(to)).getOutputStream(),"UTF-8")),true);
//								out= new PrintStream(((Socket) map.get(to)).getOutputStream());
								pout.print(msg+"\n");
								pout.flush();
								}
							    s=(Socket) map.get(from);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
