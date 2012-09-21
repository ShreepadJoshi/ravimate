package com;

public class Main {
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main().doWork();
		System.out.println("Done....");
	}

	private void doWork() {
		String[] urls = null;
		
		ReadeSakal readeSakal = new ReadeSakal();
		GMail mail = new GMail();
		readeSakal.getURLs("http://epaper.esakal.com/esakal/index.htm");
		urls = readeSakal.getUrls();
		String[] titles = readeSakal.getTitles();
		int i;
		for (i=0;i<urls.length;i++){
			String mailBody = readNews(urls[i]);
			System.out.println("Sending mail..");
			sendMail(mail,titles[i],mailBody);			
			System.out.println("Done "+ i);
		}
		//System.out.println("Done "+ i);
		mail.closeConnection();
	}
	

	private String readNews(String url) {		
		return new ReadeSakal().readPaper(url);
	}

	private void sendMail(GMail mail,String subject,String mailBody) {
		
		
			mail.sendMail(subject,mailBody);
		
	}

}
