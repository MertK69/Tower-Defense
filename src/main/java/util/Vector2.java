package util;

import java.lang.Math;

public class Vector2 {
		private double Ko_X;
		private double Ko_Y;

		public Vector2(double X, double Y){
				this.Ko_X = X;
				this.Ko_Y = Y;
		}

		public Vector2 direction_to(Vector2 Waypoint){
				double X_length = Waypoint.Ko_X - this.Ko_X;
				double Y_length = Waypoint.Ko_Y - this.Ko_Y;
				return new Vector2(X_length, Y_length);
		}

		public double Vector_length(){
				double Vector_length = Math.sqrt(Ko_X * Ko_X + Ko_Y * Ko_Y);
				return Vector_length;
		}

		public Vector2 Normierung(){
				double Vector_length = Vector_length();
				
				if (Vector_length != 0){
						double normed_Ko_X = this.Ko_X / Vector_length;
						double normed_Ko_Y = this.Ko_Y / Vector_length;
				return new Vector2(normed_Ko_X, normed_Ko_Y);
				}
				return new Vector2(0d,0d);
		}

		public Vector2 Scale(double factor){
				Vector2 scaled_vector = new Vector2(this.Ko_X * factor, this.Ko_Y * factor);
				return scaled_vector; 
		}

		public Vector2 Addition( Vector2 other){
				return new Vector2 (this.Ko_X + other.Ko_X, this.Ko_Y + other.Ko_Y);
		}
		
}
