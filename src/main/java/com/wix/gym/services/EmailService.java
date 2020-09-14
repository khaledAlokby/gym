package com.wix.gym.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendTextEmail(String email,String className) {

		SimpleMailMessage msg = new SimpleMailMessage();
		try {


					msg.setTo(email);
					msg.setSubject(className +" enrollment");
					msg.setText("You have successfully enrolled in class : "+className);
					try {
						javaMailSender.send(msg);
					}catch (Exception ex){
						System.out.println("For this feature to work, let your settings in your email accept less secure apps");
					}


		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
