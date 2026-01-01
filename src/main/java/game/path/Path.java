
package game.path;

import util.Vector2;

import java.util.ArrayList;
import java.util.List;


public class Path {
		private final List<Vector2> Waypoints;


		public Path (List<Vector2> Waypoints){
				if (Waypoints.size() < 1) {
						throw new IllegalArgumentException();
				}
				this.Waypoints = List.copyOf(Waypoints);
		}

		public Vector2 get_waypoint(int i){
				return this.Waypoints.get(i);
		}
	
		public int get_waypoint_amount(){
				return this.Waypoints.size();
		}

		



}
