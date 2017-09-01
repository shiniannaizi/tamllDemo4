package com.tamll.learn.utils;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 */
public class SendJMail {

	/**
	 * 向用户发送激活邮件
	 * @param email 用户邮箱地址
	 * @param emailMsg 用户账号激活链接
	 * @return 发送成功返回True,发送失败返回false
	 */
	public static boolean sendMail(String email, String emailMsg) {
		
		String from = "15881323261@163.com"; 				// 邮件发送人的邮件地址
		String to = email; 										// 邮件接收人的邮件地址
		final String username = "15881323261@163.com";  	//发件人的邮件帐户
		final String password = "xiaosong1995";   					//发件人的邮件密码


		//定义Properties对象,设置环境信息
		Properties props = System.getProperties();

		//设置邮件服务器的地址
		props.setProperty("mail.smtp.host", "smtp.163.com"); // 指定的smtp服务器
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
		//创建Session对象,session对象表示整个邮件的环境信息
		Session session = Session.getInstance(props);
		//设置输出调试信息
		session.setDebug(true);
		try {
			//Message的实例对象表示一封电子邮件
			MimeMessage message = new MimeMessage(session);
			//设置发件人的地址
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.CC,InternetAddress.parse(username));
			message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			//设置主题
			message.setSubject("EasyMail商城用户激活");
			//设置邮件的文本内容
			//message.setText("Welcome to JavaMail World!");
			message.setContent((emailMsg),"text/html;charset=utf-8");
			//从session的环境中获取发送邮件的对象
			Transport transport=session.getTransport();
			//连接邮件服务器
			transport.connect("smtp.163.com",25, username, password);
			//设置收件人地址,并发送消息
			transport.sendMessage(message,new Address[]{new InternetAddress(to)});
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
