package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.blackjack.models.Card;
import com.libertymutual.blackjack.models.Dealer;
import com.libertymutual.blackjack.models.Deck;
import com.libertymutual.blackjack.models.Gambler;
import com.libertymutual.blackjack.models.Hand;

@Controller
@RequestMapping({ "/", "/blackjack" })
public class BlackjackController {
	
		private Deck runningDeck;
		private Dealer dealer;
		private Gambler gambler;
		
	public BlackjackController() {

		runningDeck = new Deck();
		dealer = new Dealer();
		gambler = new Gambler();

	}

	@GetMapping("")
	public ModelAndView displayBlackjackForm() {
		Hand hand = new Hand();
		ModelAndView mv = new ModelAndView("blackjack/default");
		mv.addObject("hand", hand);
		return mv;
	}

	@PostMapping("bet")
	public String bet() {
		Card cardToDeal = runningDeck.getCard();
		gambler.giveCard(cardToDeal);
		cardToDeal = runningDeck.getCard();
		dealer.giveCard(cardToDeal);
		cardToDeal = runningDeck.getCard();
		gambler.giveCard(cardToDeal);
		cardToDeal = runningDeck.getCard();
		dealer.giveCard(cardToDeal);
		
		return "redirect:/blackjack";
	
}

}