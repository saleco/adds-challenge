package com.adidas.emailservice.model;

public class EmailDetails {
    private String emailBody;
    private String emailSubject;
    private String emailTo;
    private String from;

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
