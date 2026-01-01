package game.path;

import util.Vector2;
import java.util.List;

public enum Pathtype{
				EASY(List.of(
				new Vector2(20, 20),
				new Vector2(20, 400),
				new Vector2(100, 400),
				new Vector2(100, 20),
				new Vector2(200, 20),
				new Vector2(200, 400),
				new Vector2(300, 400),
				new Vector2(300, 20),
				new Vector2(400, 20)
			)),

			MEDIUM(List.of(
				new Vector2(20, 20),
				new Vector2(20, 450),
				new Vector2(150, 450),
				new Vector2(150, 80),
				new Vector2(300, 80),
				new Vector2(300, 450),
				new Vector2(450, 450),
				new Vector2(450, 20)
			)),

			HARD(List.of(
				new Vector2(20, 20),
				new Vector2(20, 500),
				new Vector2(120, 500),
				new Vector2(120, 100),
				new Vector2(240, 100),
				new Vector2(240, 500),
				new Vector2(360, 500),
				new Vector2(360, 100),
				new Vector2(480, 100),
				new Vector2(480, 500),
				new Vector2(600, 500),
				new Vector2(600, 20)
			)),

		Pathtype_Impossible(
			List.of(
				new Vector2(20, 20),
				new Vector2(20, 550),
				new Vector2(100, 550),
				new Vector2(100, 80),
				new Vector2(180, 80),
				new Vector2(180, 550),
				new Vector2(260, 550),
				new Vector2(260, 80),
				new Vector2(340, 80),
				new Vector2(340, 550),
				new Vector2(420, 550),
				new Vector2(420, 80),
				new Vector2(500, 80),
				new Vector2(500, 550),
				new Vector2(580, 550),
				new Vector2(580, 20)
			));

		private final List<Vector2> waypoints;

		Pathtype(List<Vector2> waypoints)
		{
				this.waypoints = waypoints;
		}

		public List<Vector2> getWaypoints() 
		{
				return waypoints;

		}

}
