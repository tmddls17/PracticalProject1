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
			// SQLite JDBC üũ
			Class.forName("org.sqlite.JDBC");

			// SQLite �����ͺ��̽� ���Ͽ� ����
			String dbFile = "myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);

			int keep = 1;
			while (keep != 0) {
				// ������ ��ȸ
				System.out.println("\n*** ������ ��ȸ ***");
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
				System.out.println("1. ������ �߰�\n2. ������ ����\n3. ������ ����\n���ϴ� �޴���ȣ �Է� >");
				num = sc.nextInt();

				if (num == 1) {
					// ������ �߰�
					System.out.println("\n*** �� ������ �߰� ***");
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
						System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");
					else
						System.out.println("[Error] ������ �߰� ����!");
					stat2.close();
				}

				if (num == 2) {
					// ������ ����
					System.out.println("\n*** ������ ���� ***");
					Statement stat3 = con.createStatement();
					String commend, content, id;
					System.out.print("���ϴ� id >");
					id = sc.next();
					System.out.print("���ϴ� column(name, a_type, a_year, debut) >");
					commend = sc.next();
					System.out.print("������ ���� >");
					content = sc.next();
					String sql3 = "update g_artists set " + commend + " = '" + content + "' where id=1 ;";
					int cnt3 = stat3.executeUpdate(sql3);
					if (cnt3 > 0)
						System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
					else
						System.out.println("[Error] ������ ���� ����!");
					stat3.close();
				}

				if (num == 3) {
					// ������ ����
					String id;
					System.out.println("\n*** ������ ���� ***");
					Statement stat4 = con.createStatement();
					System.out.print("�����Ϸ��� �׸� ��ȣ > ");
					id = sc.next();
					String sql4 = "delete from g_artists where id=" + id + ";";
					int cnt4 = stat4.executeUpdate(sql4);
					if (cnt4 > 0)
						System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
					else
						System.out.println("[Error] ������ ���� ����!");
					stat4.close();
				}
				System.out.println("����Ͻðڽ��ϱ�? > (Yes: 1, No: 0)");
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