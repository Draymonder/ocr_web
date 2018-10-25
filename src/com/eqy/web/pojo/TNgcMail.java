package com.eqy.web.pojo;

public class TNgcMail {
	
	private String myemailaccount;

    private String myemailpassword;

    private String myemailsmtphost;

    private String smtpport;
    
   
    public String getMyemailaccount() {
        return myemailaccount;
    }

    public void setMyemailaccount(String myemailaccount) {
        this.myemailaccount = myemailaccount == null ? null : myemailaccount.trim();
    }

    public String getMyemailpassword() {
        return myemailpassword;
    }

    public void setMyemailpassword(String myemailpassword) {
        this.myemailpassword = myemailpassword == null ? null : myemailpassword.trim();
    }

    public String getMyemailsmtphost() {
        return myemailsmtphost;
    }

    public void setMyemailsmtphost(String myemailsmtphost) {
        this.myemailsmtphost = myemailsmtphost == null ? null : myemailsmtphost.trim();
    }

    public String getSmtpport() {
        return smtpport;
    }

    public void setSmtpport(String smtpport) {
        this.smtpport = smtpport == null ? null : smtpport.trim();
    }
}