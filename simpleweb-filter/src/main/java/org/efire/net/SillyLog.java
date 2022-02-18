package org.efire.net;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value=org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST,proxyMode= ScopedProxyMode.TARGET_CLASS)
public class SillyLog {
	int sequence;
	String uuid;
	String message;
	
	public SillyLog()
	{
		uuid= UUID.randomUUID().toString();
		message="";
		sequence =1;
	}
	public void debug(String msg)
	{
		msg="SillyLog: "+uuid+"/"+ sequence +" "+msg;
		
		System.out.println(msg);
		sequence++;
		message+=msg+"\n\r";
	}
	public String getMessage()
	{
		return message;
	}
	public void resetSequence()
	{
		sequence =1;
		message="";
	}
}