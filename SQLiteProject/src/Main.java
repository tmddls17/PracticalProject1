import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");

			// SQLite 데이터베이스 파일에 연결
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

			int keep = 1;
			while (keep != 0) {
				// 데이터 조회
				System.out.println("\n*** 데이터 조회 ***");
				Statement stat1 = con.createStatement();
				String sql1 = "select * from g_artists";
				ResultSet rs1 = stat1.executeQuery(sql1);
				while (rs1.next()) {
					String id = rs1.getString("id");
					String name = rs1.getString("name");
					String regdate = rs1.getString("regdate");
					System.out.println(id + " " + name + " " + regdate);
				}
				stat1.close();

				int num;
				System.out.println("1. 데이터 추가\n2. 데이터 수정\n3. 데이터 삭제\n원하는 메뉴번호 입력 >");
				num = sc.nextInt();

				if (num == 1) {
					// 데이터 추가
					System.out.println("\n*** 새 데이터 추가 ***");
					String name, a_type, a_year, debut;
					Statement stat2 = con.createStatement();
					System.out.println("name: > ");
					name = sc.next();
					System.out.println("a_type: > ");
					a_type = sc.next();
					System.out.println("a_year: > ");
					a_year = sc.next();
					System.out.println("debut: > ");
					debut = sc.next();
					String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" + " values ('" + name
							+ "', '" + a_type + "', '" + a_year + "', '" + debut + "', datetime('now', 'localtime'));";
					int cnt = stat2.executeUpdate(sql2);
					if (cnt > 0)
						System.out.println("새로운 데이터가 추가되었습니다!");
					else
						System.out.println("[Error] 데이터 추가 오류!");
					stat2.close();
				}

				if (num == 2) {
					// 데이터 수정
					System.out.println("\n*** 데이터 수정 ***");
					Statement stat3 = con.createStatement();
					String commend, content, id;
					System.out.print("원하는 id >");
					id = sc.next();
					System.out.print("원하는 column(name, a_type, a_year, debut) >");
					commend = sc.next();
					System.out.print("수정할 내용 >");
					content = sc.next();
					String sql3 = "update g_artists set " + commend + " = '" + content + "' where id=1 ;";
					int cnt3 = stat3.executeUpdate(sql3);
					if (cnt3 > 0)
						System.out.println("데이터가 수정되었습니다!");
					else
						System.out.println("[Error] 데이터 수정 오류!");
					stat3.close();
				}

				if (num == 3) {
					// 데이터 삭제
					String id;
					System.out.println("\n*** 데이터 삭제 ***");
					Statement stat4 = con.createStatement();
					System.out.print("삭제하려는 항목 번호 > ");
					id = sc.next();
					String sql4 = "delete from g_artists where id=" + id + ";";
					int cnt4 = stat4.executeUpdate(sql4);
					if (cnt4 > 0)
						System.out.println("데이터가 삭제되었습니다!");
					else
						System.out.println("[Error] 데이터 삭제 오류!");
					stat4.close();
				}
				System.out.println("계속하시겠습니까? > (Yes: 1, No: 0)");
				keep = sc.nextInt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}
			}
		}
	}

}