package bookstore.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditCardController {
    @GetMapping ("/cards/{u_email}")
    List<CreditCard> getCardsByUser (@PathVariable String u_email) {
        User u = UserController.getUserByUserEmail(u_email);
        List<CreditCard> temp = CreditCard.getAllCards();
        List<CreditCard> cards = new ArrayList<CreditCard>();
        for (CreditCard c : temp) {
            if (c.getCardUser().toLowerCase().equals(u_email)) cards.add(c);
        }
        if (cards.size()==0) throw new CreditCardNotFoundException(u_email);
        return cards;
    }
}
