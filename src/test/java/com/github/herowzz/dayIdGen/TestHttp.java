package com.github.herowzz.dayIdGen;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;

public class TestHttp {

	public static void main(String[] args) throws Exception {
		Server server = new Server();

		ServerConnector http1 = new ServerConnector(server);
		http1.setPort(8080);
		http1.setIdleTimeout(30000);

		ServerConnector http2 = new ServerConnector(server);
		http2.setPort(8081);
		http2.setIdleTimeout(30000);
		
		server.setConnectors(new Connector[] { http1, http2 });

		ContextHandler context = new ContextHandler();
		context.setContextPath("/dayIdGen");
		context.setHandler(new TestHttpHandle());
		server.setHandler(context);

		server.start();
		server.join();
	}

}
