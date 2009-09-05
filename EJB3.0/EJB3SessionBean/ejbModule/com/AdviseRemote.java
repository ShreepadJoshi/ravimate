package com;
import javax.ejb.Remote;

@Remote
public interface AdviseRemote {
	String getAdvise();
}
