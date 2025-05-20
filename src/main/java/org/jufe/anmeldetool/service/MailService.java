package org.jufe.anmeldetool.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.jufe.anmeldetool.entity.anmeldung.Anmeldung;
import org.jufe.anmeldetool.entity.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final String SUBJECT = "Deine Anmeldung für: ";

    /* @formatter:off */
    private static final String TEXT = """
Hallo %s,
Vielen Dank für deine Anmeldung.
Bitte überprüfe deine persönlichen Daten <a href='%s'>hier</a>und bestätige Sie dann.

Mit freundlichen Grüßen,
Dein JuFe-Team!
            """;

    public final InternetAddress from;

    private final JavaMailSender mailSender;
    /* @formatter:on */

    @Autowired
    public MailService(JavaMailSender mailSender) throws AddressException {
        this.from = new InternetAddress("no-reply@jufe.org");
        this.mailSender = mailSender;
    }

    private void sendMail(InternetAddress to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mmHelper = new MimeMessageHelper(message, false, Encoding.DEFAULT_CHARSET.displayName());
        mmHelper.setFrom(from.toString());
        mmHelper.setTo(to.toString());
        mmHelper.setSubject(subject);
        mmHelper.setText(text);
        mailSender.send(mmHelper.getMimeMessage());
    }

    public void sendConfirmationMail(InternetAddress to, Event event, Anmeldung anmeldung) throws MessagingException {
        String subject = SUBJECT + event.getName();
        String text = String.format(TEXT, anmeldung.getVorname(), anmeldung.getId());
        sendMail(to, subject, text);
    }

}
