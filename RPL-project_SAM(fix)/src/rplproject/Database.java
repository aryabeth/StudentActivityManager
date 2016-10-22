package rplproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;

public class Database {

	private class structTask {
		int tipe;
		Task isi;
	}

	public static Stack<structTask> stak = new Stack();
	public static Stack<structTask> stakre = new Stack();

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:rpl.sqlite");
			createTable1();
			createTable2();	
			//createTable3();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {	
			//createTable1();
			e.printStackTrace();
		}
	}
	
	public void createTable1(){
		String statement = "CREATE TABLE IF NOT EXISTS taks (id INTEGER NOT NULL PRIMARY KEY, title TEXT, start DATETIME, end DATETIME, desk TEXT, tipe TEXT)";
		try {
			ps = conn.prepareStatement(statement);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
	public void createTable2(){
		String statement = "CREATE TABLE IF NOT EXISTS log (id INTEGER NOT NULL PRIMARY KEY , tasktitle TEXT, date DATETIME DEFAULT CURRENT_DATE, time DATETIME DEFAULT CURRENT_TIME, action TEXT)";
		try {
			ps = conn.prepareStatement(statement);
		ps.executeUpdate();
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cleartask() {
		try {
			
			while(!stak.empty()){
				stak.pop();
			}
			while(!stakre.empty()){
				stakre.pop();
			}
			ps = conn.prepareStatement("DELETE FROM taks");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertTaks(String taskName, String date, String date1,
			String desk, String tipe) throws SQLException {
		if (date.isEmpty()) {
			date = null;
		}
		if (date1.isEmpty()) {
			date1 = null;
		}
		try {
			ps = conn
					.prepareStatement("insert into taks(title,start,end,desk, tipe) values (?,date(?),date(?),?,?)");
			ps.setString(1, taskName);
			ps.setString(2, date);
			ps.setString(3, date1);
			ps.setString(4, desk);
			ps.setString(5, tipe);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select id from taks order by id desc limit 1 ");
		Task a = new Task();
		while (rs.next()) {
			a.setId(rs.getInt("id"));
		}

		structTask insert = new structTask();
		insert.tipe = 1;
		insert.isi = a;
		stak.push(insert);
	}

	public void updateTaks(String id, String taksName, String date,
			String date1, String desk, String tipe) throws SQLException {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from taks where id = " + id);
			Task a = new Task();
			// ArrayList<Task> data = new ArrayList<Task>();
			while (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setStart(rs.getString("start"));
				a.setEnd(rs.getString("end"));
				a.setDesk(rs.getString("desk"));
				a.setTipe(rs.getString("tipe"));
			}
			structTask insert = new structTask();
			insert.tipe = 2;
			insert.isi = a;
			stak.push(insert);

			ps = conn
					.prepareStatement("UPDATE taks SET title = ?, start = ?, end = ?, desk = ?, tipe = ? WHERE  id = "
							+ id);
			ps.setString(1, taksName);
			ps.setString(2, date);
			ps.setString(3, date1);
			ps.setString(4, desk);
			ps.setString(5, tipe);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteTaks(String id) throws SQLException {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from taks where id = " + id);
			Task a = new Task();
			while (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setTitle(rs.getString("title"));
				a.setStart(rs.getString("start"));
				a.setEnd(rs.getString("end"));
				a.setDesk(rs.getString("desk"));
				a.setTipe(rs.getString("tipe"));
				// data.add(a);
				structTask insert = new structTask();
				insert.tipe = 3;
				insert.isi = a;
				stak.push(insert);
			}
			ps = conn.prepareStatement("delete from taks where id = ?");
			ps.setString(1, id);
			// ArrayList<Task> data = new ArrayList<Task>();
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Task> getData() {
		ArrayList<Task> data = new ArrayList<Task>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from taks");
			while (rs.next()) {
				Task t = new Task();
				t.setId(rs.getInt("id"));
				t.setTitle(rs.getString("title"));
				t.setStart(rs.getString("start"));
				t.setEnd(rs.getString("end"));
				t.setDesk(rs.getString("desk"));
				t.setTipe(rs.getString("tipe"));
				data.add(t);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public ArrayList<Log> getDatalog() {
		ArrayList<Log> datalog = new ArrayList<Log>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from log");
			while (rs.next()) {
				Log l = new Log();
				l.setId(rs.getInt("id"));
				l.setTasktitle(rs.getString("tasktitle"));
				l.setDate(rs.getString("date"));
				l.setTime(rs.getString("time"));
				l.setAction(rs.getString("action"));
				datalog.add(l);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datalog;
	}

	public void insertLog(String tasktitle, String action) {
		try {
			ps = conn
					.prepareStatement("insert into log(tasktitle,action) values (?,?)");
			ps.setString(1, tasktitle);
			ps.setString(2, action);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		// TODO Auto-generated method stub
		try {
			ps = conn.prepareStatement("DELETE FROM log");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void undo() {
		try {
			if (!stak.isEmpty()) {
				switch (stak.peek().tipe) {
				case 1: {

					int id = stak.peek().isi.getId();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from taks where id = "
							+ id);

					Task a = new Task();
					// ArrayList<Task> data = new ArrayList<Task>();
					while (rs.next()) {
						a.setId(rs.getInt("id"));
						a.setTitle(rs.getString("title"));
						a.setStart(rs.getString("start"));
						a.setEnd(rs.getString("end"));
						a.setDesk(rs.getString("desk"));
						a.setTipe(rs.getString("tipe"));
						// data.add(a);
						structTask insert = new structTask();
						insert.tipe = 1;
						insert.isi = a;
						stakre.push(insert);

					}

					ps = conn.prepareStatement("delete from taks where id=(?)");
					ps.setInt(1, stak.peek().isi.getId());
					ps.executeUpdate();

					break;

				}
				case 2: {

					int id = stak.peek().isi.getId();
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from taks where id = "
							+ id);
					Task a = new Task();
					while (rs.next()) {
						a.setId(rs.getInt("id"));
						a.setTitle(rs.getString("title"));
						a.setStart(rs.getString("start"));
						a.setEnd(rs.getString("end"));
						a.setDesk(rs.getString("desk"));
						a.setTipe(rs.getString("tipe"));
						structTask insert = new structTask();
						insert.tipe = 2;
						insert.isi = a;
						stakre.push(insert);

					}

					ps = conn
							.prepareStatement("update taks set title = ?, start = date(?), end = date(?), desk = ?, tipe = ? where id = ?");
					ps.setString(1, stak.peek().isi.getTitle());
					ps.setString(2, stak.peek().isi.getStart());
					ps.setString(3, stak.peek().isi.getEnd());
					ps.setString(4, stak.peek().isi.getDesk());
					ps.setString(5, stak.peek().isi.getTipe());
					ps.setInt(6, stak.peek().isi.getId());
					ps.executeUpdate();
					break;

				}
				case 3: {
					ps = conn
							.prepareStatement("insert into taks(title,start,end,desk, tipe) values (?,date(?),date(?),?,?)");
					ps.setString(1, stak.peek().isi.getTitle());
					ps.setString(2, stak.peek().isi.getStart());
					ps.setString(3, stak.peek().isi.getEnd());
					ps.setString(4, stak.peek().isi.getDesk());
					ps.setString(5, stak.peek().isi.getTipe());
					ps.executeUpdate();

					int id = stak.peek().isi.getId();

					stmt = conn.createStatement();
					rs = stmt.executeQuery("select id from taks where id = "
							+ id);
					Task a = new Task();
					// ArrayList<Task> data = new ArrayList<Task>();
					while (rs.next()) {
						a.setId(rs.getInt("id"));
						// data.add(a);
					}
					structTask insert = new structTask();
					insert.tipe = 3;
					insert.isi = a;
					stakre.push(insert);
					break;

				}

				case 4: {
					break;
				}
				}
				stak.pop();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void redo() {
		try {
			if (!stakre.isEmpty()) {
				switch (stakre.peek().tipe) {
				case 1: {
					ps = conn
							.prepareStatement("insert into taks(title,start,end,desk, tipe) values (?,date(?),date(?),?,?)");
					ps.setString(1, stakre.peek().isi.getTitle());
					ps.setString(2, stakre.peek().isi.getStart());
					ps.setString(3, stakre.peek().isi.getEnd());
					ps.setString(4, stakre.peek().isi.getDesk());
					ps.setString(5, stakre.peek().isi.getTipe());
					ps.executeUpdate();

					break;
				}
				case 2: {
					ps = conn
							.prepareStatement("update taks set title = ?, start = date(?), end = date(?), desk = ?, tipe = ? where id = ?");
					ps.setString(1, stakre.peek().isi.getTitle());
					ps.setString(2, stakre.peek().isi.getStart());
					ps.setString(3, stakre.peek().isi.getEnd());
					ps.setString(4, stakre.peek().isi.getDesk());
					ps.setString(5, stakre.peek().isi.getTipe());
					ps.setInt(6, stakre.peek().isi.getId());
					ps.executeUpdate();
					break;
				}
				case 3: {
					// int id = stakre.peek().isi.getId();
					ps = conn.prepareStatement("delete from taks where id=(?)");
					ps.setInt(1, stakre.peek().isi.getId());
					ps.executeUpdate();

					break;
				}
				case 4: {
					break;
				}
				}
				stakre.pop();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	public int count() {
		// TODO Auto-generated method stub
		int temp = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from taks");
			while (rs.next()) {
				if (rs.getString("end") != null) {
					String c = rs.getString("end");
					String[] tanggalend = (c.split("-"));
					
					int bulan = Integer.parseInt(tanggalend[1]);
					int hari = Integer.parseInt(tanggalend[2]);
					int tahun = Integer.parseInt(tanggalend[0]);

					Date sekarang = new Date(System.currentTimeMillis());
					@SuppressWarnings("deprecation")
					int bulans = sekarang.getMonth();
					@SuppressWarnings("deprecation")
					int haris = sekarang.getDate();
					@SuppressWarnings("deprecation")
					int tahuns = sekarang.getYear();

					if (tahun == tahuns + 1900 && bulan == bulans + 1
							&& hari - haris == 1) {
						temp++;
					}
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int countd() {
		// TODO Auto-generated method stub
		int temp = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from taks");
			while (rs.next()) {
				temp++;				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

}	
