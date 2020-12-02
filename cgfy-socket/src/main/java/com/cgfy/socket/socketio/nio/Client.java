package com.cgfy.socket.socketio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
	/**
	 * IO(BIO)和NIO的区别
	 * 其本质就是阻塞与非阻塞的区别
	 * 阻塞概念:应用程序在获取网络数据的时候,如果网络传输数据(网络带宽或者传输效率)很慢,那么程序就一直等着,直到传输完毕为止
	 * 非阻塞概念:应用程序直接可以获取已经准备好的数据(服务端数据事先存入buffer缓存区),无需等待
	 * BIO为同步阻塞形式,NIO为同步非阻塞形式,NIO并没有实现异步,在JDK1.7之后,升级了NIO库包,支持异步非阻塞通信模型即NIO2.0(AIO)
	 * 同步和异步:同步和异步一般是面对操作系统与应用程序对IO操作的层面上来区别的
	 * 同步时,应用程序会直接参与IO读写操作,并且我们的应用程序会直接阻塞到某一个方法上,直到数据准备就绪;或者采用轮询的策略实时检查数据的就绪状态,如果就绪则获取数据
	 * 异步时,则所有的IO读写操作交给操作系统处理,与我们的应用程序没有直接关系,我们程序不需要关心IO读写,当操作系统完成了IO读写操作时,会给我们应用程序发送通知,我们的应用程序直接拿走数据即可
	 * 同步说的是你的server服务器端的执行方式
	 * 阻塞说的是具体的技术,接受数据的方式,状态(io,nio)
	 * 在介绍NIO之前,先澄清一个概念,NIO叫Non-block IO既非阻塞IO学习NIO编程之前先要了解几个概念
	 * Buffer(缓冲区),channel(管道,通道),Selector(选择器,多路复用器)
	 * @param args
	 */

	//需要一个Selector 
	public static void main(String[] args) {
		
		//创建连接的地址
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8765);
		
		//声明连接通道
		SocketChannel sc = null;
		
		//建立缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		try {
			//打开通道
			sc = SocketChannel.open();
			//进行连接
			sc.connect(address);
			
			while(true){
				//定义一个字节数组，然后使用系统录入功能：
				byte[] bytes = new byte[1024];
				System.in.read(bytes);
				
				//把数据放到缓冲区中
				buf.put(bytes);
				//对缓冲区进行复位
				buf.flip();
				//写出数据
				sc.write(buf);
				//清空缓冲区数据
				buf.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(sc != null){
				try {
					sc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
