/**
 * 合并js,css文件
 */
with(document){
	//新 Bootstrap 核心 CSS 文件
	write('<link rel="stylesheet" href="../css/bootstrap.min.css">');
	//可选的Bootstrap主题文件（一般不用引入）
	write('<link rel="stylesheet" href="../css/bootstrap-theme.min.css">');
	
	write('<link rel="stylesheet" href="../css/dropdown-submenu.css">');
	//<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	write('<script type="text/javascript" language="javascript" src="../js/jquery-1.11.1.min.js"></script>');
	//<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	write('<script type="text/javascript" language="javascript" src="../js/bootstrap.min.js"></script>');
	
}
