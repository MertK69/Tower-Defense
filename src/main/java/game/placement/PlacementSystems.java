package game.placement;

import java.util.List;

import game.path.Path;
import game.tower.Tower;
import game.tower.TowerType;
import util.Vector2;

public class PlacementSystems {

		public static final double TOWER_CLEARANCE = 36d;
		public static final double PATH_CLEARANCE = 30d;

		public PlacementSystems()
		{}

		public void placeTower(List<Tower>towers, TowerType type, Vector2 position)
		{
				towers.add(new Tower(type, position));
		}

		public boolean isValidPlacement(List<Tower> towers, Path path, Vector2 position)
		{
				for (Tower tower : towers)
				{
						double distance = tower.getPosition().direction_to(position).Vector_length();
						if (distance < TOWER_CLEARANCE)
						{
								return false;
						}
				}

				int waypointAmount = path.get_waypoint_amount();
				for (int i = 0; i < waypointAmount - 1; i++)
				{
						Vector2 segmentStart = path.get_waypoint(i);
						Vector2 segmentEnd = path.get_waypoint(i + 1);
						double distance = distanceToSegment(position, segmentStart, segmentEnd);
						if (distance < PATH_CLEARANCE)
						{
								return false;
						}
				}

				return true;
		}

		private double distanceToSegment(Vector2 point, Vector2 a, Vector2 b)
		{
				double abX = b.getX() - a.getX();
				double abY = b.getY() - a.getY();
				double lengthSquared = abX * abX + abY * abY;

				if (lengthSquared == 0d)
				{
						return a.direction_to(point).Vector_length();
				}

				double apX = point.getX() - a.getX();
				double apY = point.getY() - a.getY();
				double t = (apX * abX + apY * abY) / lengthSquared;
				t = Math.max(0d, Math.min(1d, t));

				Vector2 closest = new Vector2(a.getX() + abX * t, a.getY() + abY * t);
				return closest.direction_to(point).Vector_length();
		}

}
