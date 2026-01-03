package game.path;

import util.Vector2;
import java.util.List;

public enum Pathtype{
			IMPOSSIBLE(List.of(
				new Vector2(50, 100),
		     	new Vector2(50, 700),
				new Vector2(300, 700),
				new Vector2(300, 100),
				new Vector2(600, 100),
				new Vector2(600, 700),
				new Vector2(900, 700),
				new Vector2(900, 100),
				new Vector2(1150, 100)
			)),

		    HARD(List.of(
				new Vector2(50, 100),
				new Vector2(50, 750),
				new Vector2(250, 750),
		    	new Vector2(250, 200),
				new Vector2(450, 200),
				new Vector2(450, 750),
				new Vector2(650, 750),
				new Vector2(650, 200),
				new Vector2(850, 200),
				new Vector2(850, 750),
				new Vector2(1050, 750),
				new Vector2(1050, 100)
		    )),

			MEDIUM(List.of(
				new Vector2(50, 80),
				new Vector2(50, 720),
				new Vector2(200, 720),
				new Vector2(200, 150),
				new Vector2(350, 150),
				new Vector2(350, 720),
				new Vector2(500, 720),
				new Vector2(500, 150),
				new Vector2(650, 150),
				new Vector2(650, 720),
				new Vector2(800, 720),
				new Vector2(800, 150),
				new Vector2(950, 150),
				new Vector2(950, 720),
				new Vector2(1100, 720),
				new Vector2(1100, 80)
			)),

			EASY(List.of(
				new Vector2(0, 55),
				new Vector2(75, 55),
				new Vector2(75, 210),
				new Vector2(125, 210),
				new Vector2(125, 430),
				new Vector2(220, 430),
				new Vector2(220, 515),
				new Vector2(470, 515),
				new Vector2(470, 360),
				new Vector2(360, 360),
				new Vector2(360, 195),
				new Vector2(475, 195),
				new Vector2(475, 95),
				new Vector2(740, 95),
				new Vector2(740, 247),
				new Vector2(820, 247),
				new Vector2(820, 377),
				new Vector2(710, 377),
				new Vector2(710, 462),
				new Vector2(997, 462),
				new Vector2(997, 220),
				new Vector2(1200, 220)
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
