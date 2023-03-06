package kg.mega.natv_v1.services;

public interface EmailService {

    void send(String to, String subject, String text);
}
