package com.genealogy.common.utils;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务信息Banner
 * @author guofazhan
 * @version [版本号, 2018/7/18 0018 0001]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ServiceBanner implements Banner {

	private static List<String> BANNER = new ArrayList();

	private static final int STRAP_LINE_SIZE = 50;


	private static String ip;

	private static String name;

	private static int cpu;


	public static Map<String,String> INFO_MAP= new LinkedHashMap<>();
	static {
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
			name = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		cpu = Runtime.getRuntime().availableProcessors() * 2;
		BANNER.add("********************SERVICE INFO********************");
		BANNER.add("********************SERVICE INFO********************");
		INFO_MAP.put(" :: MACHINE_IP :: ", "("+ip+")");
		INFO_MAP.put(" :: MACHINE_NAME :: ", "("+name+")");
		INFO_MAP.put(" :: CPU_COUNT :: ", "("+cpu+")");
		String version = SpringBootVersion.getVersion();
		INFO_MAP.put(" :: Spring Boot :: ", version == null ? "" : " (v" + version + ")");
	}


	/**
	 * @param environment
	 * @param aClass
	 * @param printStream
	 */
	@Override
	public void printBanner(Environment environment, Class<?> aClass,
			PrintStream printStream) {
		printStream.println(" ");
		printStream.println(BANNER.get(0));

		INFO_MAP.keySet().forEach((key)->{
			String padding;
			for(padding = ""; padding.length() < STRAP_LINE_SIZE - (key.length() + INFO_MAP.get(key).length()); padding = padding + " ") {
				;
			}
			printStream.println(AnsiOutput
					.toString(new Object[]{AnsiColor.GREEN, key, AnsiColor.DEFAULT, padding, AnsiStyle.FAINT, INFO_MAP.get(key)}));
		});
		printStream.println(BANNER.get(1));
		printStream.println();

	}
}
