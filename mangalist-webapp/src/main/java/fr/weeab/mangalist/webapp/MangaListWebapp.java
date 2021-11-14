package fr.weeab.mangalist.webapp;

import fr.weeab.mangalist.core.BaseApplication;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Import(value = BaseApplication.class)
public class MangaListWebapp {

	private static final Logger LOG = LoggerFactory.getLogger(MangaListWebapp.class);


	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(MangaListWebapp.class);
		Environment env = app.run(args).getEnvironment();

		String protocol = "http";
		String hostAddress = InetAddress.getLocalHost().getHostAddress();

		if(env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}

		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if(StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}


		LOG.info("\n---------------------------------------------------------\n\t" +
				"Application '{}' is running! Access Urls: \n\t" +
				"Local: \t\t{}://localhost:{}{}\n\t" +
				"External: \t{}://{}:{}{}\n" +
				"---------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath);
	}
}
