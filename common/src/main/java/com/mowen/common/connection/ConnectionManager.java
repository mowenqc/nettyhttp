package com.mowen.common.connection;

import io.netty.channel.Channel;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * mowen_parent com.mowen.common.connection
 *
 * @author: mowen
 * @createTime: 2019/6/9 14:45
 * @group:
 * @description:
 */
public class ConnectionManager {

	private static BlockingQueue<Channel> waitQueue = new LinkedBlockingQueue<>();

	private static Set<Channel> workQueue = new HashSet<>();

	public static void offerWaitQueue(Channel channel){
		waitQueue.add(channel);
	}

	public static int waitQueueSize(){
		return waitQueue.size();
	}

	public static Channel takeWaitQueue(){
		try {
			Channel take = waitQueue.take();
			return take;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void addWorkQueue(Channel channel){
		workQueue.add(channel);
	}
}
