
package game.path;

import util.Vector2;

import java.util.ArrayList;
import java.util.List;


public class Path {
		private final List<Vector2> waypoints;


		public Path (Pathtype pathtype){
				this.waypoints = List.copyOf(pathtype.getWaypoints());
		}

		public Vector2 get_waypoint(int i){
				return this.waypoints.get(i);
		}
	
		public int get_waypoint_amount(){
				return this.waypoints.size();
		}

		



}
