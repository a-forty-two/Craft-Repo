package net.librec.util;

public class RatingContext implements Comparable<RatingContext> {

<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
<<<<<<< HEAD
    private long timestamp;

    private int user;

    private int item;

    public RatingContext(int user, int item, long timestamp) {
        this.user = user;
        this.item = item;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(RatingContext o) {
        // TODO Auto-generated method stub

        double res = this.timestamp - o.timestamp;
        if (res > 0)
            return 1;
        else if (res < 0)
            return -1;
        return 0;
    }

    public int getUser() {
        return user;
    }

    public int getItem() {
        return item;
    }
=======
=======
>>>>>>> last minute commit.
	private long timestamp;
	
	private int user;
	
	private int item;
	
	public RatingContext(int user, int item, long timestamp){
		this.user = user;
		this.item = item;
		this.timestamp = timestamp;
	}
	
	@Override
	public int compareTo(RatingContext o) {
		// TODO Auto-generated method stub
		
		double res = this.timestamp - o.timestamp;
		if (res > 0)
			return 1;
		else if (res < 0)
			return -1;
		return 0;
	}
	
	public int getUser(){
		return user;
	}
	
	public int getItem(){
		return item;
	}
<<<<<<< refs/remotes/2.0.0-beta/2.0.0-beta
>>>>>>> master
=======
>>>>>>> last minute commit.

}
