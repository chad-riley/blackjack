package com.libertymutual.blackjack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
		private int currentBet;
		
	public BlackjackController() {

		runningDeck = new Deck();
		dealer = new Dealer();
		gambler = new Gambler();

	}

	@GetMapping("")
	public String displayBlackjackForm(Model model) {
		if (gambler.isBust()) {
			currentBet = 0;
		}
		Hand dealerHand = dealer.getHand();
		Hand gamblerHand = gambler.getHand();
		model.addAttribute("dealerHand", dealerHand);
		model.addAttribute("gamblerHand", gamblerHand);
		model.addAttribute("currentBet", currentBet);
		model.addAttribute("betState", currentBet == 0 && gambler.getAvailableCash() > 0);
		model.addAttribute("roundState", currentBet != 0 && dealer.getNumberofCardsLeft() > 0);
		return "blackjack/default";
		
	}

	@PostMapping("bet")
	public String bet(int bet) {
		currentBet = gambler.ante(bet);
		runningDeck.shuffle();
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
	
	@PostMapping ("hit") 
	public String hit() {
		
		Card cardToDeal = runningDeck.getCard();
		gambler.giveCard(cardToDeal);
		cardToDeal = runningDeck.getCard();
		
		return "redirect:/blackjack";
	}
	
	@PostMapping ("stand") 
	public String stand() {
		dealer.finishHitting(runningDeck);
		if (dealer.isBust()) {
			gambler.payout(currentBet * 2);
		} else if (gambler.hasBlackjack() && !dealer.hasBlackjack()) {
			gambler.payout(currentBet = currentBet / 2);
		} else if (gambler.getBestScore() > dealer.getBestScore()) {
			gambler.payout(currentBet * 2);
		}
		
		currentBet = 0;
		return "redirect:/blackjack";
		
	}
	
	@PostMapping ("nexthand")
	public String nextHand() {
		dealer = new Dealer();
		gambler = new Gambler();
		return "blackjack/default";		
	}
}