package it.olly.fcmbe.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.olly.fcmbe.messaging.fb.FirebaseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private FirebaseUtils firebaseUtils;

    @PostMapping(value = "send", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String sendMessage(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String receiverToken = req.getParameter("receiverToken");
        String body = req.getParameter("body");
        String sent = firebaseUtils.sendMessageViaHTTP(receiverToken, body);
        return "<html><body>[" + sent + "]<br><a href=\"/?token=" + receiverToken + "\">back</a></body></html>";
    }

}
