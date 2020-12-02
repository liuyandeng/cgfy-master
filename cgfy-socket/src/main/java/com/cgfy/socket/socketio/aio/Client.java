package com.cgfy.socket.socketio.aio;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

/**
 * AIO编程，在NIO基础之上引入了异步通道的概念，并提供了异步文件和异步 套接字通道的实现，从而在真正意义上实现了异步非阻塞，之前我们学习的 NIO只是非阻塞而并非异步。
 * 而AIO它不需要通过多路复用器对注册的通道进行轮询操作即可实现异步读写，从而简化了NIO编程模型。也可以称之为 NIO2.0,这种模式才真正的属于我们异步非阻塞的模型。
 * AsynchronousServerScoketChannel
 * AsynchronousScoketChanel
 */
public class Client implements Runnable{

	private AsynchronousSocketChannel asc ;
	
	public Client() throws Exception {
		asc = AsynchronousSocketChannel.open();
	}
	
	public void connect(){
		asc.connect(new InetSocketAddress("127.0.0.1", 8765));
	}
	
	public void write(String request){
		try {
			asc.write(ByteBuffer.wrap(request.getBytes())).get();
			read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void read() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			asc.read(buf).get();
			buf.flip();
			byte[] respByte = new byte[buf.remaining()];
			buf.get(respByte);
			System.out.println(new String(respByte,"utf-8").trim());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		Client c1 = new Client();
		c1.connect();
		
		Client c2 = new Client();
		c2.connect();
		
		Client c3 = new Client();
		c3.connect();
		
		new Thread(c1, "c1").start();
		new Thread(c2, "c2").start();
		new Thread(c3, "c3").start();
		
		Thread.sleep(1000);
		
		c1.write("c1 aaa");
		c2.write("c2 bbbb");
		c3.write("c3 ccccc");
	}
	
}
