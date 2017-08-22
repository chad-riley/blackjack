package com.libertymutual.blackjack.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	private Dealer dealer;
	private Gambler gambler;
	private int currentBet;

	public BlackjackController() {

		dealer = new Dealer();
		gambler = new Gambler();

	}

	@GetMapping("")
	public ModelAndView displayBlackjackForm() {
		if (gambler.isBust()) {
			currentBet = 0;
		}
		ModelAndView mv = new ModelAndView("blackjack/default");
		mv.addObject("gambler", gambler);
		mv.addObject("dealer", dealer);
		mv.addObject("currentBet", currentBet);
		mv.addObject("betState", currentBet == 0 && gambler.getAvailableCash() > 0);
		mv.addObject("roundState", currentBet != 0 && gambler.getAvailableCash() > 0);
		return mv;
	}

	@PostMapping("bet")
	// Any value submitted by a form with the name of "ante"
	// will get bound to the "int ante" parameter.
	public String bet(int ante) {
		// Have the gambler "take" money from its wallet
		// in the amount of "ante" and "give" it to the
		// BlackjackController which will store it in
		// "currentBet"
		currentBet = gambler.ante(ante);
		dealer.startRound();
		dealer.dealCardToGambler(gambler);
		dealer.dealCardToDealer();
		dealer.dealCardToGambler(gambler);
		dealer.dealCardToDealer();
		return "redirect:/";
	}

	@PostMapping("hit")
	public String hit() {
		dealer.dealCardToGambler(gambler);
		return "redirect:/blackjack";
	}

	@PostMapping("stand")
	public String stand() {
		dealer.finishRound();

		if (dealer.isBust()) {
			gambler.payout(currentBet * 2);
		} else if (gambler.hasBlackjack() && !dealer.hasBlackjack()) {
			gambler.payout((3 * currentBet) / 2);
		} else if (gambler.getBestScore() > dealer.getBestScore()) {
			gambler.payout(currentBet * 2);
		}

		currentBet = 0;
		return "redirect:/blackjack";
	}
}