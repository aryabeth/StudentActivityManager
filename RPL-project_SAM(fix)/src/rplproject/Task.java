package rplproject;

public class Task {
	private int id;
	private String title;
	private String start;
	private String end;
	private String desk;
	private String tipe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getDesk() {
		return desk;
	}
	public void setDesk(String desk) {
		this.desk = desk;
	}
	public String getTipe(){
		return tipe;
	}
	public void setTipe(String tipe){
		this.tipe = tipe;
	}
	
}

