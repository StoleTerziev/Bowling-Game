package com.bowling.game.controller;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bowling.game.model.Frame;
import com.bowling.game.model.MessageResponse;
import com.bowling.game.model.ScoreBowling;
import com.bowling.game.service.ScoreBowlingService;

@RestController
public class ScoreBowlingController {

	
	private ScoreBowlingService scoreBowlingService;

	public ScoreBowlingController(ScoreBowlingService scoreBowlingService) {
		this.scoreBowlingService = scoreBowlingService;
	}

	@RequestMapping(value = "/bowling-game", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<MessageResponse> startNewGame() {
		String id = scoreBowlingService.newGame();
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(id));
	}

	@RequestMapping(value = "/bowling/{id}/result", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<MessageResponse> submitResults(@RequestBody Frame frame, @PathVariable(value = "id") String id) {
		if (scoreBowlingService.submitScore(frame, id)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new MessageResponse("Game with this Id doesn't exist"));
	}

	@RequestMapping(value = "/bowling/{id}/score", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<MessageResponse> getResult(@PathVariable(value = "id") String id) {

		ScoreBowling scoreBowling = scoreBowlingService.getScore(id);

		if (scoreBowling == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new MessageResponse("There is no game with the given ID"));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(scoreBowling));
	}

	@RequestMapping(value = "/bowling/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<MessageResponse> deleteGame(@PathVariable(value = "id") String id) {

		if (scoreBowlingService.deleteGame(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("The game is deleted"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("The game with this id doesn't exist"));
	}
}
