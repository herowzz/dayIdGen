package com.github.herowzz.dayIdGen.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

import com.github.herowzz.dayIdGen.server.handler.IdHandler;

public class DayIdGen {

	public static void main(String[] args) throws Exception {
		Server server = new Server(12000);
		ContextHandler context = new ContextHandler();
		context.setContextPath("/idServer");
		context.setHandler(new IdHandler());
		server.setHandler(context);

		server.start();
		server.dumpStdErr();
		server.join();
	}
}
