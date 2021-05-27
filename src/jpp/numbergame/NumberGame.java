package jpp.numbergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGame {

	int width;
	int height;
	int points = 0;
	int initialTiles;
	int[][] feld;
	private static Random ran = new Random();
	List<Move> moveList = new ArrayList<Move>();

	public NumberGame(int width, int height) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
		feld = new int[height][width];

	}

	public NumberGame(int width, int height, int initialTiles) {
		if (initialTiles < 0 || initialTiles > width * height) {
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
		feld = new int[height][width];
		this.initialTiles = initialTiles;
		for (int i = 0; i < initialTiles; i++) {
			addRandomTile();
		}

	}

	public int get(Coordinate2D coord) {
		int value = feld[coord.getY()][coord.getX()];
		if (coord.getY() > feld.length || coord.getX() > feld[0].length) {
			throw new IndexOutOfBoundsException();
		}

		return value;

	}

	public int get(int x, int y) {
		int value = feld[y][x];
		if (y < 0 || y > feld.length || x < 0 || x > feld[0].length) {
			throw new IndexOutOfBoundsException();
		}
		return value;

	}

	public int getPoints() {
		return points;

	}

	public boolean isFull() {
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[0].length; j++) {
				if (feld[i][j] == 0) {
					return false;
				}
			}
		}
		return true;

	}

	public Tile addRandomTile() {
		if (isFull()) {
			throw new TileExistsException();
		}
		Tile tile = null;
		for (int i = 0; i < height * width; i++) {
			double d = Math.random();
			int result = 0;
			if (d > 0.1) {
				result = 2;
			} else {
				result = 4;
			}
			int ranY = ran.nextInt(height);
			int ranX = ran.nextInt(width);
			if (feld[ranY][ranX] == 0) {
				Coordinate2D coor = new Coordinate2D(ranX, ranY);
				tile = new Tile(coor, result);
				feld[ranY][ranX] = result;
				return tile;
			}

		}

		return tile;
	}

	public Tile addTile(int x, int y, int value) {
		if (feld[y][x] == 0) {
			Coordinate2D coor = new Coordinate2D(x, y);
			Tile tile = new Tile(coor, value);
			feld[y][x] = value;
			return tile;
		} else
			throw new TileExistsException();

	}

	public List<Move> move(Direction dir) {
		Coordinate2D coorFrom = null;
		Coordinate2D coorTo = null;
		Move move = null;
		int value = 0;
		int tempy = 0;
		switch (dir) {
		case UP:
			for (int x = 0; x < feld.length; x++) {
				for (int y = 0; y < feld.length; y++) {

					if (feld[y][x] != 0) {
						tempy = y + 1;
						while (tempy < feld.length && feld[tempy][x] == 0) {
							tempy++;
						}
						if (tempy < feld.length && feld[y][x] == feld[tempy][x]) {
							coorFrom = new Coordinate2D(x, tempy);
							coorTo = new Coordinate2D(x, y);
							move = new Move(coorFrom, coorTo, feld[tempy][x], feld[y][x] * 2);
							value = feld[y][x] * 2;
							feld[tempy][x] = 0;
							feld[y][x] = value;
							points += value;
							moveList.add(move);

						}
					} else {
						tempy = y + 1;
						while (tempy < feld.length && feld[tempy][x] == 0) {
							tempy++;
						}
						if (tempy < feld.length) {
							coorFrom = new Coordinate2D(x, tempy);
							coorTo = new Coordinate2D(x, y);
							move = new Move(coorFrom, coorTo, feld[tempy][x]);
							value = feld[tempy][x];
							feld[tempy][x] = 0;
							feld[y][x] = value;
							moveList.add(move);
							y--;

						}

					}

				}
			}
			break;
		///////////////////// ******** DOWN **********\\\\\\\\\\\\\\\\
		case DOWN:
			for (int x = 0; x < feld.length; x++) {
				for (int y = feld.length - 1; y >= 0; y--) {
					if (feld[y][x] != 0) {
						tempy = y - 1;
						while (tempy >= 0 && feld[tempy][x] == 0) {
							tempy--;
						}
						if (tempy >= 0 && feld[y][x] == feld[tempy][x]) {
							coorFrom = new Coordinate2D(x, tempy);
							coorTo = new Coordinate2D(x, y);
							move = new Move(coorFrom, coorTo, feld[tempy][x], feld[y][x] * 2);
							value = feld[y][x] * 2;
							feld[tempy][x] = 0;
							feld[y][x] = value;
							points += value;
							moveList.add(move);

						}
					} else {
						tempy = y - 1;
						while (tempy >= 0 && feld[tempy][x] == 0) {
							tempy--;
						}
						if (tempy >= 0) {
							coorFrom = new Coordinate2D(x, tempy);
							coorTo = new Coordinate2D(x, y);
							move = new Move(coorFrom, coorTo, feld[tempy][x]);
							value = feld[tempy][x];
							feld[tempy][x] = 0;
							feld[y][x] = value;
							moveList.add(move);
							y++;

						}

					}

				}
			}
			break;
		///////////////////// ******** LEFT **********\\\\\\\\\\\\\\\\

		case LEFT:
			for (int x = 0; x < feld.length; x++) {
				for (int y = 0; y < feld.length; y++) {
					if (feld[x][y] != 0) {
						tempy = y + 1;
						while (tempy < feld.length && feld[x][tempy] == 0) {
							tempy++;
						}
						if (tempy < feld.length && feld[x][y] == feld[x][tempy]) {
							coorFrom = new Coordinate2D(tempy, x);
							coorTo = new Coordinate2D(y, x);
							move = new Move(coorFrom, coorTo, feld[x][tempy], feld[x][y] * 2);
							value = feld[x][y] * 2;
							feld[x][tempy] = 0;
							feld[x][y] = value;
							points += value;
							moveList.add(move);

						}
					} else {
						tempy = y + 1;
						while (tempy < feld.length && feld[x][tempy] == 0) {
							tempy++;
						}
						if (tempy < feld.length) {
							coorFrom = new Coordinate2D(tempy, x);
							coorTo = new Coordinate2D(y, x);
							move = new Move(coorFrom, coorTo, feld[x][tempy]);
							value = feld[x][tempy];
							feld[x][tempy] = 0;
							feld[x][y] = value;
							moveList.add(move);
							y--;

						}

					}

				}
			}
			break;
		///////////////////// ******** RIGHT **********\\\\\\\\\\\\\\\\
		case RIGHT:
			for (int x = 0; x < feld.length; x++) {
				for (int y = feld.length - 1; y >= 0; y--) {
					if (feld[x][y] != 0) {
						tempy = y - 1;
						while (tempy >= 0 && feld[x][tempy] == 0) {
							tempy--;
						}
						if (tempy >= 0 && feld[x][y] == feld[x][tempy]) {
							coorFrom = new Coordinate2D(tempy, x);
							coorTo = new Coordinate2D(y, x);
							move = new Move(coorFrom, coorTo, feld[x][tempy], feld[x][y] * 2);
							value = feld[x][y] * 2;
							feld[x][tempy] = 0;
							feld[x][y] = value;
							points += value;
							moveList.add(move);

						}
					} else {
						tempy = y - 1;
						while (tempy >= 0 && feld[x][tempy] == 0) {
							tempy--;
						}
						if (tempy >= 0) {
							coorFrom = new Coordinate2D(x, tempy);
							coorTo = new Coordinate2D(x, y);
							move = new Move(coorFrom, coorTo, feld[x][tempy]);
							value = feld[x][tempy];
							feld[x][tempy] = 0;
							feld[x][y] = value;
							moveList.add(move);
							y++;

						}

					}

				}
			}
			break;
		}
		return moveList;

	}

	public boolean canMove(Direction dir) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (feld[i][j] != 0) {
					if (dir == Direction.UP) {
						if (i != 0) {
							if (feld[i - 1][j] == 0) {
								return true;
							}

							else if (feld[i - 1][j] == feld[i][j]) {
								return true;
							}
						}

					} else if (dir == Direction.DOWN) {
						if (i != height - 1) {
							if (feld[i + 1][j] == 0) {
								return true;
							} else if (feld[i + 1][j] == feld[i][j]) {
								return true;
							}
						}
					} else if (dir == Direction.RIGHT) {
						if (j != width - 1) {
							if (feld[i][j + 1] == 0) {
								return true;
							} else if (feld[i][j + 1] == feld[i][j]) {
								return true;
							}
						}
					} else if (dir == Direction.LEFT) {
						if (j != 0) {
							if (feld[i][j - 1] == 0) {
								return true;
							} else if (feld[i][j - 1] == feld[i][j]) {
								return true;
							}
						}
					}

				}
			}
		}

		return false;

	}

	public boolean canMove() {
		if (canMove(Direction.UP) == true || canMove(Direction.DOWN) == true || canMove(Direction.LEFT) == true
				|| canMove(Direction.LEFT) == true) {
			return true;
		}
		return false;

	}

	public static void main(String[] args) {
		NumberGame game = new NumberGame(4, 4);

		game.addTile(0, 0, 2);
		game.addTile(0, 1, 2);
		game.addTile(0, 2, 2);
		game.addTile(0, 3, 2);
		game.addTile(1, 0, 2);
		game.addTile(1, 1, 2);
		game.addTile(1, 2, 2);
		game.addTile(1, 3, 2);
		game.addTile(2, 0, 2);
		game.addTile(2, 1, 2);
		game.addTile(2, 2, 2);
		game.addTile(2, 3, 2);
		game.addTile(3, 0, 2);
		game.addTile(3, 1, 2);
		game.addTile(3, 2, 2);
		game.addTile(3, 3, 2);

		System.out.println(game.canMove(Direction.RIGHT));

		for (int i = 0; i < game.feld.length; i++) {
			for (int j = 0; j < game.feld[0].length; j++) {
				System.out.print(game.feld[i][j] + " ");

			}
			System.out.print("\n");
		}
		System.out.println("Punkte zu Beginn: " + game.getPoints());

		game.move(Direction.RIGHT);

		for (int i = 0; i < game.feld.length; i++) {
			for (int j = 0; j < game.feld[0].length; j++) {
				System.out.print(game.feld[i][j] + " ");

			}
			System.out.print("\n");
		}
		System.out.println("Punkte danach: " + game.getPoints());
	}

}