package bet.service.utils;

import bet.api.constants.GameStatus;
import bet.model.Game;
import bet.repository.GameRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper methods for games schedule
 */
@Component
public class GamesSchedule {

	@Autowired
	private GameRepository gameRepository;

	/**
	 * Checks if there is a currently active game.
	 * Start date has passed and status is TIMED or IN_PLAY
	 * @param date
	 * @return
	 */
	public boolean hasActiveGame(ZonedDateTime date) {
		return getActiveGames(date).size() > 0;
	}

	public List<Game> getActiveGames(ZonedDateTime date) {
		return Lists.newArrayList(gameRepository.findAll()).stream()
				.filter(game -> game.getGameDate().isBefore(date) && (game.getStatus().equals(GameStatus.TIMED) || game.getStatus().equals(GameStatus.IN_PLAY)))
				.collect(Collectors.toList());
	}
}