<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    
<%@page import="java.sql.*, java.io.*, bbs.*, java.util.*"%>
    
<%
request.setCharacterEncoding("GBK");

int pid = Integer.parseInt(request.getParameter("pid"));
int rootId = Integer.parseInt(request.getParameter("rootId"));

String title = request.getParameter("title");
System.out.println(title);
String cont = request.getParameter("cont");
System.out.println(cont);

Connection conn = DB.getConn();

boolean autoCommit = conn.getAutoCommit();
conn.setAutoCommit(false);

String sql = "insert into article values(?, ?, ?, ?, GetDate(), ?)";
PreparedStatement ps = DB.prepareStmt(conn, sql);
ps.setInt(1, pid);
ps.setInt(2, rootId);
ps.setString(3, title);
ps.setString(4, cont);
ps.setInt(5, 0);
ps.executeUpdate();
Statement stmt = DB.createStmt(conn);
stmt.executeUpdate("update article set isleaf = 1 where id = " + pid);

conn.commit();
conn.setAutoCommit(autoCommit);
DB.close(ps);
DB.close(stmt);
DB.close(conn);
%>    

<span id="time" style="background:red">5</span>秒后自动跳转，如果不跳转，请点击下面链接

<script language="JavaScript1.2" type="text/javascript">
function delayURL(url) {
	var delay = document.getElementById("time").innerHTML;
	if(delay > 0) {
		delay --;
		document.getElementById("time").innerHTML = delay;
	} else {
		window.top.location.href = url;
	}
	setTimeout("delayURL('" + url + "')", 1000);
}
</script>

<a href="article.jsp">主题列表</a>
<script type="text/javascript">
	delayURL("article.jsp", 3000);
</script>